package Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import views.MainWindow;

import java.io.File;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class OpenHandler implements EventHandler<ActionEvent>{

    MainWindow window;
    File file;

    public OpenHandler(MainWindow mw){
        this.window = mw;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        file = window.fileChooser.showOpenDialog(new Stage());
        if(window.model.file != null) {
            //Enable Start button
        }
    }

    public File getFile(){
        return file;
    }
}
