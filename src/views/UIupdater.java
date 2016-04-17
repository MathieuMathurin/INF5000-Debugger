package views;

import javafx.application.Platform;
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

    MainWindow window;


    public UIupdater(MainWindow window) {
        this.window = window;
    }

    public void pushNotification(Frame f){
        updateLocalVariablesWindow(f.getVariables());
    }

    private void updateLocalVariablesWindow(Map<String, Value> variables) {
        ObservableList<UIPairComponent> observableVariables =  FXCollections.observableArrayList();

        Iterator it = variables.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            it.remove(); // avoids a ConcurrentModificationException

            observableVariables.add(new UIPairComponent(pair.getKey().toString(), pair.getValue().toString()));
        }

        this.window.localVariablesView.setItems(observableVariables);
    }

    public void setFileText(){
        String text = "";
        text = text.concat(this.window.model.preFileTextHtml);

        for(String line : this.window.model.originalFileTextLines){
            text = text.concat(this.window.model.preLineHtml + line + this.window.model.postLineHtml);
        }

        text = text.concat(this.window.model.postFileTextHtml);

        this.window.setFileText(text);
    }

    public void updateFileText(int lineIndex, String subString){
        int currentLine = 1;

        String text = "";
        text = text.concat(this.window.model.preFileTextHtml);

        for(String line : this.window.model.originalFileTextLines){
            if(currentLine == lineIndex){
//                String newLine = line.replace(subString, this.window.model.preHighlightHtml + subString + this.window.model.postHighlightHtml);
                text = text.concat(this.window.model.preLineHtml + this.window.model.preHighlightHtml + line + this.window.model.postHighlightHtml + this.window.model.postLineHtml);
            }else{
                text = text.concat(this.window.model.preLineHtml + line + this.window.model.postLineHtml);
            }
            ++currentLine;
        }

        text = text.concat(this.window.model.postFileTextHtml);

        final String finalText = text;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                window.setFileText(finalText);
            }
        });
    }
}
