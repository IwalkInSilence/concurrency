package concurrency;

public class Main {
    public static void main(String[] args) {
        ClassExtendThread classExtendThread = new ClassExtendThread();
        ClassImplementRunnable classImplementRunnable = new ClassImplementRunnable();
        classExtendThread.start();
        new Thread(classImplementRunnable).start();
    }
}
