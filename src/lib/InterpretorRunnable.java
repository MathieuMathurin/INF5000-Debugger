package lib;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class InterpretorRunnable implements Runnable {
    public String[] args;
    public Observer observer;

    public InterpretorRunnable(String[] args, Observer observer) {
        this.args = args;
        this.observer = observer;

        new Thread(this).start();
    }

    public void run() {
        observer.updateState(RunnableState.RUNNING);
        funlang.Main.startDebugMode(args, observer);
        observer.updateState(RunnableState.ENDED);
    }
}
