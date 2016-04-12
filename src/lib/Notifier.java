package lib;

import javafx.scene.control.TextArea;

/**
 * Created by ledrou_83 on 16-04-12.
 */
public class Notifier {
    TextArea fileView;

    public Notifier(TextArea textArea) {
        fileView = textArea;
    }

    public void pushNotification(String s){
        fileView.setText(s);
    }
}
