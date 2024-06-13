import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author masuo
 * @data 16/3/2022 下午2:28
 * @Description Arrays使用
 */

public class ArraysAPI {

    @Test
    public void test1() {
        int[] numbers = {45, 4, 9, 16, 25};

        List<Integer> integers = Arrays.asList(45, 4, 9, 16, 25);
        List<Integer> nums = new ArrayList<>();

        nums.add(5);
        nums.add(6);
        nums.add(7);

        nums.forEach(i -> {
            i = i * 10;
            System.out.println(i);
        });

        System.out.println(nums);

        Stream<Object> objectStream = nums.stream().map((Integer) -> {
            return null;
        });

    }

    private Consumer<Integer> add() {
        return null;
    }


}
