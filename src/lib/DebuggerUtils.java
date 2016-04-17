package lib;

import controllers.CommandType;
import funlang.Frame;
import funlang.syntax.node.*;

/**
 * Created by ledrou_83 on 16-04-12.
 */
public class DebuggerUtils {
    public Observer observer;
    public Frame lastFrame; // Le frame du dernier breakpoint, pas necessairement le parent
    public Frame parentFrame; // Le frame du parent du lastFrame

    // Use case flags
    boolean next; // When NEXT or Starting the program without any breakpoints

    //

    public DebuggerUtils(Observer observer){
        this.observer = observer;
    }

    public void runBreakPoint(Frame frame, int lineNumber, Node node){
        if (observer.breakpoints.containsKey(lineNumber)
            || observer.command.equals(CommandType.STEP_IN)
            || (observer.command.equals((CommandType.STEP_OUT))
                && parentFrame !=  null
                && frame == parentFrame)
            || (observer.command.equals(CommandType.STEP_OVER)
                && frame.getPreviousFrame() != this.lastFrame))
        {
            System.out.println("Command: " + observer.command + " Line number: " + lineNumber);
            observer.updateUI(frame, lineNumber, nodeText(node));
            observer.waitNextCommand();

            if (lastFrame !=  null && frame != lastFrame)
                parentFrame = lastFrame;
            lastFrame = frame;
        }
    }

    private String nodeText(Node node){
        String text = "";
        if(node instanceof AAddArith){
            AAddArith n = (AAddArith) node;
            text = text.concat(n.getArith().toString().trim());
            text = text.concat(n.getPlus().toString().trim());
            text = text.concat(n.getFac().toString().trim());
        }
        else if(node instanceof AAssignStm){
            AAssignStm n = (AAssignStm) node;
            text = text.concat(n.getId().toString().trim());
            text = text.concat(n.getAssign().toString().trim());
            text = text.concat(n.getExp().toString().trim());
            text = text.concat(n.getSemi().toString().trim());

        }
        else if(node instanceof ACall){
            ACall n = (ACall) node;
            text = text.concat(n.getId().toString().trim());
            text = text.concat(n.getLPar().toString().trim());
            if(n.getArgs() != null)
                text = text.concat(n.getArgs().toString().trim());
            text = text.concat(n.getRPar().toString().trim());
        }
        else if(node instanceof AEqualExp){
            AEqualExp n = (AEqualExp) node;
            text = text.concat(n.getLeft().toString().trim());
            text = text.concat(n.getEqual().toString().trim());
            text = text.concat(n.getRight().toString().trim());
        }
        else if(node instanceof AExpoPow){
            AExpoPow n = (AExpoPow) node;
            text = text.concat(n.getTerm().toString().trim());
            text = text.concat(n.getCaret().toString().trim());
            text = text.concat(n.getPow().toString().trim());
        }
        else if(node instanceof  ALtExp){
            ALtExp n = (ALtExp) node;
            text = text.concat(n.getLeft().toString().trim());
            text = text.concat(n.getLt().toString().trim());
            text = text.concat(n.getRight().toString().trim());
        }
        else if(node instanceof AMulFac){
            AMulFac n = (AMulFac) node;
            text = text.concat(n.getFac().toString().trim());
            text = text.concat(n.getStar().toString().trim());
            text = text.concat(n.getPow().toString().trim());
        }
        else if(node instanceof AReturnStm){
            AReturnStm n = (AReturnStm) node;
            text = text.concat(n.getReturn().toString());
            text = text.concat(n.getExp().toString().trim());
            text = text.concat(n.getSemi().toString().trim());
        }
        else if(node instanceof ASubArith){
            ASubArith n = (ASubArith) node;
            text = text.concat(n.getArith().toString().trim());
            text = text.concat(n.getMinus().toString().trim());
            text = text.concat(n.getFac().toString().trim());
        }
        else if(node instanceof AWhileStm){
            AWhileStm n = (AWhileStm) node;
            text = text.concat(n.getWhile().toString().trim());
            text = text.concat(n.getLPar().toString().trim());
            text = text.concat(n.getExp().toString().trim());
            text = text.concat(n.getRPar().toString().trim());
        }
        return text;
    }
}
