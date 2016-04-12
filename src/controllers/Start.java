package controllers;

import javafx.scene.control.TextArea;
import lib.InterpretorRunnable;
import lib.Notifier;
import lib.Observer;

public class Start {
    public InterpretorRunnable execute(String[] args, int breakpoint, TextArea t){
        Notifier notifier = new Notifier(t);
        Observer observer = new Observer(notifier);
        observer.sendContinue(breakpoint);

        return new InterpretorRunnable(args, observer);
    }
}
