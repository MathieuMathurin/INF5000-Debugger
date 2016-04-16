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
        window.model.fileText = "";
        file = window.fileChooser.showOpenDialog(new Stage());
        Boolean hasError = false;
        int lines = 0;
        try {
            if(file != null){
                List<String> temp = Files.readAllLines(file.toPath(), Charset.defaultCharset());
                for(String line : temp){
                    ++lines;
                    window.model.fileText = window.model.fileText.concat(line + "<br/>");
                }
            }
        } catch (IOException e) {
            file = null;
            hasError = true;
        }
        if(!hasError){
            //Enable buttons
            window.setFileText(window.model.fileText);
            window.initBreakPoints(lines);
        }
    }
}
