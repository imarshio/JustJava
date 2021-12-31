package leetcode.dynamicPrograming.solutions;

/**
 * @author masuo
 * @data 2021/8/24 8:04
 * @Description 爬楼梯
 */

public class ClambStairs {


    /**
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
     * <p>
     * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
     * <p>
     * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     * <p>
     * 输入：cost = [10, 15, 20]
     * 输出：15
     * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
     *
     * @param cost 最低花费
     * @return 最低花费
     */
    public int minCostClimbingStairs(int[] cost) {
        //记录法，将数据存储起来，耗费内存
        int index = 0;//初始下标为0或1
        int[] sum = new int[cost.length];
        while (index < cost.length) {
            if (index < 2) {
                sum[index] = cost[index];
            } else {
                sum[index] = cost[index] + (Math.min(sum[index - 1], sum[index - 2]));
            }
            ++index;
        }
        return Math.min(sum[cost.length - 1], sum[cost.length - 2]);
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     */
    public int climbStairs(int n) {
        //f : first
        //s : second
        //t : third
        int f = 0, s = 0, sum = 1;
        for (int i = 1; i <= n; i++) {
            f = s;
            s = sum;
            sum = f + s;
        }
        return sum;
    }
}
