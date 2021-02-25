package concurrency.second.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.collections4.ListUtils;

public class ExecutionService {
    private static final int THEADS = 12;
    private List<Integer> list;

    public ExecutionService(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> getList() {
        return list;
    }

    public Integer execute(List<Integer> list) {
        List<List<Integer>> partition = ListUtils.partition(list, list.size() / THEADS);
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (List<Integer> sublist: partition) {
            tasks.add(new SumCalculatorCallable(sublist));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THEADS);
        int result = 0;
        try {
            List<Future<Integer>> futures = executorService.invokeAll(tasks);
            for (Future<Integer> future: futures) {
                result += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("ExecutionService don't work correct", e);
        }
        executorService.shutdownNow();
        return result;
    }
}
