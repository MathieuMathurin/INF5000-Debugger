/**
 * Created by Mathieu on 4/4/2016.
 */

package Controllers;

import java.util.concurrent.SynchronousQueue;

public class Continue {
    public void execute(SynchronousQueue<String> queue) {
        System.out.println("In continue command, sending data in the queue");

        try {
            queue.put("Bonjour!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
