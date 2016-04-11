/**
 * Created by ledrou_83 on 16-04-11.
 */
package Controllers;

import Lib.InterpretorThread;

public class Start{
    public InterpretorThread execute(InterpretorThread t) {
        if(t != null) t.interrupt();

        t = new InterpretorThread();
        t.setDaemon(true);
        t.start();

        return t;
    }
}
