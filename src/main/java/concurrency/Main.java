package concurrency;

import concurrency.second.Randomizer;
import concurrency.second.executor.ExecutionService;
import concurrency.second.join.CustomRecursiveTask;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Counter counter = new Counter();
        ClassExtendThread classExtendThread = new ClassExtendThread(counter);
        ClassImplementRunnable classImplementRunnable = new ClassImplementRunnable(counter);
        classExtendThread.start();
        new Thread(classImplementRunnable).start();

        Randomizer randomizer = new Randomizer();
        List<Integer> listOfNumber = randomizer.getListOfNumber(2);
        ExecutionService exacutor = new ExecutionService(listOfNumber);
        System.out.println(exacutor.execute(listOfNumber));
        CustomRecursiveTask joinTask = new CustomRecursiveTask(listOfNumber);
        System.out.println(joinTask.compute());
    }
}
