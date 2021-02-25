package concurrency.second.executor;

import java.util.List;
import java.util.concurrent.Callable;

public class ClassImplementsCallable implements Callable<Integer> {
    private List<Integer> list;

    public ClassImplementsCallable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        return list.stream().mapToInt(i -> i).sum();
    }
}
