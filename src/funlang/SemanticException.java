
package funlang;

import funlang.syntax.node.*;

public class SemanticException
        extends RuntimeException {

    public SemanticException(
            String message,
            Token location) {

        super("[" + location.getLine() + "," + location.getPos()
                + "] SEMANTIC ERROR: " + message);
    }

}
