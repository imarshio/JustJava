package newcoder.top101;

import org.junit.Test;

/**
 * @author masuo
 * @data 20/4/2022 下午4:45
 * @Description 二分
 */

public class Solution_017ErfenSearch {

    public int search(int[] nums, int target) {
        // write code here
        return search(nums, target, 0, nums.length - 1);
    }

    public int search(int[] nums, int target, int L, int R) {
        if (L == R) {
            return nums[L] == target ? L : -1;
        }
        int mid = (L + R) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return search(nums, target, L, mid - 1);
        } else {
            return search(nums, target, mid + 1, R);
        }

    }

    @Test
    public void test() {
        search(new int[]{-1, 0, 3, 4, 6, 10, 13, 14}, 13);
    }
}
