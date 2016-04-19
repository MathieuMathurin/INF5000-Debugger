package models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ledrou_83 on 16-04-13.
 */
public class UIPairComponent {
    private SimpleStringProperty variable;
    private SimpleStringProperty value;

    public UIPairComponent(String variable, String value){
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
