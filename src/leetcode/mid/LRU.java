package leetcode.mid;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author masuo
 * @data 6/4/2022 下午4:06
 * @Description NC93 设计LRU缓存结构
 */

public class LRU {


    @Test
    public void test0() {
        //测试protected级别的包级访问
        LevelOrder lo = new LevelOrder();
        int i = lo.i;
        System.out.println(i);
        lo.f();
    }


    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}

/**
 * 设计LRU(最近最少使用)缓存结构，该结构在构造时确定大小，假设大小为 capacity ，操作次数是 n ，并有如下功能:
 * 1. Solution(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * 2. get(key)：如果关键字 key 存在于缓存中，则返回key对应的value值，否则返回 -1 。
 * 3. set(key, value)：将记录(key, value)插入该结构，
 * <p>如果关键字 key 已经存在，则变更其数据值 value，<p/>
 * <p>如果不存在，则向缓存中插入该组 key-value ，
 * 如果key-value的数量超过capacity，弹出最久未使用的key-value<p/>
 */
class LRUCache {
    //选择一种结构，HashMap + 链表 ，
    // 头节点
    LRUNode head;

    //尾节点
    LRUNode tail;

    // 剩余空间
    int leftCapacity;

    HashMap<Integer, Integer> map;

    public LRUCache(int capacity) {
        // write code here
        this.map = new HashMap<>(capacity, 1);
        this.head = this.tail = null;
        this.leftCapacity = capacity;
    }

    public int get(int key) {
        // write code here
        Integer value = map.get(key);
        if (value != null) {
            // 有该节点，需要将该节点放到首位，
            LRUNode node = new LRUNode(key);
            // 删除该节点
            removeNode(key);
            // 放到首位
            addHeadNode(node);
            return value;
        }
        return -1;
    }

    private void removeNode(int key) {
        LRUNode temp = head;
        while (temp != null && temp.value != key){
            temp = temp.next;
        }
        // 因为一定有这个节点
        removeNode(temp);
    }

    public void put(int key, int value) {
        LRUNode node = new LRUNode(key);

        // 先判断是否存在该节点
        boolean b = map.containsKey(key);

        if (!b && leftCapacity <= 0) {
            // 不存在且剩余空间不足，需要先删除最长未使用节点,然后删除map中的点
            map.remove(removeLastNode());
        } else if (!b) {
            // 不存在且剩余空间足够，剩余空间-1
            leftCapacity--;
        } else {
            // 存在该节点，则需要先在链表中删除该节点
            map.remove(key);
            removeNode(key);
        }
        // 不存在 添加  || 存在，且剩余空间足够 覆盖
        map.put(key, value);
        // 然后在头部添加该节点
        addHeadNode(node);
    }

    private int removeLastNode() {
        int key = tail.value;
        if (tail.pre == null) {
            // tail == head
            head = tail = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }
        return key;
    }

    /**
     * 添加头节点
     *
     * @param node n
     */
    private void addHeadNode(LRUNode node) {
        // 判断头节点是否为空
        if (head == null) {
            // 头节点为空，则尾节点一定为空
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head.pre = node;
        //新头
        head = node;
    }

    /**
     * 删除节点
     *
     * @param node n
     */
    private void removeNode(LRUNode node) {
        // pre -> node -> next ==> pre -> next
        // node.pre.next = node.next,需要判断node.pre是否为null
        if (node.pre != null) {
            node.pre.next = node.next;
        } else {
            //说明这是头节点
            head = node.next;
        }

        // node.next.pre = node.pre,需要判断node.next是否为null
        if (node.next != null) {
            node.next.pre = node.pre;
        } else {
            // 说明这是尾节点
            tail = node.pre;
        }
    }
}

// 用来维持先后的链表结构
class LRUNode {

    LRUNode pre;
    LRUNode next;

    int value;

    public LRUNode(int value) {
        this.value = value;
    }
}
