package leetcode.dynamicPrograming.solutions;

import arithmetic.sort.QuickSort;

/**
 * @author masuo
 * @data 2021/11/23 10:56
 * @Description 跳跃游戏 -- 贪心算法
 */

public class Greedy {

    //给定长度为2n的整数数组 nums ，你的任务是将这些数分成 n 对,
    // 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
    //
    //输入：nums = [1,4,3,2]
    //输出：4
    //解释：所有可能的分法（忽略元素顺序）为：
    //1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
    //2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
    //3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
    //所以最大总和为 4
    public int arrayPairSum(int[] nums) {
        //需要先排序，然后进行分组，即是最优解
        //QuickSort qs = new QuickSort();
        //qs.sort(nums);

        return 0;
    }

    //给定一个非负整数 数组 nums ，你最初位于数组的 第一个下标 。
    //数组中的每个元素代表你在该位置 【可以】 跳跃的最大长度。
    //判断你是否能够到达最后一个下标。
    public boolean canJump(int[] nums) {
        //贪心算法，假设能到达，那么往后走，
        if (nums.length <= 1) {
            return true;
        }
        int maxStep = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (maxStep >= i) {
                //说明这个位置可达
                maxStep = Math.max(maxStep, nums[i] + i);
                if (maxStep >= nums.length - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    //给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
    //数组中的每个元素代表你在该位置可以跳跃的最大长度。
    //你的目标是使用最少的跳跃次数到达数组的最后一个位置。
    //假设你总是可以到达数组的最后一个位置。
    public int jump(int[] nums) {
        int step = 0;
        int position = 0;

        int end = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的
            position = Math.max(position, nums[i] + i);
            if( i == end){ //遇到边界，就更新边界，并且步数加一
                end = position;
                step++;
            }
        }

        return step;
    }

    private int getMaxStep(int[] nums, int position, int maxStep) {
        int max = nums[position];
        if(maxStep>= nums.length-1){
            return nums.length - 1;
        }
        for (int i = position + 1; i <= maxStep; i++) {
            if(max <= nums[i]){
                max = nums[i];
                position = i;
            }
        }
        return position;
    }

}
