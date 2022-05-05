package leetcode.easy;

/**
 * @author ： masuo
 * @arithmetics ：DP()动态规划
 * @date  ：2021年3月11日 上午11:18:11
 * @description  :给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaxSubArray {

    public static void main(String[] args) {
        //  [-2,1,-3,4,-1,2,1,-5,4] = 6
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("连续子数组的和最大为:" + maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int big = nums[0];
        int[] max = new int[len];
        max[0] = big;
        for (int i = 1; i < len; i++) {
            // 一次循环 ，时间复杂度 = O(N)
            max[i] = Math.max(nums[i], nums[i] + max[i - 1]);
            big = Math.max(max[i], big);
        }
        return big;
    }

}
