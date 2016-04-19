/**
 * Created by Mathieu on 3/31/2016.
 */
package views;
import javafx.application.Application;
import javafx.stage.Stage;



public class Debugger extends Application {

    static String[] mainArgs;

    public static void main(String[] args) throws InterruptedException {
        mainArgs = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("Debugger");
        primaryStage.setScene(new MainWindow().init(mainArgs, primaryStage));
        primaryStage.show();
    }
}
