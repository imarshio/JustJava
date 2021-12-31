package leetcode.dynamicPrograming.main;

import leetcode.dynamicPrograming.solutions.ClambStairs;
import leetcode.dynamicPrograming.solutions.Greedy;
import leetcode.dynamicPrograming.solutions.MaxSubArray;
import leetcode.dynamicPrograming.solutions.Rob;
import org.junit.Test;

/**
 * @author masuo
 * @data 2021/8/24 8:07
 * @Description DP
 */

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.clambStairs();
        main.rob();
        main.greddy();
        main.maxSubArray();
    }

    @Test
    public void maxSubArray() {
        MaxSubArray msa = new MaxSubArray();
        System.out.println(msa.maxSubArrayI(new int[]{5, -3, 5}));
        System.out.println(msa.maxSubarraySumCircular(new int[]{5, -3, 5}));
        System.out.println(msa.maxSubarraySumCircular(new int[]{-3, -5, -2, -5, -6}));
        System.out.println(msa.maxProduct(new int[]{-3, -5, -2, -5, -6}));
        System.out.println(msa.getMaxLen(new int[]{-3, -5, -2, -5, -6}));
        System.out.println(msa.getMaxLen(new int[]{1, -2, -3, 4}));
        System.out.println(msa.maxScoreSightseeingPair(new int[]{1, 2, 3, 4}));
        System.out.println(msa.maxProfitI(new int[]{3, 6, 4, 3, 1}));
        System.out.println(msa.maxProfitII(new int[]{3, 6, 4, 3, 1}));
        System.out.println(msa.maxProfitII(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    public void greddy() {
        Greedy greedy = new Greedy();
        System.out.println(greedy.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(greedy.canJump(new int[]{1, 2}));
        System.out.println(greedy.jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(greedy.jump(new int[]{2, 3, 1}));
        System.out.println(greedy.jump(new int[]{2, 1}));
        System.out.println(greedy.jump(new int[]{1, 2, 1, 1, 1}));
        System.out.println(greedy.jump(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0}));
    }

    @Test
    public void rob() {
        Rob rob = new Rob();
        System.out.println(rob.rob(new int[]{1, 2, 3, 1}));//4
        System.out.println(rob.rob(new int[]{2, 7, 9, 3, 1}));//12
        System.out.println(rob.rob(new int[]{2, 1, 1, 2}));//4
        System.out.println(rob.robs(new int[]{2, 3, 2}));//3
        System.out.println(rob.robs(new int[]{0}));//3
        System.out.println(rob.deleteAndEarnI(new int[]{3, 4, 2}));//6 = 4 + 2
        System.out.println(rob.deleteAndEarnI(new int[]{2, 2, 3, 3, 3, 4}));//6 = 4 + 2
        System.out.println(rob.deleteAndEarnII(new int[]{2, 2, 3, 3, 3, 4}));//6 = 4 + 2
    }

    @Test
    public void clambStairs() {

        ClambStairs cs = new ClambStairs();
        //System.out.println(cs.climbStairs(2));
        //System.out.println(cs.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(cs.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
