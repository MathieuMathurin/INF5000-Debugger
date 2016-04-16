package Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import views.MainWindow;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class AddWatchHandler implements EventHandler<ActionEvent> {
    MainWindow window;

    public  AddWatchHandler(MainWindow mw){
        this.window = mw;
    }

    @Override
    public void handle(ActionEvent event) {
        window.updateConsoleOutputText(window.getNewWatchTextAndReset());
        //Add new watch to watch list
    }
}
