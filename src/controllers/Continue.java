/**
 * Created by Mathieu on 4/4/2016.
 */
package controllers;

import lib.*;

import java.util.HashMap;

public class Continue {
    public void execute(Observer observer, HashMap<Integer, Integer> breakpoints) {
        observer.sendContinue(breakpoints);
    }
}
