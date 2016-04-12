
package funlang;

import funlang.syntax.node.*;

public class Variable {

    private TId nameToken;

    private FunType type;

    public Variable(
            TId nameToken,
            FunType type) {

        this.nameToken = nameToken;
        this.type = type;
    }

    public Variable(
            AParam node) {

        this.nameToken = node.getId();
        this.type = FunType.getType(node.getType());
    }

    public FunType getType() {

        return this.type;
    }

    public String getName() {

        return this.nameToken.getText();
    }

}
