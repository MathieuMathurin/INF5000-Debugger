/**
 * Created by Mathieu on 4/4/2016.
 */
package controllers;

import lib.*;

public class Continue {
    public void execute(Observer observer, int breakpoint) {
        System.out.println("In continue command, sending data in the queue");

        observer.updateContinue(breakpoint);
    }
}
