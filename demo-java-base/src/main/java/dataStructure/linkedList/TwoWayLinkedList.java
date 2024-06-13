package dataStructure.linkedList;

/**
 * @author masuo
 * @data 2021/9/18 14:56
 * @Description 双向链表
 */

public class TwoWayLinkedList<E> {

    transient int size;

    transient Node<E> first;

    transient Node<E> last;

    public boolean add(E item) {
        //
        linkedLast(item);
        return true;
    }

    public boolean add(int index, E item) {
        checkIndex(index);
        if (index == size) {
            linkedLast(item);
        } else {
            linkedBefore(index, item);
        }
        return true;
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
        return node(index);
    }

    private E node(int index) {
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.item;
        } else {
            Node<E> node = last;
            for (int i = index; i > 0; i--) {
                node = node.pre;
            }
            return node.item;
        }
    }

    private void checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
        }
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
