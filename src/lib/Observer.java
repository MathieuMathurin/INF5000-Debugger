package lib;

import controllers.CommandType;
import funlang.Frame;
import views.UIupdater;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class Observer {

    RunnableState runnableState;
    CommandType command;
    int breakpoint;
    UIupdater textAreaNotifier;

    public Observer(UIupdater n){
        textAreaNotifier = n;
    }

    public synchronized void updateUI(Frame f){
        textAreaNotifier.pushNotification(f);
        notify();
    }

    public synchronized void waitNextCommand() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void updateState(RunnableState rs) {
        this.runnableState = rs;
    }

    public synchronized boolean runnableHasEnded() {
        return runnableState == RunnableState.ENDED;
    }

    public synchronized void sendContinue(int breakpoint) {
        this.command = CommandType.CONTINUE;
        this.breakpoint = breakpoint;

        notify();
    }

    public synchronized void sendStepOver() {
        this.command = CommandType.STEP_OVER;
        notify();
    }

    public synchronized void sendStepIn() {
        this.command = CommandType.STEP_IN;
        notify();
    }

    public synchronized void sendStepOut() {
        this.command = CommandType.STEP_OUT;
        notify();
    }
}
