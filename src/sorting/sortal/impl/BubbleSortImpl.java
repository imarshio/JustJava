package sorting.sortal.impl;

import sorting.sortal.Sorting;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 2/3/2022 上午8:14
 * @Description 冒泡排序，依次对比两两相邻的数字，
 * 时间复杂度：O（N^2）
 * 空间复杂度：O(1)
 */

public class BubbleSortImpl implements Sorting {

    private int [] sorted;

    private void setSorted(int[] sorted) {
        this.sorted = sorted.clone();
    }

    @Override
    public int[] sortDescent(int[] unsorted) {
        setSorted(unsorted);
        for (int i = sorted.length - 1; i > 0; i--) {
            //从前往后对比，最大的或最小的放在最后
            for (int j = 0; j < sorted.length - 1; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    int temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
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
