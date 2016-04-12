
package funlang;

import java.util.*;

import funlang.syntax.node.*;

public class Scope {

    private Scope parent;

    private Map<String, Variable> variableMap = new HashMap<String, Variable>();

    public Scope(
            Scope parent) {

        this.parent = parent;
    }

    public Scope getParent() {

        return this.parent;
    }

    public Variable getVariable(
            String name) {

        Variable variable = this.variableMap.get(name);
        if (variable == null && this.parent != null) {
            return this.parent.getVariable(name);
        }
        return variable;
    }

    public void addVariable(
            TId nameToken,
            FunType type) {

        String name = nameToken.getText();
        if (getVariable(name) != null) {
            throw new SemanticException(
                    "variable \"" + name + "\" is aldready declared",
                    nameToken);
        }

        this.variableMap.put(name, new Variable(nameToken, type));
    }

    public void addParameter(
            Variable parameter) {

        this.variableMap.put(parameter.getName(), parameter);
    }
}
