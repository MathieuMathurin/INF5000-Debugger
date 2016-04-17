package Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import views.MainWindow;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

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
        Boolean hasError = false;
        int lines = 1;
        try {
            if(file != null){
                List<String> temp = Files.readAllLines(file.toPath(), Charset.defaultCharset());
                for(String line : temp){
                    ++lines;
                    this.window.model.originalFileTextLines.add(line);
                }
            }
        } catch (IOException e) {
            file = null;
            hasError = true;
        }
        if(!hasError){
            //Enable buttons
            this.window.uIupdater.setFileText();
            window.initBreakPoints(lines);
        }
    }
}
