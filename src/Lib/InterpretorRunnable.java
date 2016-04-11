package Lib;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class InterpretorRunnable implements Runnable {
    public Observer observer;

    public InterpretorRunnable(Observer observer) {
        this.observer = observer;

        new Thread(this).start();
    }

    public void run() {
        while(true){
            System.out.println("ON attend de tes nouvelles :) ...");

            observer.waitForTestValue();
        }
    }
}
