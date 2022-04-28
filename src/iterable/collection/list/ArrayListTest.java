package iterable.collection.list;

/**
 * @author masuo
 * @date: 2022/04/27/ 下午10:31
 * @description 底层是数组，可以动态的增加、删除元素，动态扩容等，
 * 默认初始容量10，超出上限会扩容至：原来容量的1.5倍
 * int newCapacity = oldCapacity + (oldCapacity >> 1);
 * 优点：按下标索引速度快(O(1))。
 * 缺点：插入删除会慢(O(n))。
 */
public class ArrayListTest {


}
