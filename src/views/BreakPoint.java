package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

/**
 * Created by Mathieu on 4/14/2016.
 */
public class BreakPoint {
    public CheckBox box;
    int line;
    MainWindow window;

    public BreakPoint(MainWindow mw, final int line){
        this.line = line;
        this.window = mw;
        this.box = new CheckBox();
        this.box.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(box.isSelected()){
                    window.model.breakpoints.put(line, line);
                }else{
                    window.model.breakpoints.remove(line);
                }
            }
        });
        this.box.setText(new Integer(line).toString());
    }
}
