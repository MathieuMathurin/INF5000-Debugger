package Lib;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class InterpretorThread extends Thread {
    public SynchronousQueue<String> queue;

    public InterpretorThread() {
        this.queue = new SynchronousQueue<String>();
    }

    public void run() {
        try {
            while(true){
                System.out.println("I'm in!");

                String event = queue.take(); // thread will block here

                System.out.printf("[%s] consumed event : %s %n", Thread .currentThread().getName(), event);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
