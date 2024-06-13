package sort;

import com.ms.math.Random;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;


/**
 * @author masuo
 * @data 2021/9/10 17:09
 * @Description 归并排序
 */

public class MergeSort {

    @Test
    public void sort() {

        // addTest();

        HashSet<Integer> unsorted = new HashSet<>();
        int i = 0;
        while (i < 20000000) {
            unsorted.add(Random.randomInt(0, 2000000));
            i++;
        }

        // long start1 = System.currentTimeMillis();

        int[] unsort1 = new int[unsorted.size()];
        // int[] unsort = {2, 1, 8, 4, 5, 9, 7, 4, 85, 0};
        int s1 = 0;
        for (Integer integer : unsorted) {
            unsort1[s1] = integer;
            ++s1;
        }

        long start1 = System.currentTimeMillis();
        mergeSort1(unsort1);
        long end1 = System.currentTimeMillis();
        System.out.println(("数组长度为：" + unsorted.size()));
        System.out.println("方法一用时：" + (end1 - start1));

        int[] unsort2 = new int[unsorted.size()];
        // int[] unsort = {2, 1, 8, 4, 5, 9, 7, 4, 85, 0};
        int s = 0;
        for (Integer integer : unsorted) {
            unsort2[s] = integer;
            ++s;
        }
        long start2 = System.currentTimeMillis();
        mergeSort2(unsort2, 0, unsort2.length - 1);
        long end2 = System.currentTimeMillis();
        System.out.println("方法二用时：" + (end2 - start2));

        // 多次测试发现，当数组容量在10万左右时，时间在30~100ms徘徊
        // 方法二比较优秀，因为方法一在中间建立了多个数组，空间浪费严重，
        // 方法一在中间一直是在使用原数组，只有在排序时才使用临时数组
        // for (Integer in : unsort) {
        //    System.out.println(in);
        //}
    }


    private void mergeSort2(int[] unsort, int left, int right) {

        int mid = (right + left) / 2;
        if (left < right) {
            mergeSort2(unsort, left, mid);
            mergeSort2(unsort, mid + 1, right);
            merge(unsort, left, mid, right);
        }
    }

    private void merge(int[] unsort, int left, int mid, int right) {
        // 归并处理, 0 0 1
        int ll = mid;
        int lr = right;
        int size = right - left + 1;
        int[] temp = new int[size];
        while (ll >= left && lr > mid) {
            // 从大往小排序
            if (unsort[ll] > unsort[lr]) {
                temp[size - 1] = unsort[ll];
                --ll;
                --size;
            } else {
                temp[size - 1] = unsort[lr];
                --lr;
                --size;
            }
        }
        while (ll >= left) {
            // 从大往小排序
            temp[--size] = unsort[ll--];
        }
        while (lr > mid) {
            // 从大往小排序
            temp[size - 1] = unsort[lr];
            --lr;
            --size;
        }
        while (left <= right) {
            unsort[left++] = temp[size++];
        }
    }


    @Test
    public void addTest() {
        int i = 0;
        // i++;//会生成新的对象 ===》相当于 ：int j = i+1; i = j;
        System.out.println("执行时的i等于：" + (i++));// 0,因为“++”在后面，所以后执行“++”操作，
        System.out.println("执行完”i++“后：" + i);// 1
        System.out.println(++i);// 2,因为“++”在前面，所以先执行“++”操作，执行赋值
        System.out.println(i);// 1
        ++i;// 不会生成新的对象 相当于：i = i+1;
        int j = 0;
        j++;// 会生成新的对象 ===》相当于 ：int j = i+1;
        ++j;
    }


    /**
     * 这种方法会浪费空间，
     *
     * @param unsort 未排序数组，
     * @return 排序数组
     */
    public int[] mergeSort1(int[] unsort) {

        // 归并排序是建立在归并操作上的一种有效的排序算法。
        // 该算法是采用分治法的一个典型应用
        // 将已有序的子序列合并，得到完全有序的序列；
        // 即先使每个子序列有序，在使子序列段间有序
        // 这种将两个有序表合并为一个有序表称为二路归并。


        // 具体步骤，找到数组的中点，分成两个数组，当两个数组长度大于2时，就继续分，小于2就返回
        // 归并两个有序数组，所以我们需要对两个数组排序，然后将两个数组合并在排序
        int length = unsort.length;
        if (length < 2) {
            return unsort;
        }

        int mid = length / 2;
        // 取左侧数组
        int[] left = Arrays.copyOfRange(unsort, 0, mid);
        // 取右侧数组
        int[] right = Arrays.copyOfRange(unsort, mid, length);
        return sort(mergeSort1(left), mergeSort1(right));
    }

    private int[] sort(int[] left, int[] right) {
        int ll = left.length;
        int lr = right.length;
        int[] sorted = new int[ll + lr];
        int size = ll + lr - 1;
        while (ll > 0 && lr > 0) {
            if (left[ll - 1] > right[lr - 1]) {
                sorted[size] = left[ll - 1];
                --ll;
            } else {
                sorted[size] = right[lr - 1];
                --lr;
            }
            --size;
        }
        // 由于二分的特点，这里最多会剩余一个元素，不确定是在左还是右，但是只有一个，所以我们直接放入即可
        while (ll > 0) {
            sorted[size] = left[ll - 1];
            --size;
            --ll;
        }
        while (lr > 0) {
            sorted[size] = right[lr - 1];
            --size;
            --lr;
        }
        // left = null;
        // right = null;
        return sorted;
    }
}
