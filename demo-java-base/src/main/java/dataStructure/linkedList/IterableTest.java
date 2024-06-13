package dataStructure.linkedList;

import org.junit.Test;

import java.util.Iterator;

/**
 * @author masuo
 * @data 2021/9/23 15:31
 * @Description TODO
 */

public class IterableTest {
    public static void main(String[] args) {
        IterableTest it = new IterableTest();
        it.test();
    }

    @Test
    public void test() {

        TestB<Integer> tb = new TestB<>();
        tb.add(1);
        tb.add(2);
        tb.add(3);
        tb.add(4);
        tb.add(5);

        Iterator<Integer> iterator = tb.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class TestB<E> implements Iterable<E> {

    transient int size;

    transient Node<E> first;

    transient Node<E> last;


    // 实现 Iterable 只需要重写他的 iterator()方法，
    // 比如:public interface Collection<E> extends Iterable<E>,
    // ** 在这里接口之间继承使用extends，类实现接口需要使用implements
    //
    @Override
    public Iterator<E> iterator() {
        return new testIter(0);
    }

    class testIter implements Iterator<E> {

        transient int nextIndex;
        transient Node<E> next;
        transient Node<E> lastReturn;

        public testIter(int index) {
            next = node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkIndex(nextIndex);

            lastReturn = next;
            next = next.next;
            nextIndex++;
            return lastReturn.item;
        }
    }

    Node<E> node(int index) {
        checkIndex(index);
        Node<E> node = first;
        for (int i = index; i > 0; i--) {
            node = node.next;
        }
        return node;
    }

    private void checkIndex(int currentIndex) {
        if (size < currentIndex) {
            throw new IndexOutOfBoundsException(currentIndex + "");
        }
    }

    public boolean add(E item) {
        Node<E> node = new Node<>(item);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        ++size;
        return true;
    }

    static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

    }
}