/**
 * Created by Mathieu on 3/31/2016.
 */
package Views;
import Controllers.*;
import Lib.*;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.concurrent.SynchronousQueue;

public class Debugger extends Application {

    String consoleOutputText = "";
    TextArea outputConsole;
    TextField variable;

    // Commands
    Start cmdStart;
    Continue cmdContinue;
    StepIn cmdStepIn;
    StepOver cmdStepOver;

    // Le thread est maintenu à jour dans les differentes commandes
    // Cet objet est soit null (Bouton Start n'a jamais ete appuyé), soit valide (le SEUL thread en vie et valide)
    InterpretorThread interpretorThread;

    public static void main(String[] args) throws InterruptedException {
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

        //Create buttons
        Button startBtn = new Button("Start");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateConsoleOutputText("Start");
                interpretorThread = cmdStart.execute(interpretorThread);
            }
        });


        Button continueBtn = new Button("Continue");
        continueBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateConsoleOutputText("Continue");
                if(interpretorThread != null)
                    cmdContinue.execute(interpretorThread.queue);
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
                if(interpretorThread != null) interpretorThread.interrupt();
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

        commandButtonsPanel.getChildren().addAll(startBtn, continueBtn, stepOverBtn, stepInBtn, stopBtn);

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
}
