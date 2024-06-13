package dataStructure.linkedList;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author masuo
 * @data 2021/9/18 14:56
 * @Description 双向链表-继承Abstract
 */

public class TwoWayLinkedListPlus<E> extends AbstractSequentialList<E> {

    transient int size;

    transient Node<E> first;

    transient Node<E> last;

    @Override
    public boolean add(E item) {
        //
        linkedLast(item);
        return true;
    }

    // 在继承AbstractSequentialList类之后，由于
    public void add(int index, E item) {
        checkIndex(index);
        if (index == size) {
            linkedLast(item);
        } else {
            linkedBefore(index, item);
        }
    }

    private void linkedBefore(int index, E item) {
        Node<E> node = null;
        Node<E> newNode = new Node<>(null, item, null);
        if (index < (size >> 1)) {
            // index小于链表的一半大小，从前往后遍历
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            // index大于链表的一半，从后往前遍历
            node = last;
            for (int i = index; i > 0; i--) {
                node = node.pre;
            }
        }

        // 获取node之后，链接
        newNode.pre = node.pre;
        node.pre = newNode;
        newNode.next = node;
    }

    void linkedLast(E item) {
        Node<E> newNode = new Node<>(null, item, null);
        // 我的方法
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.pre = last;
        }
        last = newNode;

        // 源码方法
        // final Node<E> node = last;
        ////newNode
        // newNode = new Node<>(node,item,null);
        // last = newNode;
        // if(node == null)
        //    first = newNode;
        // else
        //    node.next = newNode;

        ++size;

    }

    public E get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new iterPlus(0);
    }

    class iterPlus implements Iterator<E>, ListIterator<E> {
        int nextIndex;
        Node<E> lastReturn;
        Node<E> next;

        public iterPlus(int index) {
            nextIndex = index;
            next = node(index);
        }


        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturn = next;
            next = next.next;
            ++nextIndex;
            return lastReturn.item;
        }

        @Override
        public boolean hasPrevious() {
            return next.pre != null;
        }

        @Override
        public E previous() {
            return next.pre.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return --nextIndex;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }

    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = index; i > 0; i--) {
                node = node.pre;
            }
            return node;
        }
    }

    private void checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {
        E item;
        Node<E> pre;
        Node<E> next;

        public Node(Node<E> pre, E item, Node<E> next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }
}
