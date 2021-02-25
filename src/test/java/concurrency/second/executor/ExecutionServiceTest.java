package concurrency.second.executor;

import java.util.List;
import concurrency.second.Randomizer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExecutionServiceTest {
    private Randomizer randomizer;
    private ExecutionService executionService;
    private List<Integer> list;

    @Before
    public void setUp() throws Exception {
        randomizer = new Randomizer();
        list = randomizer.getListOfNumber(1);
        executionService = new ExecutionService(list);
    }

    @Test
    public void getSum_ok() {
        Integer expected = list.stream().mapToInt(i -> i).sum();
        Integer actual = executionService.execute(list);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNegativeSum_ok() {
        List<Integer> listOfNumber = randomizer.getListOfNumber(-2);
        Integer expected = listOfNumber.stream().mapToInt(i -> i).sum();
        Integer actual = executionService.execute(listOfNumber);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSumOfRandomList() {
        List<Integer> listOfNumber = randomizer.getRandomList();
        Integer expected = listOfNumber.stream().mapToInt(i -> i).sum();
        Integer actual = executionService.execute(listOfNumber);
        Assert.assertEquals(expected, actual);
    }
}
