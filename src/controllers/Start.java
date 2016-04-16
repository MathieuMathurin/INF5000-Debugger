package controllers;

import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import lib.InterpretorRunnable;
import lib.Observer;
import views.UIupdater;

import java.util.HashMap;

public class Start {
    public InterpretorRunnable execute(String[] args, HashMap<Integer, Integer> breakpoints, UIupdater n){
        UIupdater notifier = n;
        Observer observer = new Observer(notifier);
        
        if(!breakpoints.isEmpty())
            observer.sendContinue(breakpoints);
        else
            observer.sendStepIn();

        return new InterpretorRunnable(args, observer);
    }
}
