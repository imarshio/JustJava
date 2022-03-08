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
        quickSortPR(unsorted, 0, length - 1);
        return unsorted;
    }

    private void quickSortPR(int[] unsorted, int left, int right) {
        if (left >= right) {
            return;
        }

        int indexL = left;
        int indexR = right;
        int pivot = unsorted[right];
        while (left < right) {
            while (unsorted[left] < pivot) {
                left++;
            }
            while (unsorted[right] >= pivot && left < right) {
                right--;
            }
            if (left != right) {
                int temp = unsorted[left];
                unsorted[left] = unsorted[right];
                unsorted[right] = temp;
            }
        }

        unsorted[indexR] = unsorted[left];
        unsorted[left] = pivot;

        quickSortPR(unsorted,indexL,left-1);
        quickSortPR(unsorted,left+1,indexR);
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
