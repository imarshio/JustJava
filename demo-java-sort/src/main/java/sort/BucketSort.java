package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author masuo
 * @data 2021/9/16 13:38
 * @Description 桶排序
 */

@SuppressWarnings("ALL")
public class BucketSort {

    @Test
    public void sort() {

        int[] unsort = {11, 22, 41, 51, 82, 92, 72, 42, 85, 10};

        // 桶排序是以一定间隔作为一个桶，这个间隔就是桶的容量（默认是10）。
        // 将数据放到桶中，进行排序。因为桶内使用的是链表，可以使用插入排序加快效率。
        int capacity = 10;
        bucketSort(unsort, capacity);


    }

    public void getS(int... ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.println(i);
        }
    }

    private void bucketSort(int[] unsort, int capacity) {

        int max = Arrays.stream(unsort).max().getAsInt();
        int min = Arrays.stream(unsort).min().getAsInt();
        int pivot = min / capacity;// 海平线
        // 声明桶的个数，也是为了减少空间的浪费，同时也是为了防止下标越界的错误，因为可能会出现负数的情况,
        // 如果数组中最小值是
        int length = (max / capacity) - (min / capacity) + 1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(length);

        // 添加桶
        for (int i = 0; i < length; i++) {
            buckets.add(new ArrayList<>());
        }
        // OneWayNode[] buckets = new OneWayNode[length];

        // 为每个桶添加数据
        for (int i : unsort) {
            int index = i / capacity - pivot;// 这是实际的桶下标
            ArrayList<Integer> bucket = buckets.get(index);
            bucket.add(i);
        }

        // 对每个桶排序
        for (ArrayList<Integer> bucket : buckets) {
            // 自然排序
            bucket.sort(Comparator.naturalOrder());
        }

        // 输出桶元素
        for (ArrayList<Integer> bucket : buckets) {
            for (Integer integer : bucket) {
                System.out.println(integer);
            }
        }
    }


    static class TwoWayNode {
        int value;
        TwoWayNode pre;
        TwoWayNode next;

        // 双向节点需要使用First和Last来解决头为null的问题
        public TwoWayNode() {
        }

        public TwoWayNode(int value) {
            this(value, null, null);
        }

        public TwoWayNode(int value, TwoWayNode next) {
            this(value, null, next);
        }

        public TwoWayNode(int value, TwoWayNode pre, TwoWayNode next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    static class OneWayNode {
        int value;
        OneWayNode next;

        public OneWayNode() {
        }

        public OneWayNode(int value) {
            this.value = value;
        }

        public OneWayNode(int value, OneWayNode next) {
            this.value = value;
            this.next = next;
        }
    }
}
