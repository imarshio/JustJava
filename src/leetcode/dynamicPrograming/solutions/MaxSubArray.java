package leetcode.dynamicPrograming.solutions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author masuo
 * @data 2021/11/25 11:46
 * @Description 最大子序和
 */

public class MaxSubArray {
    //给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    public int maxSubArrayI(int[] nums) {
        //-2,1,-3,4,-1,2,1,-5,4
        //下面这就是 kadane算法，enmmm，自己想出来的，后来看到leetcode上面竟然有这种算法。
        int preMax = nums[0], max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preMax = Math.max(nums[i], preMax + nums[i]);
            max = Math.max(preMax, max);
        }
        return max;
    }

    /*环形子数组的最大和*/
    //给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
    //在此处，环形数组意味着数组的末端将会与开头相连呈环状。
    // （形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
    //此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。
    // （形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j
    // 其中 k1 % A.length = k2 % A.length）
    public int maxSubarraySumCircular(int[] nums) {
        //5，-3，5
        //队列
        // S: sum of A

        //环形数组连续最大和，无非就是两种情况
        // 1.无环，即数组最大和
        // 2.有环，必定包括头尾两个数据，这样的话，我们只需要找出其余数据的【连续最小和】
        // 再让【sum】-【连续最小和】即可得到带环的【最大连续和】

        //求不带环的最大连续和，使用kadane算法
        if (nums.length == 1) {
            return nums[0];
        }
        int preMax = nums[0], max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preMax = Math.max(nums[i], preMax + nums[i]);
            max = Math.max(preMax, max);
        }

        //求1 ~ n-1带环的最小连续和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int preMin = nums[1], min = nums[1];
        for (int i = 2; i < nums.length; i++) {
            preMin = Math.min(nums[i], preMin + nums[i]);
            min = Math.min(min, preMin);
        }

        return Math.max(max, sum - min);
    }

    //乘积最大子数组
    public int maxProduct(int[] nums) {
        //数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
        //在这里我们不能简单的使用kadane算法秋节，因为这里存在负负得正的情况，
        // 即我们不能得知下一个数是 正 还是 负 ，
        // 所以我们这里除了维护一个最大值之外，还需要维护一个最小值，
        // 根据【nums[i]】【min[i]】【max[i]】得出最大值

        // -3, -5, -2, -5, -6
        int[] maxPro = nums.clone();
        int[] minPro = nums.clone();
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tempMax = maxPro[i - 1] * nums[i];
            int tempMin = minPro[i - 1] * nums[i];
            maxPro[i] = Math.max(nums[i], Math.max(tempMin, tempMax));
            minPro[i] = Math.min(nums[i], Math.min(tempMin, tempMax));
            max = Math.max(max, maxPro[i]);
        }
        return max;
    }

    //乘积为正数的最长子数组长度
    public int getMaxLen(int[] nums) {
        int length = nums.length;
        int[] positive = new int[length];
        int[] negative = new int[length];

        positive[0] = nums[0] > 0 ? 1 : 0;//乘积为正数
        negative[0] = nums[0] < 0 ? 1 : 0;//乘积为负数
        int max = positive[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] == 0) {
                negative[i] = 0;
                positive[i] = 0;
                continue;
            }
            if (nums[i] > 0) {
                // 大于0 ，正数数组+1，负数数组
                positive[i] = positive[i - 1] + 1;
                negative[i] = negative[i - 1] == 0 ? 0 : negative[i - 1] + 1;
                //if (negative[i - 1] == 0) {
                //    negative[i] = 0;
                //} else {
                //    negative[i] = positive[i - 1] + 1;
                //}
            } else {
                // 小于0 ，符号转换
                // negative[i] = positive[i-1] + 1
                // positive[i] = negative[i-1] + 1
                // 无论 positive[i-1] 是否等于 0，对 negative[i] 都没有影响
                // 但是 negative[i-1] 等于 0 的时候，positive[i] 由于前面不为负数，所以不能转换符号
                //if (negative[i - 1] == 0) {
                //    positive[i] = 0;
                //} else {
                //    positive[i] = negative[i - 1] + 1;
                //}
                positive[i] = negative[i - 1] == 0 ? 0 : negative[i - 1] + 1;
                //负数
                negative[i] = positive[i - 1] + 1;
            }
            max = Math.max(max, positive[i]);
        }
        return max;
    }

    /*最佳观光组合*/
    //给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，
    // 并且两个景点 i 和 j 之间的 距离 为 j - i。
    //一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，
    // 也就是景点的评分之和 减去 它们两者之间的距离。
    //返回一对观光景点能取得的最高分。
    public int maxScoreSightseeingPair(int[] values) {
        //2 <= values.length <= 5 * 10^4
        //这题一看我们就知道可以使用双循环的暴力解法，但是这样时间复杂度是O（n^2）,
        // 由于数组最大长度为5 * 10^4,所以肯定不会行的，需要优化
        // 优化方法：拆分
        // values[i] + values[j] + i - j ==>  【va[i]+i】 + 【va[j]-j】
        //这样我们需要建立两个数组，用来存储【value[i]+i】【value[i]-i】，
        // 然后寻找两个数组的最大值，相加即可，这样时间复杂度就是O(3n),即O(n)

        int max = values[0], ans = 0;
        for (int i = 1; i < values.length; i++) {
            ans = Math.max(ans, max + values[i] - i);
            max = Math.max(max, values[i] + i);
        }
        return ans;
    }

    /*买卖股票的最佳时机*/
    //给定一个数组 prices ，它的第  i 个元素  prices[i] 表示一支给定股票第 i 天的价格。
    //你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
    // 设计一个算法来计算你所能获取的最大利润。
    //返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    public int maxProfitI(int[] prices) {
        // 买入价格：purPrice  prices[i]
        // 卖出价格：offers  prices[j]
        int purPrice = prices[0], offers = 0, profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (purPrice > prices[i]) {
                offers = 0;
                purPrice = prices[i];
                continue;
            }
            if (offers > prices[i]) {
                continue;
            } else offers = prices[i];
            profit = Math.max(profit, offers - purPrice);
        }
        return Math.max(profit, 0);
    }

    public int maxProfitII(int[] prices) {
        // 买入价格：purPrice  prices[i]
        // 卖出价格：offers  prices[j]
        int len = prices.length;

        //此时，
        // 假设第一手买入，第二手的时候我们的选择就是卖出或者不卖
        // 如果第一手不买入，第二手的选择就是买入
        int [][] cashOrStock = new int[len][2];
        cashOrStock[0][0] = 0;//[0]表示持有现金数
        cashOrStock[0][1] = -prices[0];//表示持有的股票
        for (int i = 1; i < prices.length; i++) {
            //prices[i] 进来无非两种情况，
            //1. 大于prices[i-1]，此时prices[i-1]买入，prices[i]等待卖出
            //1. 小于prices[i-1]，待买入为prices[i]
            //1.大于卖出价格offers，则替换卖出价格
            //2.小于买入价格purPrice，则先卖出，然后买入
            cashOrStock[i][0] = Math.max(cashOrStock[i-1][0],cashOrStock[i-1][1]+prices[i]);//[0]表示持有现金数
            cashOrStock[i][1] = Math.max(cashOrStock[i-1][1],cashOrStock[i-1][0]-prices[i]);//表示持有的股票
        }
        return cashOrStock[len-1][0];
    }

    private int sell(int purPrice, int offers) {
        return offers - purPrice;
    }
}
