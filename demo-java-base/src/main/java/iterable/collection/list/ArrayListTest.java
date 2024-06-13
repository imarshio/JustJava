package iterable.collection.list;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author masuo
 * @date: 2022/04/27/ 下午10:31
 * @description 底层是数组，可以动态的增加、删除元素，动态扩容等，
 * 默认初始容量10，超出上限会扩容至：原来容量的1.5倍
 * int newCapacity = oldCapacity + (oldCapacity >> 1);
 * 优点：按下标索引速度快(O(1))。
 * 缺点：插入删除会慢(O(n))。
 */
public class ArrayListTest {

    @Test
    public void test1() {
        ArrayList<Integer> list0 = new ArrayList<>();
        list0.add(1);
        list0.add(2);
        list0.add(3);
        list0.add(4);

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(7);
        // subList 区间左闭右开
        list1.addAll(list0.subList(0, 3));

        System.out.println(list1);
    }


}
