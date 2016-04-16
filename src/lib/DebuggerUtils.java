package lib;

import controllers.CommandType;
import funlang.Frame;

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

    public void runBreakPoint(Frame frame, int lineNumber){
        if (observer.breakpoints.containsKey(lineNumber)
            || observer.command.equals(CommandType.STEP_IN)
            || (observer.command.equals((CommandType.STEP_OUT))
                && parentFrame !=  null
                && frame == parentFrame)
            || (observer.command.equals(CommandType.STEP_OVER)
                && frame.getPreviousFrame() != this.lastFrame))
        {
            System.out.println("Command: " + observer.command + " Line number: " + lineNumber);
            observer.updateUI(frame);
            observer.waitNextCommand();

            if (lastFrame !=  null && frame != lastFrame)
                parentFrame = lastFrame;
            lastFrame = frame;
        }
    }
}
