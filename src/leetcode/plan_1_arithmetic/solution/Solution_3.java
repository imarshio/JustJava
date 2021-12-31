package leetcode.plan_1_arithmetic.solution;

/**
 * @author masuo
 * @data 2021/8/20 13:13
 * @Description day 3
 */

public class Solution_3 {


    /**
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     * numbers = [2,7,11,15], target = 9
     * [1,2]
     *
     * @param numbers 升序排列  的整数数组
     * @param target  目标数
     * @return 数组中找出两个数的下标
     * 13:13-13:23 --> 10m
     */
    public int[] twoSum(int[] numbers, int target) {
        //双指针
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int num = numbers[left] + numbers[right];
            if (num == target) {
                return new int[]{left + 1, right + 1};
            } else if (num < target) {
                left++;
            } else {
                right--;
            }
        }
        return numbers;
    }


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * [0,1,0,3,12]--1003--130012--131200
     * 输出: [1,3,12,0,0]
     * 0 1 /
     * 1 0 /
     * 0 0 /
     * 1 1 /
     * @param nums 数组
     */
    public void moveZeroes(int[] nums) {
        //双指针
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }

        for (int i : nums) {
            System.out.println(i);
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
    }
}
