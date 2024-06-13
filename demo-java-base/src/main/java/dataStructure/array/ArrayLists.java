package dataStructure.array;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 2021/9/27 12:43
 * @Description 尽可能实现 ArrayList
 */

public class ArrayLists {

    @Test
    public void test() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.forEach(i -> System.out.print(i + " "));
    }
}
