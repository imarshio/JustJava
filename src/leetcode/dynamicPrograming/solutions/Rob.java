package leetcode.dynamicPrograming.solutions;

import java.util.HashMap;

/**
 * @author masuo
 * @data 2021/11/22 11:29
 * @Description 打家劫舍
 */

public class Rob {

    //你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
    // 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
    // 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
    public int robs(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //处理数组
        int[] numh = new int[nums.length - 1];//[0,length-2]
        int[] numt = new int[nums.length - 1];//[1,length-1]
        if (nums.length - 1 >= 0) System.arraycopy(nums, 0, numh, 0, nums.length - 1);
        if (nums.length - 1 >= 0) System.arraycopy(nums, 1, numt, 0, nums.length - 1);
        return Math.max(rob(numh), rob(numt));
    }


    //你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    //输入：[1,2,3,1]
    //输出：4
    //解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
    //     偷窃到的最高金额 = 1 + 3 = 4 。
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (nums.length == 3) {
            return Math.max(nums[0] + nums[2], nums[1]);
        }
        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = Math.max(nums[0], nums[1]);
        max[2] = Math.max(nums[0] + nums[2], nums[1]);
        int index = 3;
        while (index < nums.length) {
            max[index] = Math.max(max[index - 2], max[index - 3]) + nums[index];
            ++index;
        }
        return Math.max(max[max.length - 1], max[max.length - 2]);
    }


    //给你一个整数数组nums，你可以对它进行一些操作。
    //
    //每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。
    // 之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素，注意连带删除不获得点数。
    // 只有删除操作删除的数据才会获取点数，不然总点数就是数组数据之和了，就没有意义了。
    //开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

    /**********暴力寻找，费时费力*********/
    //输入：nums = [3,4,2]
    //输出：6
    //解释：
    //删除 4 获得 4 个点数，因此 3 也被删除。
    //之后，删除 2 获得 2 个点数。总共获得 6 个点数。
    public int deleteAndEarnI(int[] nums) {
        int[] points = new int[nums.length];
        HashMap<Integer, Integer> pointMap = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (pointMap.containsKey(nums[i])) {
                continue;
            }
            points[i] = del(nums, i);
            max = Math.max(max, points[i]);
            pointMap.put(nums[i], points[i]);
        }
        return max;
    }

    //从下标为index得数据开始删除
    private int del(int[] nums, int index) {
        //初始删除操作，获得点数
        int point = nums[index];
        int[] tempNums = nums.clone();//这里必须使用clone，如果不使用clone，还是会对原数组进行操作
        int max = 0;
        //连带删除,1 <= nums[i] <= 10^4
        if (relateDel(tempNums, index)) {
            //说明还有没有被删除的。继续按照上面的思路循环查找
            for (int i = 0; i < nums.length; i++) {
                if (tempNums[i] != 0) {
                    max = Math.max(max, del(tempNums, i));
                }
            }
        }

        return point + max;
    }

    private boolean relateDel(int[] nums, int index) {
        boolean flag = false;
        //标志已被删除
        int temp = nums[index];
        nums[index] = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                //碰到0 跳过
                continue;
            }
            if (nums[i] == temp + 1 || nums[i] == temp - 1) {
                //连带删除
                nums[i] = 0;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    /*********打家劫舍版，省时省力**********/
    public int deleteAndEarnII(int[] nums) {
        //打家劫舍就是从数组中找出最大的不相邻的数据之和
        //所以我们需要先对数据进行处理，让其变成相邻数据
        //首先找到最大值，扩容数组做准备
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] sum = new int[max + 1];
        for (int num : nums) {
            //这里是让其保持原位置不变的情况下，进行累加，这样它的相邻位置还是num的相邻数据
            sum[num] += num;
        }

        return robI(sum);
    }

    private int robI(int[] sum) {
        //这里保证了数组长度大于等于2，所以我们可以直接使用滑动窗口方法
        int first = sum[0];
        int second = Math.max(sum[1], sum[0]);
        for (int i = 2; i < sum.length; i++) {
            int temp = first;
            first = second;
            second = Math.max(temp+sum[i],second);
        }
        return second;
    }
}
