/**
 * Created by ledrou_83 on 16-04-10.
 */
package Lib;

import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.collections.ObservableList;

public class InterpretorService extends Service<String> {
    @Override protected Task createTask() {
        return new Task<ObservableList<String>>() {
            @Override protected ObservableList<String> call() throws InterruptedException {
                updateMessage("Starting the Interpretor...");

                // Start the intepretor program
                // Thread.sleep(3000) // TEST

                return null; // TODO return an observable
            }
        };
    }
}
