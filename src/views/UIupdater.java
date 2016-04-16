package views;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.collections.FXCollections;

import java.util.Iterator;
import java.util.Map;

import funlang.Frame;
import funlang.Value;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

/**
 * Created by ledrou_83 on 16-04-12.
 */
public class UIupdater {
    //    TextArea fileView;
    WebView fileView;
    TableView<UIPairComponent> localVariables;


    public UIupdater(WebView fileView, TableView<UIPairComponent> localVariables) {
        this.fileView = fileView;
        this.localVariables = localVariables;
    }

    public void pushNotification(Frame f){
        //updateLocalVariablesWindow(f.getVariables());

    }

    private void updateLocalVariablesWindow(Map<String, Value> variables) {
        ObservableList<UIPairComponent> observableVariables =  FXCollections.observableArrayList();

        Iterator it = variables.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            it.remove(); // avoids a ConcurrentModificationException

            observableVariables.add(new UIPairComponent(pair.getKey().toString(), pair.getValue().toString()));
        }

//        localVariables.setItems(observableVariables);
    }
}
