package lib;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class Observer {

    RunnableState runnableState;
    public String testValue;
    Notifier textAreaNotifier;

    public Observer(Notifier n){
        textAreaNotifier = n;
    }

    public synchronized void updateUI(String s){
        textAreaNotifier.pushNotification(s);
    }

    public synchronized void update() {
        testValue = new BigInteger(130, new SecureRandom()).toString(32);

        notify();
    }

    public synchronized void updateState(RunnableState rs) {
        this.runnableState = rs;
    }

    public synchronized boolean runnableHasEnded() {
        return runnableState == RunnableState.ENDED;
    }
}
