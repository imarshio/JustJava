package sorting.sortal.impl;

import sorting.sortal.Sorting;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 2/3/2022 下午3:34
 * @Description 快排，夹击
 * 选择一边的数作为基准值，比基准值大的放在右面，比基准值小的放在左面，最后基准值放在中间
 */

public class QuickSortImpl implements Sorting {

    @Override
    public int[] sortDescent(int[] unsorted) {
        int length = unsorted.length;
        // 快排版本1.0：左侧为《= pivot，右侧为 》 pivot。时间复杂度为O（N*logN）,空间复杂度为O（logN）
        quickSort1(unsorted, 0, length - 1);

        // 快排版本2.0：左侧为《 pivot，中间为 = pivot，右侧为 》pivot。时间复杂度为O（N*logN）,空间复杂度为O（logN）
        quickSort2(unsorted, 0, length - 1);

        // 快排版本3.0：左侧为《 pivot，中间为 = pivot，右侧为 》pivot，且pivot为随机选取的点，时间复杂度为O（N*logN）,空间复杂度为O（logN）
        quickSort2(unsorted, 0, length - 1);

        return unsorted;
    }

    /**
     * 快排版本1.0
     * 左侧为《= pivot，右侧为 》 pivot。
     * 时间复杂度为O（N*logN）,，最坏为O（N^2）空间复杂度为O（logN）
     * @param unsorted 未排序数组
     * @param L 左侧起点
     * @param R 右侧起点
     */
    private void quickSort1(int[] unsorted, int L, int R) {

    }

    /**
     * 快排版本2.0
     * 左侧为《 pivot，中间为 = pivot，右侧为 》pivot.
     * 时间复杂度为O（N*logN）,空间复杂度为O（logN）
     * @param unsorted 未排序数组
     * @param L 左侧起点
     * @param R 右侧起点
     */
    private void quickSort2(int[] unsorted, int L, int R) {

    }

    /**
     * 快排版本3.0
     * 左侧为《 pivot，中间为 = pivot，右侧为 》pivot，且pivot为随机选取的点.
     * 时间复杂度为O（N*logN）,空间复杂度为O（logN）
     * @param unsorted 未排序数组
     * @param L 左侧起点
     * @param R 右侧起点
     */
    private void quickSort3(int[] unsorted, int L, int R) {

    }


    @Override
    public int[] sortAscent(int[] unsorted) {
        return new int[0];
    }

    @Override
    public ArrayList<Integer> sortDescent(ArrayList<Integer> unsorted) {
        return null;
    }

    @Override
    public ArrayList<Integer> sortAscent(ArrayList<Integer> unsorted) {
        return null;
    }
}
