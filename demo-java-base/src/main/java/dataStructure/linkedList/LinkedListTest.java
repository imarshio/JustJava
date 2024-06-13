package dataStructure.linkedList;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author masuo
 * @data 2021/9/18 15:08
 * @Description 链表测试
 */

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedListTest llt = new LinkedListTest();
        llt.onewayTest();
        llt.twowayTest();
        llt.test("", "1");
        llt.test("00", null);
    }

    public void test(String str, String i) {
        System.out.println(str + i);
    }

    @Test
    public void twowayTest() {

        TwoWayLinkedList<Integer> twoWayLinkedList = new TwoWayLinkedList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            twoWayLinkedList.add(i);
            integers.add(i);
            // 次 100 0000  10000000     100000000
            // 我    46      8141        OOM
            // 源    43      8049        OOM
            // linked 48      7797
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // System.out.println(integers.get(100));
        System.out.println(twoWayLinkedList.get(1));

        twoWayLinkedList.add(5, 50);

        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    @Test
    public void onewayTest() {

        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);

        ll.add(5, 10);

        ll.remove(1);

        System.out.println(ll);

        OneWayLinkedList<Integer> oneWay = new OneWayLinkedList<>();
        oneWay.add(1);
        oneWay.add(2);
        oneWay.add(3);

        oneWay.add(2, 5);
        System.out.println(oneWay.get(1));
        System.out.println(oneWay);

        // 上面两种的不同在于一个显式的是数组，一个显示的是详细信息，
        // 需要继承AbstractSequentialList来实现第一种效果，
        // 继承AbstractSequentialList就需要重写listIterator方法，
        // 这个方法需要新建类实现ListIterator接口，重写内部方法
    }
}
