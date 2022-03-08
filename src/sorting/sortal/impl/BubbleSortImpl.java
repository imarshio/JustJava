package sorting.sortal.impl;

import sorting.sortal.Sorting;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 2/3/2022 上午8:14
 * @Description 冒泡排序，依次对比两两相邻的数字，
 */

public class BubbleSortImpl implements Sorting {

    @Override
    public int[] sortDescent(int[] unsorted) {
        for (int i = unsorted.length - 1; i > 0; i--) {
            //从前往后对比，最大的或最小的放在最后
            for (int j = 0; j < unsorted.length - 1; j++) {
                if (unsorted[j] > unsorted[j + 1]) {
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j + 1];
                    unsorted[j + 1] = temp;
                }
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
