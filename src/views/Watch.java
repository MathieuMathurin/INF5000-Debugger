package views;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Mathieu on 4/17/2016.
 */
public class Watch {
    private SimpleStringProperty variable;
    private SimpleStringProperty value;

    public Watch(String variable){
        this.variable = new SimpleStringProperty(variable);
        this.value = new SimpleStringProperty("");
    }

    public String getVariable(){ return this.variable.get(); }

    public String getValue(){
        return this.value.get();
    }

    public void setValue(String value){ this.value = new SimpleStringProperty(value); }
}
