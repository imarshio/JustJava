package leetcode.easy;

import org.junit.Test;

/**
 * @author masuo
 * @data 15/4/2022 下午4:32
 * @Description 合并两个有序的数组
 */

public class Merge {

    public void merge(int[] A, int m, int[] B, int n) {
        int[] rt = new int[m + n];
        int a = 0, b = 0;
        while (a < m && b < n) {
            if (A[a] <= B[b]) {
                rt[a + b] = A[a++];
            } else {
                rt[a + b] = B[b++];
            }
        }
        while (a < m) {
            rt[a + b] = A[a++];
        }
        while (b < n) { 
            rt[a + b] = B[b++];
        }
        if (m + n >= 0) System.arraycopy(rt, 0, A, 0, m + n);
    }

    @Test
    public void test(){
        merge(new int[]{1},1,new int[]{},0);
    }
}
