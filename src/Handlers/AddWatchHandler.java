package Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.MainWindow;
import models.Watch;

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
        Watch newWatch = new Watch(getNewWatchTextAndReset());
        this.window.model.watches.add(newWatch.getVariable());
        newWatch.setValue("null");
        this.window.watchView.getItems().add(newWatch);
    }

    private String getNewWatchTextAndReset(){
        String text = window.variable.getText();
        window.variable.setText("");
        return text;
    }
}
