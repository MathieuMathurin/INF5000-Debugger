
package funlang;

import funlang.syntax.node.*;

public class InterpreterException
        extends RuntimeException {

    public InterpreterException(
            String message,
            Token location) {

        super("[" + location.getLine() + "," + location.getPos()
                + "] RUNTIME ERROR: " + message);
    }
//ajoute frame, recuperer la ligne
}
