package lib;

import controllers.CommandType;
import funlang.Frame;
import views.UIupdater;

import java.util.HashMap;

/**
 * Created by ledrou_83 on 16-04-11.
 */
public class Observer {

    RunnableState runnableState;
    CommandType command;
    HashMap<Integer, Integer> breakpoints;
    String stdinValue;
    UIupdater textAreaNotifier;

    public Observer(UIupdater n){
        textAreaNotifier = n;
        breakpoints = new HashMap<>();
    }

    public synchronized void updateUI(Frame f, int lineIndex, String subString){
        textAreaNotifier.pushNotification(f);

        textAreaNotifier.updateFileText(lineIndex, subString);
        notify();
    }

    public synchronized void updateDebuggerConsole(String text){
        textAreaNotifier.updateConsoleText(text);
        notify();
    }

    public synchronized void waitResponse() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendProgramHasEnded() {
        runnableState = RunnableState.ENDED;

        textAreaNotifier.pushProgramHasEnded();
    }

    public boolean runnableHasEnded(){
        return runnableState == RunnableState.ENDED;
    }

    public synchronized void sendContinue(HashMap<Integer, Integer> breakpoints) {
        this.command = CommandType.CONTINUE;
        this.breakpoints = breakpoints;

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

    public synchronized void sendRequireStdInEntry(){
        textAreaNotifier.updateRequireStdinEntry();
        notify();
    }

    public synchronized void sendStdInEntry(String s){
        stdinValue = s;
        notify();
    }
}
