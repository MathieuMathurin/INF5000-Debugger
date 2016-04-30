package Handlers;

import lib.Observer;
import views.MainWindow;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

/**
 * Created by ledrou_83 on 16-04-17.
 */
public class StdinSubmitHandler implements EventHandler<ActionEvent>{
    MainWindow window;
    TextField textField;

    public StdinSubmitHandler(MainWindow w, TextField t){
        this.window = w;
        this.textField = t;
    }

    @Override
    public void handle(ActionEvent event) {
        window.interpretorRunnable.observer.sendStdInEntry(textField.getText());

        window.primaryStage.setScene(window.mainScene);
        window.primaryStage.show();
    }
}
