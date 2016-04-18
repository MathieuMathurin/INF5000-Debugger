package Handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import models.BreakPoint;
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
    public File file;

    public OpenHandler(MainWindow mw){
        this.window = mw;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.window.model.originalFileTextLines.clear();
        Boolean hasError = true;
        file = window.fileChooser.showOpenDialog(new Stage());
        int lines = 0;
        try {
            if(file != null){
                List<String> temp = Files.readAllLines(file.toPath(), Charset.defaultCharset());
                for(String line : temp){
                    ++lines;
                    if(line.trim().isEmpty()){
                        line = "<br>";
                    }
                    this.window.model.originalFileTextLines.add(line);
                }
                hasError = false;
            }
        } catch (IOException e) {
            file = null;
        }
        if(!hasError){
            //Enable buttons
            this.window.uIupdater.setFileText();
            this.window.model.args[0] = file.toString();
            initBreakPoints(lines);
            window.startBtn.setDisable(false);
        }
    }

    private void initBreakPoints(int lines){
        for(int i = 0; i < lines; ++i){
            //Creation de checkbox et ajout du controle
            window.breakpointsPane.getChildren().add(new BreakPoint(window, i + 1).box);
        }
    }
}
