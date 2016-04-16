/**
 * Created by Mathieu on 3/31/2016.
 */
package views;
import controllers.*;
import javafx.stage.FileChooser;
import lib.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;

public class Debugger extends Application {

    static String[] mainArgs;

    public static void main(String[] args) throws InterruptedException {
        mainArgs = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("Debugger");
        primaryStage.setScene(new MainWindow().init(mainArgs));
        primaryStage.show();
    }
}
