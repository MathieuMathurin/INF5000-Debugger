/**
 * Created by Mathieu on 4/4/2016.
 */

package controllers;

import lib.Observer;

public class StepOver {
    public void execute(Observer observer) {
        observer.sendStepOver();
    }
}
