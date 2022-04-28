package newcoder.top101;

/**
 * @author masuo
 * @data 22/4/2022 下午3:11
 * @Description 寻找峰值
 */

public class Solution_019FindPeak {

    public int findPeakElement(int[] nums) {
        // 给定一个长度为n的数组nums，请你找到峰值并返回其索引。
        // 数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
        // nums[-1] = nums[n] = −∞

        //数据范围：
        // 1 ≤ nums.length ≤ 2×10^5
        // -2^31 <= nums[i] <= 2^31 - 1
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        // 我们假设此数组只有一个波峰，
        // 那么从左边开始到波峰之前数组一定是递增的，
        // 同理，从右边开始到波峰之前数组一定是递减的，所以我们利用二分查找此波峰
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 2);

            if (nums[mid] > nums[mid + 1]) {
                //右边是往下，不一定有坡峰
                right = mid;
            } else {
                //右边是往上，一定能找到波峰
                left = mid + 1;
            }
        }
        return right;
    }
}
