package concurrency;

import org.apache.log4j.Logger;

public class ClassImplementRunnable implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(ClassExtendThread.class);

    @Override
    public void run() {
        int value = 0;
        for (int i = 0; value <= 100; i++) {
            value = Counter.getCount() + 1;
            Counter.setCount(value);
            LOGGER.info(Thread.currentThread().getName() + " value = " + value);
        }
    }
}
