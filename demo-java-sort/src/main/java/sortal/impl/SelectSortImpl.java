package sortal.impl;

import sorting.sortal.Sorting;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 2/3/2022 下午3:50
 * @Description 选择排序, 是冒泡排序的升级版，在冒泡排序里每两个相邻的数字都需要对比
 * 选择排序只需要选择一次的最大值/最小值，最后再进行替换，一次循环只需要交换一次位置
 * 时间复杂度：O（N^2）
 * 空间复杂度：O（1）
 */

public class SelectSortImpl implements Sorting {

    @Override
    public int[] sortDescent(int[] unsorted) {
        for (int i = unsorted.length - 1; i >= 0; i--) {
            int max = unsorted[0];
            int index = 0;
            for (int j = 1; j < i; j++) {
                if (unsorted[j] > max) {
                    max = unsorted[j];
                    index = j;
                }
            }
            if (index != i) {
                // 这是为了最大值刚好为待排序的最后一位时，不在交换
                unsorted[index] = unsorted[i];
                unsorted[i] = max;
            }
        }
        return unsorted;
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
