package threads.task_05.ex_02;

/**
 * Philosopher with id 5 takes the right fork first.
 *
 * @author yuzo
 */
import java.util.concurrent.Semaphore;

public class DiningPhilosophersV1 implements Thinker {

    int n = 0;
    Semaphore[] fork = null;

    public DiningPhilosophersV1(int initN) {
        n = initN;
        fork = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            fork[i] = new Semaphore(1);
        }
    }

    @Override
    public void pickup(int i) throws Exception {
        if (i == n - 1) {
            fork[(i + 1) % n].acquire();
            fork[i].acquire();
        } else {
            fork[i].acquire();
            fork[(i + 1) % n].acquire();
        }
    }

    @Override
    public void drop(int i) throws Exception {
        fork[i].release();
        fork[(i + 1) % n].release();
    }
}
