package Handlers;

import controllers.StepOver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lib.InterpretorRunnable;
import views.MainWindow;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class StepOverHandler implements EventHandler<ActionEvent> {
    MainWindow window;
    StepOver cmdStepOver;

    public StepOverHandler(MainWindow mw){
        this.window = mw;
        this.cmdStepOver = new StepOver();
    }

    @Override
    public void handle(ActionEvent event) {
        if (window.interpretorRunnable != null)
            cmdStepOver.execute(window.interpretorRunnable.observer);
    }
}
