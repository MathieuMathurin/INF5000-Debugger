package Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lib.InterpretorRunnable;
import views.MainWindow;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class StopHandler implements EventHandler<ActionEvent> {
    MainWindow window;

    public StopHandler(MainWindow mw){
        this.window = mw;
    }

    @Override
    public void handle(ActionEvent event) {
        window.interpretorRunnable = null; // Ne jamais relancer un thread...

        this.window.stopBtn.setDisable(true);
        this.window.stepOutBtn.setDisable(true);
        this.window.stepInBtn.setDisable(true);
        this.window.stepOverBtn.setDisable(true);
        this.window.addWatchBtn.setDisable(true);
        this.window.continueBtn.setDisable(true);
    }
}
