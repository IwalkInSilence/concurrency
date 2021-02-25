package concurrency.second.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.collections4.ListUtils;

public class ExecutionService {
    public static final int THEADS = 12;
    private List<Integer> list;

    public ExecutionService(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> getList() {
        return list;
    }

    public Integer execute(List<Integer> list) {
        List<List<Integer>> partition = ListUtils.partition(list, list.size() / THEADS);
        List<ClassImplementsCallable> callableList = new ArrayList<>();
        for (List<Integer> sublist: partition) {
            callableList.add(new ClassImplementsCallable(sublist));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THEADS);
        int result = 0;
        try {
            executorService.invokeAll(callableList);
            for (ClassImplementsCallable thread: callableList) {
                result += executorService.submit(thread).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }
        return result;
    }
}
