package newcoder.top101;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 11/4/2022 上午8:54
 * @Description NC119 最小的K个数
 */

public class Solution_119LeastK {

    /**
     * 此问题即求topK的变种，LeastK
     * 快排解法
     *
     * @param input 数组
     * @param k     容量
     * @return 数组
     */
    public ArrayList<Integer> getLeastNumbers_QuickSort(int[] input, int k) {
        // 第一种方法：快排，然后取前k个数
        quickSort(input, 0, input.length - 1);
        // 取前k个
        ArrayList<Integer> rt = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            rt.add(i, input[i]);
        }
        return rt;
    }

    private void quickSort(int[] input, int L, int R) {
        if (L < R) {
            // 分区
            int[] p = partition(input, L, R);
            quickSort(input, L, p[0]);
            quickSort(input, p[1], R);
        }
    }

    /**
     * 求分区， 区1  <  区2  <  区3
     *
     * @param input 待排序数组
     * @param l     左边界
     * @param r     右边界
     * @return int[]
     */
    private int[] partition(int[] input, int l, int r) {
        // 设置基准值
        int pivot = input[r];
        // 区1起点
        int indexL = l - 1;
        // 区3起点
        int indexR = r + 1;
        // 数组起点
        int index = l;
        while (index < indexR) {
            if (input[index] < pivot) {
                // 放在区1，交换
                swap(input, index++, ++indexL);
            } else if (input[index] > pivot) {
                // 放在区3，交换
                swap(input, index, --indexR);
            } else {
                // 相等
                index++;
            }
        }
        return new int[]{indexL, indexR};
    }

    private void swap(int[] input, int index, int indexL) {
        if (index != indexL) {
            int temp = input[index];
            input[index] = input[indexL];
            input[indexL] = temp;
        }
    }

    /**
     * 此问题即求topK的变种，LeastK
     * 堆排解法
     *
     * @param input 数组
     * @param k     容量
     * @return 数组
     */
    public ArrayList<Integer> getLeastNumbers_HeapSort(int[] input, int k) {
        // 第二种方法：堆排，建立一个小顶堆
        int len = input.length;
        int lastNonLeaf = len / 2 - 1;
        buildSmallPile(input, lastNonLeaf, len);

        ArrayList<Integer> rt = new ArrayList<>(k);
        // 给小顶堆 排序，我们只需要排序k次
        for (int i = len - 1; k > 0; i--, k--) {
            rt.add(input[0]);
            swap(input, 0, i);
            shiftDown(input, 0, i);
        }
        rt.forEach(i -> {
            System.out.print(i + "  ");
        });
        return rt;
    }

    private void buildSmallPile(int[] input, int lastNonLeaf, int length) {
        while (lastNonLeaf >= 0) {
            shiftDown(input, lastNonLeaf--, length);
        }
    }

    /**
     * 构建以lastNonLeaf为根节点的子树的堆
     *
     * @param input   数组
     * @param nonLeaf 非叶子节点
     * @param length  数组长度
     */
    private void shiftDown(int[] input, int nonLeaf, int length) {
        int left = nonLeaf * 2 + 1;
        if (left >= length) return;
        if (input[left] < input[nonLeaf]) {
            // 左子节点小于父节点，交换
            swap(input, left, nonLeaf);
            shiftDown(input, left, length);
        }

        int right = left + 1;
        if (right >= length) return;
        if (input[right] < input[nonLeaf]) {
            swap(input, right, nonLeaf);
            shiftDown(input, right, length);
        }
    }

    /**
     * 此问题即求topK的变种，LeastK
     * 冒泡+选择思想，每次循环只取最小的
     *
     * @param input 数组
     * @param k     容量
     * @return 数组
     */
    public ArrayList<Integer> getLeastNumbers_BubbleSort(int[] input, int k) {
        // 第三种方法：冒泡+选择
        ArrayList<Integer> rt = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            // 最小值的下标
            int min = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[min]) {
                    min = j;
                }
            }
            swap(input, i, min);
            rt.add(input[i]);
        }
        // 取前k个
        return rt;
    }

    @Test
    public void test() {
        // getLeastNumbers_QuickSort(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
        // getLeastNumbers_BubbleSort(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
        getLeastNumbers_HeapSort(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
    }
}
