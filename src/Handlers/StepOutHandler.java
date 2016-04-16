package Handlers;

import controllers.StepOut;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lib.InterpretorRunnable;
import views.MainWindow;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class StepOutHandler implements EventHandler<ActionEvent> {
    MainWindow window;
    StepOut cmdStepOut;

    public StepOutHandler(MainWindow mw){
        this.window = mw;
        this.cmdStepOut = new StepOut();
    }

    @Override
    public void handle(ActionEvent event) {
        if (window.interpretorRunnable != null)
            cmdStepOut.execute(window.interpretorRunnable.observer);
    }
}
