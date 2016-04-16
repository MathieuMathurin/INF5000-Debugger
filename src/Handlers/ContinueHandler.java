package Handlers;

import controllers.Continue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lib.InterpretorRunnable;
import views.MainWindow;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class ContinueHandler implements EventHandler<ActionEvent> {
    MainWindow window;
    Continue cmdContinue;

    public ContinueHandler(MainWindow mw){
        this.window = mw;
        this.cmdContinue = new Continue();
    }

    @Override
    public void handle(ActionEvent event) {
        if(window.interpretorRunnable != null)
            cmdContinue.execute(window.interpretorRunnable.observer, window.model.breakpoints);
    }
}
