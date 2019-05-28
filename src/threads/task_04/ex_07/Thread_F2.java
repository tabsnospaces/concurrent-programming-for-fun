package threads.task_04.ex_07;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class Thread_F2 implements Runnable {
    
    Queue q;
    
    public Thread_F2(Queue q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            q.F2();
        } catch (Exception ex) {
            Logger.getLogger(Thread_F2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
