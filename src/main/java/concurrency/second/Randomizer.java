package concurrency.second;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Randomizer {
    private static final int MAX = 10;
    private static final int MIN = 0;

    public int getRandom() {
        Random random = new Random();
        return random.nextInt(MAX - MIN) + MIN;
    }

    public List<Integer> getRandomList() {
        Randomizer randomizer = new Randomizer();
        List<Integer> list = IntStream
                .range(0, 1_000_000)
                .map(i -> randomizer.getRandom())
                .boxed()
                .collect(Collectors.toList());
        return list;
    }

    public List<Integer> getListOfNumber(int number) {
        List<Integer> list = IntStream
                .range(0, 1_000_000)
                .map(i -> number)
                .boxed()
                .collect(Collectors.toList());
        return list;
    }
}
