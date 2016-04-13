package controllers;

import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import lib.InterpretorRunnable;
import lib.Observer;
import views.UIupdater;

public class Start {
    public InterpretorRunnable execute(String[] args, int breakpoint, TextArea fileView, TableView localVariablesView){
        UIupdater notifier = new UIupdater(fileView, localVariablesView);
        Observer observer = new Observer(notifier);
        observer.sendContinue(breakpoint);

        return new InterpretorRunnable(args, observer);
    }
}
