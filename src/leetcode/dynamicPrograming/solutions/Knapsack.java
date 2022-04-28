package leetcode.dynamicPrograming.solutions;

import org.junit.Test;

/**
 * @author masuo
 * @data 24/4/2022 上午11:26
 * @Description 背包问题 -- 动态规划系
 */

public class Knapsack {

    // 01背包，最基本的背包问题，指一共有N件物品，第i件的重量为w[i]，背包限重为j
    // 所以我们将dp[i][j]表示为将前i件物品装入限重为j的背包

    // 例子：装箱问题 now coder
    // 有一个箱子容量为V（正整数，0 ≤ V ≤ 20000），同时有n个物品（0＜n ≤ 30），每个物品有一个体积（正整数）。
    // 要求n个物品中，任取若干个装入箱内，使箱子的剩余空间为最小。

    // 我们可以将物品组合成一个数组 nums{num1,num2,num3}

    public void packing(int v, int num, int[] volumes) {
        // dp[i][j] : 代表在箱子剩余容量为j时，前 i个物品体积和在不超过j的情况下的最大值
        // 由于 i = 0时，dp[0][j] = 0,
        int[][] dp = new int[num][v];
        for (int i = 1; i < num; i++) {
            int j = v;//剩余容量
            while (j >= volumes[i]) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - volumes[i]] + volumes[i]);
                j -= volumes[i];
            }
        }
    }

    @Test
    public void test() {
        packing(24, 6, new int[]{8, 3, 12, 7, 9, 7});
    }
}
