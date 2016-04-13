
package funlang;

import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

import funlang.syntax.analysis.*;
import funlang.syntax.node.*;

import lib.*;

public class Interpreter
        extends DepthFirstAdapter {

    private DebuggerUtils debuggerUtils;

    private Semantics semantics;

    private Value result;

    private List<Value> resultList;

    private Frame currentFrame;

    public Interpreter(
            Semantics semantics,
            Observer observer) {

        this.semantics = semantics;
        if (observer != null) this.debuggerUtils = new DebuggerUtils(observer);
    }

    public static void interpret(
            Start tree,
            Semantics semantics,
            Observer observer) {

        Interpreter interpreter = new Interpreter(semantics, observer);
        tree.apply(interpreter);
    }

    @Override
    public void caseAProgram(
            AProgram node) {

        // setup bottom frame
        this.currentFrame = new Frame(null, null);

        // execute main program
        node.getBlock().apply(this);
    }

    @Override
    public void caseABlockBody(
            ABlockBody node) {

        node.getBlock().apply(this);

        // check that a value was returned
        Value result = this.currentFrame.getReturnValue();
        if (result == null && this.currentFrame.getFunctionInfo()
                .getReturnType() != FunType.VOID) {
            throw new InterpreterException("missing return statement",
                    ((ABlockBlock) node.getBlock()).getRBrace());
        }
    }

    @Override
    public void caseAInternalBody(
            AInternalBody node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.

        FunctionInfo functionInfo = this.currentFrame.getFunctionInfo();
        String name = functionInfo.getName();

        if (name.equals("println")) {
            System.out.println();
        }
        else if (name.equals("printint")) {
            System.out.print(
                    ((IntValue) this.currentFrame.getVariable("n")).getValue());
        }
        else if (name.equals("printbool")) {
            System.out.print(((BoolValue) this.currentFrame.getVariable("b"))
                    .getValue());
        }
        else if (name.equals("printstring")) {
            System.out.print(((StringValue) this.currentFrame.getVariable("s"))
                    .getValue());
        }
        else if (name.equals("readint")) {
            System.out.println("Entrez un nombre");

            Scanner in = new Scanner(System.in);
            int num = in.nextInt();

            this.currentFrame.setReturnValue(new IntValue(num));
        }
        else if (name.equals("readstring")) {
            Scanner in = new Scanner(System.in);
            String s = in.next();

            this.currentFrame.setReturnValue(new StringValue(s));
        }
        else {
            throw new InterpreterException(
                    "unhandled function \"" + name + "\"", node.getSemi());
        }
    }

    @Override
    public void caseADeclStm(
            ADeclStm node) {

        node.getExp().apply(this);
        Value result = this.result;

        this.currentFrame.setVariable(node.getId().getText(), result);
    }

    @Override
    public void caseAAssignStm(
            AAssignStm node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getAssign().getLine());

        node.getExp().apply(this);
        Value result = this.result;

        this.currentFrame.setVariable(node.getId().getText(), result);
    }

    @Override
    public void caseAIfStm(
            AIfStm node) {

        // Evaluate the expression
        node.getExp().apply(this);
        boolean value = ((BoolValue) this.result).getValue();
        if (value) {
            node.getBlock().apply(this);
        }
    }

    @Override
    public void caseAIfElseStm(
            AIfElseStm node) {

        // Evaluate the expression
        node.getExp().apply(this);
        boolean value = ((BoolValue) this.result).getValue();
        if (value) {
            node.getThenBlock().apply(this);
        }
        else {
            node.getElseBlock().apply(this);
        }
    }

    @Override
    public void caseAWhileStm(
            AWhileStm node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getWhile().getLine());

        while (true) {
            // Evaluate the expression
            node.getExp().apply(this);
            boolean value = ((BoolValue) this.result).getValue();
            if (!value) {
                break;
            }

            // interpret the block
            node.getBlock().apply(this);
        }
    }

    @Override
    public void caseAReturnStm(
            AReturnStm node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getReturn().getLine());

        if (node.getExp() != null) {
            node.getExp().apply(this);
            this.currentFrame.setReturnValue(this.result);
        }

        // return
        throw new ReturnException();
    }

    @Override
    public void caseAEqualExp(
            AEqualExp node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getEqual().getLine());

        node.getLeft().apply(this);
        Value left = this.result;

        node.getRight().apply(this);
        Value right = this.result;

        this.result = BoolValue.get(left.isSame(right));
    }

    @Override
    public void caseALtExp(
            ALtExp node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getLt().getLine());


        node.getLeft().apply(this);
        int left = ((IntValue) this.result).getValue();

        node.getRight().apply(this);
        int right = ((IntValue) this.result).getValue();

        this.result = BoolValue.get(left < right);
    }

    @Override
    public void caseAAddArith(
            AAddArith node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getPlus().getLine());

        node.getArith().apply(this);
        int left = ((IntValue) this.result).getValue();

        node.getFac().apply(this);
        int right = ((IntValue) this.result).getValue();

        this.result = new IntValue(left + right);
    }

    @Override
    public void caseASubArith(
            ASubArith node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getMinus().getLine());

        node.getArith().apply(this);
        int left = ((IntValue) this.result).getValue();

        node.getFac().apply(this);
        int right = ((IntValue) this.result).getValue();

        this.result = new IntValue(left - right);
    }

    @Override
    public void caseAMulFac(
            AMulFac node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getStar().getLine());

        node.getFac().apply(this);
        int left = ((IntValue) this.result).getValue();

        node.getPow().apply(this);
        int right = ((IntValue) this.result).getValue();

        this.result = new IntValue(left * right);
    }

    @Override
    public void caseAExpoPow(
            AExpoPow node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getCaret().getLine());

        node.getTerm().apply(this);
        int base = ((IntValue) this.result).getValue();

        node.getPow().apply(this);
        int exponent = ((IntValue) this.result).getValue();

        if (exponent < 0) {
            throw new InterpreterException("invalid exponent", node.getCaret());
        }

        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        this.result = new IntValue(result);

    }

    @Override
    public void caseANumTerm(
            ANumTerm node) {

        this.result = new IntValue(this.semantics.getNumber(node));
    }

    @Override
    public void caseAFalseTerm(
            AFalseTerm node) {

        this.result = BoolValue.get(false);
    }

    @Override
    public void caseATrueTerm(
            ATrueTerm node) {

        this.result = BoolValue.get(true);
    }

    @Override
    public void caseAStringTerm(
            AStringTerm node) {

        String string = node.getStringLiteral().getText();
        string = string.substring(1, string.length() - 1);
        this.result = new StringValue(string);
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        this.result = this.currentFrame.getVariable(node.getId().getText());
    }

    @Override
    public void caseACall(
            ACall node) {
        debuggerUtils.runBreakPoint(this.currentFrame, node.getId().getLine());

        // get function
        String name = node.getId().getText();
        FunctionInfo functionInfo = this.semantics.getFunctionTable().get(name);

        // evaluate call arguments
        List<Value> args;

        if (node.getArgs() == null) {
            args = new LinkedList<Value>();
        }
        else {
            node.getArgs().apply(this);
            args = this.resultList;
        }

        // get function parameters
        List<Variable> parameters = functionInfo.getParameters();

        // create new frame
        this.currentFrame = new Frame(this.currentFrame, functionInfo);

        // fill frame

        Iterator<Value> argIterator = args.iterator();
        for (Variable parameter : parameters) {
            Value arg = argIterator.next();
            this.currentFrame.setVariable(parameter.getName(), arg);
        }

        // execute the function
        Value result = execute(functionInfo);

        // put back previous frame
        this.currentFrame = this.currentFrame.getPreviousFrame();

        this.result = result;
    }

    private Value execute(
            FunctionInfo functionInfo) {

        try {
            functionInfo.getBody().apply(this);
        }
        catch (ReturnException e) {

        }

        // retieve return value
        Value result = this.currentFrame.getReturnValue();

        return result;
    }

    @Override
    public void caseAArgs(
            AArgs node) {

        List<Value> values = new LinkedList<Value>();

        // first arg
        node.getArg().apply(this);
        values.add(this.result);

        // additional args
        for (PAdditionalArg additionalArg : node.getAdditionalArgs()) {

            additionalArg.apply(this);
            values.add(this.result);
        }

        this.resultList = values;
    }
}
