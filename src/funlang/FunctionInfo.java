
package funlang;

import java.util.*;

import funlang.syntax.analysis.*;
import funlang.syntax.node.*;

public class FunctionInfo {

    private AFunc declaration;

    private List<Variable> parameters;

    public FunctionInfo(
            AFunc declaration) {

        this.declaration = declaration;
    }

    public String getName() {

        return this.declaration.getId().getText();
    }

    public List<Variable> getParameters() {

        if (this.parameters == null) {

            if (this.declaration.getParams() == null) {
                this.parameters = new LinkedList<Variable>();
            }
            else {

                final List<Variable> parameterList = new LinkedList<Variable>();

                this.declaration.getParams().apply(new DepthFirstAdapter() {

                    @Override
                    public void caseAParam(
                            AParam node) {

                        parameterList.add(new Variable(node));
                    }
                });

                this.parameters = parameterList;
            }
        }

        return this.parameters;
    }

    public FunType getReturnType() {

        return FunType.getType(this.declaration.getReturnType());
    }

    public PBody getBody() {

        return this.declaration.getBody();

    }

}
