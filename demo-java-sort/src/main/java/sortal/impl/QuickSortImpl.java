package sortal.impl;

// import org.junit.Test;
import sortal.Sorting;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 2/3/2022 下午3:34
 * @Description 快排，夹击
 * 选择一边的数作为基准值，比基准值大的放在右面，比基准值小的放在左面，最后基准值放在中间
 * 时间复杂度：O（N*logN）
 * 空间复杂度：O(logN)
 */

public class QuickSortImpl implements Sorting {

    private int[] sorted;

    public void setSorted(int[] sorted) {
        this.sorted = sorted;
    }

    @Override
    public int[] sortDescent(int[] unsorted) {
        this.setSorted(unsorted);
        int length = sorted.length;
        if (length == 0 || length == 1) {
            return sorted;
        }
        // 快排版本1.0：左侧为《= pivot，右侧为 》 pivot。时间复杂度为O（N*logN）,空间复杂度为O（logN）
        quickSort1(sorted, 0, length - 1);

        // 快排版本2.0：左侧为《 pivot，中间为 = pivot，右侧为 》pivot。时间复杂度为O（N*logN）,空间复杂度为O（logN）
        quickSort2(sorted, 0, length - 1);

        // 快排版本3.0：左侧为《 pivot，中间为 = pivot，右侧为 》pivot，且pivot为随机选取的点，时间复杂度为O（N*logN）,空间复杂度为O（logN）
        quickSort2(sorted, 0, length - 1);

        for (int i : sorted) {
            System.out.println(i);
        }
        return sorted;
    }

    /**
     * 快排版本1.0
     * 左侧为《= pivot，右侧为 》 pivot。
     * 时间复杂度为O（N*logN）,，最坏为O（N^2）空间复杂度为O（logN）
     *
     * @param sorted 未排序数组
     * @param L      左侧起点
     * @param R      右侧起点
     */
    private void quickSort1(int[] sorted, int L, int R) {
        if (L < R) {
            int[] p = partition1(sorted, L, R);
            quickSort1(sorted, L, p[0] - 1); // <= 区
            quickSort1(sorted, p[1] + 1, R); // > 区
        }

    }

    /**
     * 根据一个pivot将数组划分成两部分，
     * 区1 <= pivot < 区2
     *
     * @param sorted 排序数组
     * @param l      左边界
     * @param r      右边界
     * @return 区1的右边界，区2的左边界
     */
    private int[] partition1(int[] sorted, int l, int r) {
        // 取最右侧为基准,3 5 6 7 4 3 5 8
        int pivot = sorted[r];
        // 小于区的起始点为 l-1
        int indexL = l - 1;
        // 数组起点为l
        int index = l;
        while (index <= r) {
            // sorted[index] 和 pivot 对比， 两种情况
            if (sorted[index] <= pivot) {
                // 情况1：小于等于基准值，则交换当前位置数字和小于等于区的下一个数
                swap(sorted, index, ++indexL);
            }
            // 情况2：大于基准值，直接index+1
            index++;
        }
        return new int[]{indexL, indexL};
    }

    // @Test
    public void swapTest() {
        // swap(new int[]{1, 2}, 0, 1);
        sortDescent(new int[]{3, 5, 6, 7, 4, 3, 8, 5});
    }

    private void swap(int[] sorted, int index1, int index2) {
        int temp = sorted[index1];
        sorted[index1] = sorted[index2];
        sorted[index2] = temp;

        // 方法2,前提：index1不等于index2或两个数的地址不一样
        // sorted[index1] = sorted[index1] ^ sorted[index2];
        // sorted[index2] = sorted[index1] ^ sorted[index2];
        // sorted[index1] = sorted[index1] ^ sorted[index2];
    }

    /**
     * 快排版本2.0
     * 左侧为《 pivot，中间为 = pivot，右侧为 》pivot.
     * 左区 < pivot < 右区
     * 时间复杂度为O（N*logN）,空间复杂度为O（logN）
     *
     * @param sorted 未排序数组
     * @param L      左侧起点
     * @param R      右侧起点
     */
    private void quickSort2(int[] sorted, int L, int R) {
        if (L < R) {
            int[] partition = partition2(sorted, L, R);
            quickSort2(sorted, L, partition[0]);
            quickSort2(sorted, partition[1], R);
        }
    }

    /**
     * 根据一个pivot将数组划分成三部分，
     * 区1 < 区2 < 区3
     *
     * @param sorted 排序数组
     * @param l      左边界
     * @param r      右边界
     * @return 区1的右边界，区3的左边界
     */
    private int[] partition2(int[] sorted, int l, int r) {
        int pivot = sorted[r];
        int indexL = l - 1;
        int indexR = r + 1;
        int index = l;
        while (index < indexR) {
            // 对比index和pivot
            if (sorted[index] < pivot) {
                // 小于pivot，放到区1
                swap(sorted, index++, ++indexL);
            } else if (sorted[index] > pivot) {
                // 大于pivot，放到区3
                swap(sorted, index, --indexR);
            } else {
                // == pivot,index++
                index++;
            }
        }
        return new int[]{indexL, indexR};
    }

    /**
     * 快排版本3.0
     * 左侧为《 pivot，中间为 = pivot，右侧为 》pivot，且pivot为随机选取的点.
     * 时间复杂度为O（N*logN）,空间复杂度为O（logN）
     *
     * @param sorted 未排序数组
     * @param L      左侧起点
     * @param R      右侧起点
     */
    private void quickSort3(int[] sorted, int L, int R) {

    }


    @Override
    public int[] sortAscent(int[] sorted) {
        return new int[0];
    }

    @Override
    public ArrayList<Integer> sortDescent(ArrayList<Integer> sorted) {
        return null;
    }

    @Override
    public ArrayList<Integer> sortAscent(ArrayList<Integer> sorted) {
        return null;
    }
}
