package lib;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class Observer {

    RunnableState runnableState;
    CommandType command;
    int breakpoint;
    Notifier textAreaNotifier;

    public Observer(Notifier n){
        textAreaNotifier = n;
    }

    public synchronized void updateUI(String s){
        textAreaNotifier.pushNotification(s);
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
}
