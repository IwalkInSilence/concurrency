package concurrency;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ClassExtendThread classExtendThread = new ClassExtendThread(counter);
        ClassImplementRunnable classImplementRunnable = new ClassImplementRunnable(counter);
        classExtendThread.start();
        new Thread(classImplementRunnable).start();
    }
}
