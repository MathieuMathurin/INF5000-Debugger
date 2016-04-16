package models;

import views.BreakPoint;

/**
 * Created by Mathieu on 4/15/2016.
 */
public class ProgramLine {
    BreakPoint b;
    int line;
    String text;

    public ProgramLine(BreakPoint b, int line, String t){
        this.b = b;
        this.line = line;
        this.text = t;
    }
}
