
package funlang;

import java.util.*;

import funlang.syntax.analysis.*;
import funlang.syntax.node.*;

public class SemanticVerifier
        extends DepthFirstAdapter {

    private FunType resultType;

    private List<FunType> resultTypes;

    private FunctionTable functionTable = new FunctionTable();

    private Scope currentScope;

    private FunctionInfo currentFunc;

    private Map<Node, Scope> scopes = new HashMap<Node, Scope>();

    private Map<ANumTerm, Integer> numbers = new HashMap<ANumTerm, Integer>();

    private SemanticVerifier() {

    }

    public static Semantics verify(
            Start tree) {

        SemanticVerifier semanticVerifier = new SemanticVerifier();
        tree.apply(semanticVerifier);
        return new Semantics(semanticVerifier.functionTable,
                semanticVerifier.numbers);
    }

    @Override
    public void caseStart(
            Start node) {

        // Find all functions
        node.apply(new DepthFirstAdapter() {

            @Override
            public void caseAFunc(
                    AFunc node) {

                SemanticVerifier.this.functionTable.add(new FunctionInfo(node),
                        node.getId());
            }
        });

        node.getPProgram().apply(this);
    }

    @Override
    public void inAFunc(
            AFunc node) {

        // set current function
        this.currentFunc = this.functionTable.get(node.getId().getText());

        assert this.currentScope == null;

        // add top-level scope
        this.currentScope = new Scope(null);
        this.scopes.put(node, this.currentScope);

        // add parameters into scope
        List<Variable> parameters = this.currentFunc.getParameters();
        for (Variable parameter : parameters) {
            this.currentScope.addParameter(parameter);
        }
    }

    @Override
    public void outAFunc(
            AFunc node) {

        // recover previous scope
        this.currentScope = this.currentScope.getParent();

        // unset current function
        this.currentFunc = null;

        assert this.currentScope == null;
    }

    @Override
    public void inABlockBlock(
            ABlockBlock node) {

        // add new scope
        this.currentScope = new Scope(this.currentScope);
        this.scopes.put(node, this.currentScope);
    }

    @Override
    public void outABlockBlock(
            ABlockBlock node) {

        // recover previous scope
        this.currentScope = this.currentScope.getParent();
    }

    @Override
    public void caseADeclStm(
            ADeclStm node) {

        // get expression type
        node.getExp().apply(this);
        FunType expType = this.resultType;

        if(expType == FunType.VOID) {
            throw new SemanticException("cannot define a variable of type void", node.getAssign());
        }
        
        // add variable into scope
        this.currentScope.addVariable(node.getId(), expType);
    }

    @Override
    public void caseAAssignStm(
            AAssignStm node) {

        // get variable
        String name = node.getId().getText();
        Variable variable = this.currentScope.getVariable(name);

        if (variable == null) {
            throw new SemanticException("undeclared variable \"" + name + "\"",
                    node.getId());
        }

        // get expression type
        node.getExp().apply(this);
        FunType expType = this.resultType;

        // check compatibility
        if (expType != variable.getType()) {
            throw new SemanticException("invalid assignment", node.getAssign());
        }
    }

    @Override
    public void caseAIfStm(
            AIfStm node) {

        // check condition
        node.getExp().apply(this);
        FunType expType = this.resultType;

        if (expType != FunType.BOOL) {
            throw new SemanticException("expression is not boolean",
                    node.getLPar());
        }

        // check block
        node.getBlock().apply(this);
    }

    @Override
    public void caseAIfElseStm(
            AIfElseStm node) {

        // check condition
        node.getExp().apply(this);
        FunType expType = this.resultType;

        if (expType != FunType.BOOL) {
            throw new SemanticException("expression is not boolean",
                    node.getLPar());
        }

        // check blocks
        node.getThenBlock().apply(this);
        node.getElseBlock().apply(this);
    }

    @Override
    public void caseAWhileStm(
            AWhileStm node) {

        // check condition
        node.getExp().apply(this);
        FunType expType = this.resultType;

        if (expType != FunType.BOOL) {
            throw new SemanticException("expression is not boolean",
                    node.getLPar());
        }

        // check block
        node.getBlock().apply(this);
    }

    @Override
    public void caseAReturnStm(
            AReturnStm node) {

        if (this.currentFunc == null) {
            throw new SemanticException("there is no function to return from",
                    node.getReturn());
        }

        FunType returnType = this.currentFunc.getReturnType();

        if (node.getExp() == null) {
            if (returnType != FunType.VOID) {
                throw new SemanticException("expecting a value",
                        node.getSemi());
            }
        }
        else {
            node.getExp().apply(this);
            FunType expType = this.resultType;

            if (expType != returnType) {
                throw new SemanticException("invalid return value",
                        node.getReturn());
            }
        }
    }

    @Override
    public void caseAEqualExp(
            AEqualExp node) {

        node.getLeft().apply(this);
        FunType leftType = this.resultType;

        node.getRight().apply(this);
        FunType rightType = this.resultType;

        if (leftType != rightType) {
            throw new SemanticException(
                    "arguments of operator are not of the same type",
                    node.getEqual());
        }

        this.resultType = FunType.BOOL;
    }

    @Override
    public void caseALtExp(
            ALtExp node) {

        node.getLeft().apply(this);
        FunType leftType = this.resultType;

        if (leftType != FunType.INT) {
            throw new SemanticException(
                    "invalid left argument for less-than operator",
                    node.getLt());
        }

        node.getRight().apply(this);
        FunType rightType = this.resultType;

        if (rightType != FunType.INT) {
            throw new SemanticException(
                    "invalid right argument for less-than operator",
                    node.getLt());
        }

        this.resultType = FunType.BOOL;
    }

    @Override
    public void caseAAddArith(
            AAddArith node) {

        node.getArith().apply(this);
        FunType leftType = this.resultType;

        if (leftType != FunType.INT) {
            throw new SemanticException("invalid left argument for addition",
                    node.getPlus());
        }

        node.getFac().apply(this);
        FunType rightType = this.resultType;

        if (rightType != FunType.INT) {
            throw new SemanticException("invalid right argument for addition",
                    node.getPlus());
        }

        this.resultType = FunType.INT;
    }

    @Override
    public void caseASubArith(
            ASubArith node) {

        node.getArith().apply(this);
        FunType leftType = this.resultType;

        if (leftType != FunType.INT) {
            throw new SemanticException("invalid left argument for subtraction",
                    node.getMinus());
        }

        node.getFac().apply(this);
        FunType rightType = this.resultType;

        if (rightType != FunType.INT) {
            throw new SemanticException(
                    "invalid right argument for subtraction", node.getMinus());
        }

        this.resultType = FunType.INT;
    }

    @Override
    public void caseAMulFac(
            AMulFac node) {

        node.getFac().apply(this);
        FunType leftType = this.resultType;

        if (leftType != FunType.INT) {
            throw new SemanticException(
                    "invalid left argument for multiplication", node.getStar());
        }

        node.getPow().apply(this);
        FunType rightType = this.resultType;

        if (rightType != FunType.INT) {
            throw new SemanticException(
                    "invalid right argument for multiplication",
                    node.getStar());
        }

        this.resultType = FunType.INT;
    }

    @Override
    public void caseAExpoPow(
            AExpoPow node) {

        node.getTerm().apply(this);
        FunType baseType = this.resultType;

        if (baseType != FunType.INT) {
            throw new SemanticException("invalid base value", node.getCaret());
        }

        node.getPow().apply(this);
        FunType exponentType = this.resultType;

        if (exponentType != FunType.INT) {
            throw new SemanticException("invalid exponent", node.getCaret());
        }

        this.resultType = FunType.INT;
    }

    @Override
    public void caseANumTerm(
            ANumTerm node) {

        // check number and store int representation in this.numbers
        try {
            int number = Integer.parseInt(node.getNum().getText());
            this.numbers.put(node, number);
        }
        catch (NumberFormatException e) {
            throw new SemanticException("invalid number", node.getNum());
        }

        this.resultType = FunType.INT;
    }

    @Override
    public void caseAFalseTerm(
            AFalseTerm node) {

        this.resultType = FunType.BOOL;
    }

    @Override
    public void caseATrueTerm(
            ATrueTerm node) {

        this.resultType = FunType.BOOL;
    }

    @Override
    public void caseAStringTerm(
            AStringTerm node) {

        this.resultType = FunType.STRING;
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        // get variable
        String name = node.getId().getText();
        Variable variable = this.currentScope.getVariable(name);

        if (variable == null) {
            throw new SemanticException("undeclared variable \"" + name + "\"",
                    node.getId());
        }

        this.resultType = variable.getType();
    }

    @Override
    public void caseACall(
            ACall node) {

        // check if function exists
        String name = node.getId().getText();
        FunctionInfo functionInfo = this.functionTable.get(name);

        if (functionInfo == null) {
            throw new SemanticException("Unknown function " + name,
                    node.getId());
        }

        // get function parameters
        List<Variable> parameters = functionInfo.getParameters();

        // get call argument types
        List<FunType> argTypes;

        if (node.getArgs() == null) {
            argTypes = new LinkedList<FunType>();
        }
        else {
            node.getArgs().apply(this);
            argTypes = this.resultTypes;
        }

        // check arguments

        int paramSize = parameters.size();
        int argSize = argTypes.size();

        if (argSize != paramSize) {

            throw new SemanticException("Function " + name + " + Expects "
                    + paramSize + " parameters but " + argSize
                    + " arguments were found", node.getLPar());
        }

        Iterator<FunType> argIterator = argTypes.iterator();
        int index = 0;
        for (Variable parameter : parameters) {
            index++;
            FunType argType = argIterator.next();
            if (argType != parameter.getType()) {
                throw new SemanticException("parameter " + index
                        + " is not of type " + parameter.getType(),
                        node.getLPar());
            }
        }

        this.resultType = functionInfo.getReturnType();
    }

    @Override
    public void caseAArgs(
            AArgs node) {

        List<FunType> types = new LinkedList<FunType>();

        // first arg
        node.getArg().apply(this);
        types.add(this.resultType);

        // additional args
        for (PAdditionalArg additionalArg : node.getAdditionalArgs()) {

            additionalArg.apply(this);
            types.add(this.resultType);
        }

        this.resultTypes = types;
    }

}
