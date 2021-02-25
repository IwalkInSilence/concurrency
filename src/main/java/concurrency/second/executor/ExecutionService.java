package concurrency.second.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        List<SumCalculatorCallable> callableList = new ArrayList<>();
        for (List<Integer> sublist: partition) {
            callableList.add(new SumCalculatorCallable(sublist));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THEADS);
        int result = 0;
        try {
            executorService.invokeAll(callableList);
            for (SumCalculatorCallable thread: callableList) {
                result += executorService.submit(thread).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
        return result;
    }
}
