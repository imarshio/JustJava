package dataStructure.linkedList;

import java.util.*;

/**
 * @author masuo
 * @data 2021/9/18 14:56
 * @Description 单向链表
 * 链表的实现是将节点与节点连接起来实现的，
 * 继承AbstractSequentialList的作用是使数据顺序访问，不支持随机访问，这样访问时速度较慢，
 * 主要是靠Collection和Iterator实现的
 */

public class OneWayLinkedList<E> extends AbstractSequentialList<E> {

    //将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会被序列化
    //现在问题来了，什么是序列化？序列化有啥用？啥时候序列化？怎么序列化？
    //
    //为什么要将Java对象序列化？  -  一个被序列化之后的对象可以存储在数据库或文件中，也可用于网络传输
    // 序列化的最大的作用就是为了反序列化，这样我们就能读取出来Java对象了，
    // 所以序列化后的字节序列都是可以恢复成Java对象
    //
    // what = Java序列化就是指把Java对象转换为字节序列的过程
    //
    // 什么时候序列化： 转化为字节序列的时候，什么时候需要转化为字节序列，
    // 一般当我们使用缓存（缓存空间不足时会存储到本地）或远程调用rpc（网络传输）的时候，
    // 经常需要将Java对象序列化来传输/保存
    //
    //数组的大小
    transient int size = 0;

    //链表的头部
    transient Node<E> first;

    //链表的尾部
    transient Node<E> last;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    //增加一个节点，
    public boolean add(E item) {
        //链表没有初始容量，所以不需要检验数组容量是否足够
        // 在多线程情况下，由于头插容易死锁，所以我们使用尾插法
        Node<E> node = new Node<>(item);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        ++size;
        return false;
    }


    public void add(int index, E item) {
        checkIndex(index);
        if (index == size) {
            add(item);
        } else {
            //插入前面某个字段
            Node<E> node = new Node<>(item);
            Node<E> temp = first;
            while (index > 1) {
                temp = temp.next;
                --index;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 检查插入节点下标是否符合规则
     *
     * @param index 插入节点下标
     */
    private void checkIndex(int index) {
        //
        if (index > size) {
            throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
        }
    }

    //无参构造函数
    public OneWayLinkedList() {
    }

    //复制构造器
    public OneWayLinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        addAll(size, c);
        return false;
    }

    public boolean addAll(int size, Collection<? extends E> c) {

        return false;
    }

    @Override
    public E get(int index) {
        return node(index).item;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkIndex(index);
        return new Iter(index);
    }

    //继承了AbstractSequentialList就必须实现这个类，因为AbstractSequentialList都是通过操作这个类的对象来操作数组，
    // 如果没有实现这个的话，就会出现collecting data而没有数据
    private class Iter implements ListIterator<E> {
        private Node<E> first;
        private Node<E> next;

        public Iter() {
        }

        public Iter(int index) {
            next = (index == size) ? null : node(index);
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
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

    /**
     * 获取index下标上的节点
     *
     * @param index 节点下标
     * @return <@code >Node</@code>
     */
    Node<E> node(int index) {

        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
        //if (index < (size >> 1)) {
        //    //判断index是否小于size的一半，此处的判断适用于双向链表，可以减少查询时间，但是单向链表中，不需要判断
        //}
    }

    //单向链表的实现是用单向节点，即节点指向下一节点的地址
    //节点类
    static class Node<E> {
        //泛型，可以传入任意类型得参数
        E item;
        Node<E> next;

        public Node() {
            this(null, null);
        }

        public Node(E item) {
            this(item, null);
        }

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
