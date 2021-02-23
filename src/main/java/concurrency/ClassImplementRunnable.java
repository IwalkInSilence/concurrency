package concurrency;

import org.apache.log4j.Logger;

public class ClassImplementRunnable implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(ClassExtendThread.class);
    private static int MAX_COUNTER = 100;
    private Counter counter;

    public ClassImplementRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; counter.getCount() <= MAX_COUNTER; i++) {
            counter.increment();
            LOGGER.info(Thread.currentThread().getName() + " value = " + counter.getCount());
        }
    }
}
