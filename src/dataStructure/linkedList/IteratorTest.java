package dataStructure.linkedList;

import org.junit.Test;

import java.util.Iterator;

/**
 * @author masuo
 * @data 2021/9/23 11:34
 * @Description 迭代器测试
 */

public class IteratorTest {

    public static void main(String[] args) {
        IteratorTest it = new IteratorTest();
        it.test1();
    }

    @Test
    public void test1() {

        TestA<Integer> ia = new TestA<>();

        ia.add(1);
        ia.add(2);
        ia.add(3);
        ia.add(4);
        while (ia.hasNext()){
            System.out.println(ia.next());
        }
    }
}

class TestA<E> implements Iterator<E>{

    transient int size;

    transient Node<E> first;

    transient Node<E> last;

    int currentIndex;

    Node<E> next;

    //实现 Iterator 只需要继承它的 hashNext()方法 和 next()方法
    @Override
    public boolean hasNext() {
        return size > currentIndex;
    }

    @Override
    public E next() {
        checkIndex(++currentIndex);
        return next.item;
    }

    private E node(int index) {
        checkIndex(index);
        Node<E> node = first;
        for (int i = index; i > 0; i--) {
            node = node.next;
        }
        return node.item;
    }

    private void checkIndex(int currentIndex) {
        if(size<currentIndex){
            throw new IndexOutOfBoundsException(currentIndex+"");
        }
    }

    public boolean add(E item) {
        Node<E> node = new Node<>(item);
        if(first == null){
            first = node;
        }else {
            last.next = node;
        }
        last = node;
        ++size;
        return true;
    }

    static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item){
            this.item = item;
        }

    }
}


