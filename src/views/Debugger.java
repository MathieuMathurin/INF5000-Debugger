/**
 * Created by Mathieu on 3/31/2016.
 */
package views;
import controllers.*;
import lib.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Debugger extends Application {

    // test
    int breakpoint = 17;
    //test

    static String[] mainArgs;

    String consoleOutputText = "";
    TextArea outputConsole;
    TextArea fileView;
    TextField variable;

    // Commands
    Start cmdStart;
    Continue cmdContinue;
    StepIn cmdStepIn;
    StepOver cmdStepOver;

    InterpretorRunnable interpretorRunnable;

    public static void main(String[] args) throws InterruptedException {
        mainArgs = args;
        launch(args);
    }

    public void initCommands() {
        cmdStart = new Start();
        cmdContinue = new Continue();
        cmdStepIn = new StepIn();
        cmdStepOver = new StepOver();
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        initCommands();

        //Create buttons
        Button startBtn = new Button("Start");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (interpretorRunnable ==  null || interpretorRunnable.observer.runnableHasEnded()){
                    interpretorRunnable = cmdStart.execute(mainArgs, breakpoint, fileView);
                }
            }
        });

        Button continueBtn = new Button("Continue");
        continueBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (interpretorRunnable != null)
                    cmdContinue.execute(interpretorRunnable.observer, breakpoint);
            }
        });

        Button stepOverBtn = new Button("Step Over");
        stepOverBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cmdStepOver.execute(interpretorRunnable.observer);
            }
        });

        Button stepInBtn = new Button("Step In");
        stepInBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //Calls Command stepIn.execute();
            }
        });

        Button stopBtn = new Button("Stop");
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                interpretorRunnable = null; // Ne jamais relancer un thread...
            }
        });

        Button addWatchBtn = new Button(" + ");
        addWatchBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateConsoleOutputText(getNewWatchTextAndReset());
                //Add new watch to watch list
            }
        });

        BorderPane mainPanel = new BorderPane();
        HBox commandButtonsPanel = new HBox();
        commandButtonsPanel.setPadding(new Insets(15, 12, 15, 12));
        commandButtonsPanel.setSpacing(10);
        VBox rightPane = new VBox();
        rightPane.setSpacing(10);
        commandButtonsPanel.getChildren().addAll(startBtn, continueBtn, stepOverBtn, stepInBtn, stopBtn);
        fileView = new TextArea();
        fileView.setEditable(false);
        outputConsole = new TextArea();
        outputConsole.setEditable(false);
        outputConsole.setText(consoleOutputText);
        HBox newLine = new HBox();
        variable = new TextField();
        newLine.getChildren().addAll(variable, addWatchBtn);
        mainPanel.setTop(commandButtonsPanel);
        mainPanel.setCenter(fileView);
        mainPanel.setRight(rightPane);
        mainPanel.setBottom(outputConsole);
        Scene scene = new Scene(mainPanel, 1000, 1500 );
        primaryStage.setTitle("Debugger");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getNewWatchTextAndReset(){
        String text = variable.getText();
        variable.setText("");
        return text;
    }

    private void updateConsoleOutputText(String text){
        outputConsole.appendText("\n" + text);
    }
}
