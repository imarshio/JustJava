package iterable.map;

import java.util.HashMap;

/**
 * @author masuo
 * @data 28/4/2022 下午5:18
 * @Description map 不继承于collection
 * 底层是数组+链表，jdk1.8以上是数组+链表/红黑树，在数组长度大于64，且链表长度大于8会转化成红黑树
 * 扩容机制：size * 2
 * hashcode计算：
 */

public class HashMapTest {

    public void hashcodeTest() {
        HashMap<String, Integer> hashMap = new HashMap<>(16);


    }

    int hash(Object key) {
        int h;
        // 这样计算的原因是扩大高位的影响，int类型是32位的，在计算将元素放到哪个位置时，
        // 如果单纯的使用【h.hashcode() / size】，会
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
