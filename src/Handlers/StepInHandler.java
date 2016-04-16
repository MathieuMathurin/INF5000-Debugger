package Handlers;

import controllers.StepIn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lib.InterpretorRunnable;
import views.MainWindow;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class StepInHandler implements EventHandler<ActionEvent> {
    MainWindow window;
    StepIn cmdStepIn;

    public StepInHandler(MainWindow mw){
        this.window = mw;
        this.cmdStepIn = new StepIn();
    }

    @Override
    public void handle(ActionEvent event) {
        if (window.interpretorRunnable != null)
            cmdStepIn.execute(window.interpretorRunnable.observer);
    }
}
