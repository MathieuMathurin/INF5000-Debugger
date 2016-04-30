package models;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mathieu on 4/13/2016.
 */
public class ViewModel {
    public String consoleOutputText;
    public String[] args;
    public HashMap<Integer, Integer> breakpoints;
    public ArrayList<String> originalFileTextLines;
    public File file;
    public String preFileTextHtml;
    public String postFileTextHtml;
    public String preLineHtml;
    public String postLineHtml;
    public String preHighlightHtml;
    public String postHighlightHtml;
    public ArrayList<String> watches;

    public ViewModel(String[] args){
        this.consoleOutputText = "";
        this.args = new String[1];
        this.breakpoints = new HashMap<>();
        this.originalFileTextLines = new ArrayList<>();
        this.preFileTextHtml = "<!DOCTYPE html><html><head><style>body{font-size:20px; text-align: left;}span{background-color: yellow;}div{white-space: nowrap}</style></head><body>";
        this.postFileTextHtml = "</body></html>";
        this.preLineHtml = "<div>";
        this.postLineHtml = "</div>";
        this.preHighlightHtml = "<span>";
        this.postHighlightHtml = "</span>";
        this.watches =  new ArrayList<>();
    }

}
