package iterable.collection.set;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author masuo
 * @data 28/4/2022 下午5:32
 * @Description 基于HashMap，只是只有key，没有v，在HashMap中，key是不重复的，所以HashSet可以去重
 */

public class HashSetTest {

    @Test
    public void test() {
        HashSet<Integer> integerHashSet = new HashSet<>(10);
        integerHashSet.add(1);
        integerHashSet.add(1);
        integerHashSet.add(1);
        integerHashSet.add(1);
        integerHashSet.forEach(integer -> {
            System.out.println(integer + " ");
        });
    }
}
