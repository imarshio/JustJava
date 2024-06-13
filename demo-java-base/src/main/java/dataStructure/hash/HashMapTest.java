package dataStructure.hash;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author masuo
 * @data 18/1/2022 下午2:35
 * @Description HashMap
 * 数组+链表，数组初始化大小为16，每次扩容增大一倍
 */

public class HashMapTest {

    @Test
    public void test1() {

        HashMap<String, String> hashMap = new HashMap<>(4);
        hashMap.put("0", "000");
        hashMap.put("1", "111");
        hashMap.put("2", "222");
        String s = hashMap.get("0");
        System.out.println(s);

    }
}
