package lib;

import funlang.Frame;

/**
 * Created by ledrou_83 on 16-04-12.
 */
public class DebuggerUtils {
    public Observer observer;
    public Frame lastFrame;

    public DebuggerUtils(Observer observer){
        this.observer = observer;
    }

    public void runBreakPoint(Frame frame, int lineNumber){
        if (observer.breakpoint == lineNumber
            || observer.command.equals(CommandType.STEP_IN)
            || (observer.command.equals(CommandType.STEP_OVER)
                && frame.getPreviousFrame() != this.lastFrame))
        {
            System.out.println("Command: " + observer.command + " Line number: " + lineNumber);
            observer.updateUI(frame.toString());
            observer.waitNextCommand();

            lastFrame = frame;
        }
    }
}
