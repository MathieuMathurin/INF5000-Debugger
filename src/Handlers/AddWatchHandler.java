package Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import views.MainWindow;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;

import java.io.IOException;

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

    }
}
