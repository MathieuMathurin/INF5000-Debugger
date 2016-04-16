package models;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class ViewModel {
    public String consoleOutputText;
    public String[] args;
    public HashMap<Integer, Integer> breakpoints;
    public String fileText;
    public File file;

    public ViewModel(String[] args){
        this.consoleOutputText = "";
        this.args = args;
        this.breakpoints = new HashMap<>();
        this.fileText = "";
    }

}
