/**
 * Created by Mathieu on 3/31/2016.
 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.concurrent.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.Semaphore;

public class Debugger extends Application {

    String consoleOutputText = "";
    TextArea outputConsole;
    TextField variable;
    Boolean threadSuspende = true;

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        final CustomTask ct = new CustomTask();
        ct.lock();
        Task t = ct.getTask();

        Thread th = new Thread(t);
        th.setDaemon(true);
        th.start();

        String mockContent = "Collaboratively administrate empowered markets via plug-and-play networks. Dynamically procrastinate B2C users after installed base benefits. Dramatically visualize customer directed convergence without revolutionary ROI.\n" +
                "\n" +
                "Efficiently unleash cross-media information without cross-media value. Quickly maximize timely deliverables for real-time schemas. Dramatically maintain clicks-and-mortar solutions without functional solutions.\n" +
                "\n" +
                "Completely synergize resource taxing relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. Dynamically innovate resource-leveling customer service for state of the art customer service\n";


        ObservableList<Watch> data =
                FXCollections.observableArrayList(
                        new Watch("1+2", "3"),
                        new Watch("isTrue", "True")
                );


        BorderPane mainPanel = new BorderPane();

        HBox commandButtonsPanel = new HBox();
        commandButtonsPanel.setPadding(new Insets(15, 12, 15, 12));
        commandButtonsPanel.setSpacing(10);

        VBox rightPane = new VBox();
        rightPane.setSpacing(10);

/*
    Declaration des boutons
 */
        Button continueBtn = new Button("Continue");
        continueBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateConsoleOutputText("Continue");
                //Calls Command continue.execute();
                ct.unlock();
            }
        });

        Button stepOverBtn = new Button("Step Over");
        stepOverBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateConsoleOutputText("Step Over");
                //Calls Command stepOver.execute();
            }
        });

        Button stepInBtn = new Button("Step In");
        stepInBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateConsoleOutputText("Step In");
                //Calls Command stepIn.execute();
            }
        });

        Button stopBtn = new Button("Stop");
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateConsoleOutputText("Stop");
                //Calls Command stop.execute();
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

        commandButtonsPanel.getChildren().addAll(continueBtn, stepOverBtn, stepInBtn, stopBtn);

        TextArea fileView = new TextArea(mockContent);
        fileView.setEditable(false);

        outputConsole = new TextArea();
        outputConsole.setEditable(false);
        outputConsole.setText(consoleOutputText);


        TableView<Watch> watches = new TableView<Watch>();
        watches.setEditable(true);
        TableColumn watchVariable = new TableColumn("Variables");
        watchVariable.prefWidthProperty().bind(watches.widthProperty().divide(2));
        watchVariable.setCellValueFactory(new PropertyValueFactory<Watch,String>("variable"));
        watchVariable.setEditable(true);

        TableColumn watchValues = new TableColumn("Values");
        watchValues.setCellValueFactory(new PropertyValueFactory<Watch,String>("value"));
        watchValues.prefWidthProperty().bind(watches.widthProperty().divide(2));
        watchValues.setEditable(false);

        watches.setItems(data);

        watches.getColumns().addAll(watchVariable, watchValues);

        HBox newLine = new HBox();


        variable = new TextField();

        newLine.getChildren().addAll(variable, addWatchBtn);

        rightPane.getChildren().addAll(watches, newLine);

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

    public class Watch{
        private SimpleStringProperty variable;
        private SimpleStringProperty value;

        public Watch(String variable, String value){
            this.variable = new SimpleStringProperty(variable);
            this.value = new SimpleStringProperty(value);
        }

        public String getVariable(){
            return this.variable.get();
        }

        public String getValue(){
            return this.value.get();
        }
    }

    public class CustomTask{
        Semaphore lock = new Semaphore(1);
        public void lock(){
            try{
                lock.acquire();
            }catch(InterruptedException e){

            }
        }

        public void unlock(){
            lock.release();
        }

        private Task t = new Task() {
            @Override
            protected Object call() throws Exception {
                lock.acquire();
                System.out.println("Unlocked");
                return this;
            }
        };

        public Task getTask(){
            return t;
        }
    }
}
