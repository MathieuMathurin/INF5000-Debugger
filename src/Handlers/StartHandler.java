package Handlers;

import controllers.Start;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lib.InterpretorRunnable;
import views.MainWindow;
import views.UIupdater;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class StartHandler implements EventHandler<ActionEvent> {
    MainWindow window;
    Start cmdStart;

    public StartHandler(MainWindow mw){
        this.window = mw;
        this.cmdStart = new Start();
    }

    @Override
    public void handle(ActionEvent event) {
        if (window.interpretorRunnable == null || window.interpretorRunnable.observer.runnableHasEnded()) {
            window.interpretorRunnable = cmdStart.execute(window.model.args, window.model.breakpoints, this.window.uIupdater);
        }
    }
}
