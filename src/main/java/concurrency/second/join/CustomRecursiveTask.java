package concurrency.second.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private static final int SUB_LIST_SIZE = 100;
    private static final int THRESHOLD = 100_000;
    private List<Integer> list;

    public CustomRecursiveTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer compute() {
        if (list.size() > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks(list)).stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }
        return sum();
    }

    private Collection<CustomRecursiveTask> createSubtasks(List<Integer> list) {
        List<CustomRecursiveTask> tasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += SUB_LIST_SIZE) {
            tasks.add(new CustomRecursiveTask(new ArrayList<>(list.subList(i,
                    Math.min(list.size(), i + SUB_LIST_SIZE)))));
        }
        return tasks;
    }

    private Integer sum() {
        return list.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
