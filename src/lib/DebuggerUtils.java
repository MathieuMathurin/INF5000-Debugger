package lib;

import funlang.syntax.node.*;
import funlang.Frame;

/**
 * Created by ledrou_83 on 16-04-12.
 */
public class DebuggerUtils {
    public Observer observer;

    public DebuggerUtils(Observer observer){
        this.observer = observer;
    }

    public void runBreakPoint(Frame frame, int lineNumber){
        //if (observer.lineNumber != lineNumber) return;

        System.out.println("Line number: " + lineNumber);
        observer.updateUI(frame.toString());
        observer.waitNextCommand();
    }
}
