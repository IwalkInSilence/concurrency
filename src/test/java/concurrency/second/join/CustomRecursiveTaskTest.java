package concurrency.second.join;

import java.util.List;
import concurrency.second.Randomizer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomRecursiveTaskTest {
    private Randomizer randomizer;
    private CustomRecursiveTask customRecursiveTask;

    @Before
    public void setUp() throws Exception {
        randomizer = new Randomizer();
    }

    @Test
    public void getSum_ok() {
        List<Integer> listOfNumber = randomizer.getListOfNumber(5);
        Integer expected = listOfNumber.stream().mapToInt(i -> i).sum();
        customRecursiveTask = new CustomRecursiveTask(listOfNumber);
        Integer actual = customRecursiveTask.compute();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNegativeSum_ok() {
        List<Integer> listOfNumber = randomizer.getListOfNumber(-2);
        Integer expected = listOfNumber.stream().mapToInt(i -> i).sum();
        customRecursiveTask = new CustomRecursiveTask(listOfNumber);
        Integer actual = customRecursiveTask.compute();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSumOfRandomList() {
        List<Integer> listOfNumber = randomizer.getRandomList();
        Integer expected = listOfNumber.stream().mapToInt(i -> i).sum();
        customRecursiveTask = new CustomRecursiveTask(listOfNumber);
        Integer actual = customRecursiveTask.compute();
        Assert.assertEquals(expected, actual);
    }
}
