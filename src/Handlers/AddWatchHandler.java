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
        window.updateConsoleOutputText(getNewWatchTextAndReset());
        //Add new watch to watch list
    }

    private String getNewWatchTextAndReset(){
        String text = window.variable.getText();
        window.variable.setText("");
        return text;
    }
}
