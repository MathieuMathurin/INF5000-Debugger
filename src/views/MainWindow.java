package views;

import Handlers.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lib.InterpretorRunnable;
import models.ViewModel;

import java.io.File;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class MainWindow {

    // test
    // TODO si on change des valeurs locales au runtime, on doit faire la verif de TYPES


    public InterpretorRunnable interpretorRunnable;
    public ViewModel model;
    public UIupdater uIupdater;
    //GUI Scene
    private Scene scene;

    //Layouts
    public BorderPane mainPanel;
    public HBox centerPane;
    public VBox rightPane;
    public HBox commandButtonsPanel;
    public VBox breakpointsPane;
    public HBox newLinePane;

    //Controls
    public FileChooser fileChooser;
    public TextArea outputConsole;
    //    private TextArea fileView;
    public TableView<UIPairComponent> localVariablesView;
    public TextField variable;
    public WebView fileView;

    //Buttons
    public Button openBtn;
    public Button startBtn;
    public Button continueBtn;
    public Button stepOverBtn;
    public Button stepInBtn;
    public Button stepOutBtn;
    public Button stopBtn;
    public Button addWatchBtn;

    public Scene init(final String[] args){
        model = new ViewModel(args);
        uIupdater = new UIupdater(this);
        initButtons();

        //init breakpoints
        breakpointsPane = new VBox();
        breakpointsPane.setPrefWidth(350);


        //init file chooser
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Funlang files (*.funlang)", "*.funlang");
        fileChooser.getExtensionFilters().add(extentionFilter);
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);

        //init File view
        fileView = new WebView();

        outputConsole = new TextArea();
        outputConsole.setEditable(false);
        variable = new TextField();

        //init local variable
        initLocalVariablesTable();

        //init layout
        mainPanel = new BorderPane();
        centerPane = new HBox();
        rightPane = new VBox();
        rightPane.setSpacing(10);

        //init panes
        commandButtonsPanel = new HBox();
        commandButtonsPanel.setPadding(new Insets(15, 12, 15, 12));
        commandButtonsPanel.setSpacing(10);
        commandButtonsPanel.getChildren().addAll(openBtn, startBtn, continueBtn, stepOverBtn, stepInBtn, stepOutBtn, stopBtn);
        newLinePane = new HBox();

        //AddItems to Panes
        centerPane.getChildren().addAll(breakpointsPane, fileView);
        newLinePane.getChildren().addAll(variable, addWatchBtn);
        rightPane.getChildren().addAll(localVariablesView, newLinePane);


//        fileView.prefWidthProperty().bind(centerPane.widthProperty());
//        fileView.prefHeightProperty().bind(centerPane.heightProperty());
        mainPanel.setTop(commandButtonsPanel);
        mainPanel.setCenter(centerPane);
        mainPanel.setRight(rightPane);
        mainPanel.setBottom(outputConsole);

        scene = new Scene(mainPanel, 1000, 1500 );
        scene.getStylesheets().add(this.getClass()
                .getResource("checkBox.css").toExternalForm());
        initButtonsHandlers();
        return scene;
    }

    public void updateConsoleOutputText(String text){
        outputConsole.appendText("\n" + text);
    }

    private void initButtons(){
        openBtn = new Button("Open");
        startBtn = new Button("Start");
        startBtn.setDisable(true);
        continueBtn = new Button("Continue");
        continueBtn.setDisable(true);
        stepOverBtn = new Button("Step Over");
        stepOverBtn.setDisable(true);
        stepInBtn = new Button("Step In");
        stepInBtn.setDisable(true);
        stepOutBtn = new Button("Step Out");
        stepOutBtn.setDisable(true);
        stopBtn = new Button("Stop");
        stopBtn.setDisable(true);
        addWatchBtn = new Button(" + ");
        addWatchBtn.setDisable(true);
    }

    private void initButtonsHandlers(){
        openBtn.setOnAction(new OpenHandler(this));
        startBtn.setOnAction(new StartHandler(this));
        continueBtn.setOnAction(new ContinueHandler(this));
        stepOverBtn.setOnAction(new StepOverHandler(this));
        stepInBtn.setOnAction(new StepInHandler(this));
        stepOutBtn.setOnAction(new StepOutHandler(this));
        stopBtn.setOnAction(new StopHandler(this));
        addWatchBtn.setOnAction(new AddWatchHandler(this));
    }

    private void initLocalVariablesTable(){
        localVariablesView = new TableView<UIPairComponent>();
        localVariablesView.setEditable(true);
        TableColumn localVariablesColumnName= new TableColumn("Name");
        localVariablesColumnName.prefWidthProperty().bind(localVariablesView.widthProperty().divide(2));
        localVariablesColumnName.setCellValueFactory(new PropertyValueFactory<UIPairComponent, String>("variable"));
        localVariablesColumnName.setEditable(true);

        TableColumn localVariablesColumnValue = new TableColumn("Value");
        localVariablesColumnValue.setCellValueFactory(new PropertyValueFactory<UIPairComponent, String>("value"));
        localVariablesColumnValue.prefWidthProperty().bind(localVariablesView.widthProperty().divide(2));
        localVariablesColumnValue.setEditable(false);
        localVariablesView.getColumns().addAll(localVariablesColumnName, localVariablesColumnValue);
    }

      public void setFileText(String text){
        this.fileView.getEngine().loadContent(text);
    }

}