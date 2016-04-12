package lib;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class Observer {
    RunnableState runnableState;
    public String testValue;

    public synchronized void update() {
        testValue = new BigInteger(130, new SecureRandom()).toString(32);

        notify();
    }

    //TEST
    public synchronized void waitForTestValue() {
        try {
            wait();
            System.out.println(testValue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    //TEST

    public synchronized void updateState(RunnableState rs) {
        this.runnableState = rs;
    }

    public synchronized boolean runnableHasEnded() {
        return runnableState == RunnableState.ENDED;
    }
}
