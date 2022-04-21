package newcoder.top101;

import org.junit.Test;

/**
 * @author masuo
 * @data 13/4/2022 上午9:24
 * @Description 寻找第K大
 */

public class Solution_088KthMax {
    /**
     * 有一个整数数组，请你根据快速排序的思路，找出数组中第 k 大的数。
     * 给定一个整数数组 a ,同时给定它的大小n和要找的 k ，请返回第 k 大的数
     * (包括重复的元素，不用去重)，保证答案存在。
     *
     * @param a 整数数组
     * @param n 数组大小
     * @param K 要找的 k
     * @return int
     */
    public int findKth1(int[] a, int n, int K) {
        // write code here
        quickSort(a, n);
        return a[n - K];
    }

    private void quickSort(int[] a, int len) {
        quickSort(a, 0, len - 1);
    }

    private void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, R, (int) (Math.random() * (R - L) + L));
            int[] edges = partition(arr, L, R);
            quickSort(arr, L, edges[0]);
            quickSort(arr, edges[1], R);
        }
    }

    private int[] partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int indexL = l - 1;
        int indexR = r + 1;
        while (l < indexR) {
            if (arr[l] < pivot) {
                swap(arr, l++, ++indexL);
            } else if (arr[l] > pivot) {
                swap(arr, l, --indexR);
            } else {
                l++;
            }
        }
        return new int[]{indexL, indexR};
    }

    private void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    @Test
    public void test() {
        int[] a = new int[]{10, 10, 9, 9, 8, 7, 5, 6, 4, 3, 4, 2};
        findKth2(a, 12, 3);
    }

    /**
     * 利用快排机制，而不是将数组排序
     */
    public int findKth2(int[] a, int n, int K) {
        // write code here
        return quickFind(a, 0, n - 1, n - K);
    }

    private int quickFind(int[] a, int L, int R, int k) {
        swap(a, R, (int) (L + (Math.random() * (R - L))));
        int[] p = partition(a, L, R);
        if (p[0] >= k) {
            return quickFind(a, L, p[0], k);
        } else if (p[1] <= k) {
            return quickFind(a, p[1], R, k);
        } else {
            return a[k];
        }
    }
}
