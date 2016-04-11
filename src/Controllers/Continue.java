/**
 * Created by Mathieu on 4/4/2016.
 */
package Controllers;

import Lib.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Continue {
    public void execute(Observer observer) {
        System.out.println("In continue command, sending data in the queue");

        //TEST
        observer.update();
    }
}
