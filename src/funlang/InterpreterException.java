
package funlang;

import funlang.syntax.node.*;
import sun.org.mozilla.javascript.internal.Function;

public class InterpreterException
        extends RuntimeException {

    public InterpreterException(
            String message) {
        super(message);
    }

    public static String getMessage(String message, Token location, Frame f){
        Frame frame = f;
        String output = "[" + location.getLine() + "," + location.getPos()
                + "] RUNTIME ERROR: " + message;
        output = output.concat("\n");
        while(frame.getPreviousFrame() !=null){
            Frame previous = frame.getPreviousFrame();
            FunctionInfo function = frame.getFunctionInfo();
            String info = function != null ? function.getName() : null;
            if(info != null){
                output = output.concat("At line: " + previous.getCallingLine() + ", Function: " + info + "\n");
            }else{
                output = output.concat("At line: " + previous.getCallingLine() + "\n");
            }
            frame = previous;
        }

        return output;
    }
//ajoute frame, recuperer la ligne
}
