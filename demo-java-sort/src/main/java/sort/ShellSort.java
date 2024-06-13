package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： masuo
 * @arithmetics ：希尔排序
 * @date ：2021年5月14日 下午11:36:27
 */
public class ShellSort {

    @Test
    public void sort() {
        int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0};
        int length = unsort.length;
        // 希尔排序是插入排序的升级版，插入排序的时间复杂度： O（N） = n*log(n);
        // 希尔排序通过减少数组容量的方法来防止最坏情况的产生，
        // 比如前面是排序好的数组，然后最后一个数据是最小数据，这样我们需要经历n次比较和移动
        // 例：{1.2.3.4.5.6.7.8.9.0}，因为插入排序是将第一个数组作为初始数组，
        // 所以仍需要经历n*log(n)次的比较，但是此时不涉及移动数组
        // 最后一个数据0，需要经历n次的比较和移动

        // 希尔排序通过设置步长来决定数组被分成几份进行移动
        //
        // 一般情况下，取数组长度/2===》就是分组的组数。

        // 制造步长,可以不制造，在使用时在制造
        List<Integer> gaps = new ArrayList<>();
        while (length > 1) {
            gaps.add(length / 2);
            length = length / 2;
        }
        for (Integer gap : gaps) {
            // 步长循环
            for (int i = gap; i < unsort.length; i++) {
                // 注意，虽然在排序的时候我们是按照组来排序，但是在代码实现上我们打乱了顺序，
                int index = i;

                while (index - gap >= 0) {
                    if (unsort[index] < unsort[index - gap]) {
                        int temp = unsort[index];
                        unsort[index] = unsort[index - gap];
                        unsort[index - gap] = temp;
                    }
                    index -= gap;
                }
            }
        }

        for (int i : unsort) {
            System.out.println(i);
        }

    }

    @Test
    public void insertSort() {
        int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0};

        // 插入排序是将第一个数据作为初始数组，之后的数据一个一个的插入
        // 插入时和数组中的数据进行对比，插入到合适的位置
        int[] sorted = new int[unsort.length];
        sorted[0] = unsort[0];// 作为已排序数组

        for (int i = 1; i < unsort.length; i++) {
            // 从第二个数据开始遍历未排序数组
            int j = i;
            // 用j来控制插入数组的数量
            while (j >= 1) {// 为了方便转换成希尔排序，此处使用:j >= 1,可以使用j > 0 来代替
                // 这里使用了冒泡交换的思想，前提是除去未排序的数据之外是一个已排序数组
                // 即在一个已排序的数组的基础上插入一个数据，此时我们只需要将数组扩大一格，然后从后循环比较
                if (unsort[j] < unsort[j - 1]) {
                    int temp = unsort[j];
                    unsort[j] = unsort[j - 1];
                    unsort[j - 1] = temp;
                }
                j--;
            }
        }

        for (int i : sorted) {
            System.out.println(i);
        }
    }

    public static void sort(Comparable[] arr) {
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                Comparable tmp = arr[i];
                for (j = i; j >= gap && tmp.compareTo(arr[j - gap]) < 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }
}
