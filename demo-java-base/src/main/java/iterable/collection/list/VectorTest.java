package iterable.collection.list;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author masuo
 * @date: 2022/04/27/ 下午10:32
 * @description 向量 底层是数组的，对一些操作数据的方法加了synchronized，可以看做是ArrayList一个线程安全的版本
 * 优点：线程安全的。
 * 缺点：效率慢，同步必然的必然结果。
 */
public class VectorTest {

    @Test
    public void test() {
        // 可以消除泛型，不消除泛型可以存储多种数据类型，建议消除泛型
        // Vector<Integer> v = new Vector<>(3, 2);
        Vector<Object> v = new Vector<>(3, 2);
        System.out.println("Initial size: " + 0);
        System.out.println("Initial capacity: " +
                v.capacity());
        v.addElement(1);
        v.addElement(2);
        v.addElement(3);
        v.addElement(4);
        System.out.println("Capacity after four additions: " +
                v.capacity());

        v.addElement(5.45);
        System.out.println("Current capacity: " +
                v.capacity());
        v.addElement(6.08);
        v.addElement(7);
        System.out.println("Current capacity: " +
                v.capacity());
        v.addElement(9.4F);
        v.addElement(10);
        System.out.println("Current capacity: " +
                v.capacity());
        v.addElement(11);
        v.addElement(12);
        System.out.println("First element: " +
                v.firstElement());
        System.out.println("Last element: " +
                v.lastElement());
        if (v.contains(3))
            System.out.println("Vector contains 3.");
        // 向量的遍历
        Enumeration<Object> vEnum = v.elements();
        System.out.println("\nElements in vector:");
        while (vEnum.hasMoreElements())
            System.out.print(vEnum.nextElement() + " ");
        System.out.println();
    }
}
