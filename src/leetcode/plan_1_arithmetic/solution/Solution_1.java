package leetcode.plan_1_arithmetic.solution;


/**
 * @author masuo
 * @data 2021/8/18 11:07
 * @Description leet code day1
 */

public class Solution_1 {

    /**
     * 给定一个n个元素有序的（升序）整型数组 nums 和一个目标值 target ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * @param nums   整型数组
     * @param target 目标值
     * @return 下标
     */
    public int search(int[] nums, int target) {
        //因为数组有序，我们使用二分法时间复杂度为 O（logN）
        //int n = nums.length/2;//中间值


        int piovt;//中间值,轴，位运算
        int left = 0;
        int right = nums.length - 1;//left和right指向实际下标位置

        while (left <= right) {
            piovt = ((right - left) >> 1) + left;
            if (target == nums[piovt]) {
                return piovt;
            }
            if (target < nums[piovt]) {
                //即target在轴的左侧，需改变right指针
                right = piovt - 1;

            } else {
                //
                left = piovt + 1;
            }
        }
        return -1;

    }

    private int[] halfNums(int[] nums, int start, int end) {
        int[] temp = new int[end - start];
        int len = 0;
        for (int i = start; i < end; i++) {
            temp[len] = nums[i];
            len++;
        }
        return temp;
    }

    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * <p>
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * <p>
     * @param n 个版本
     * @return 调用 API 的次数
     */
    public int firstBadVersion(int n) {
        int first = n;
        int povit,left = 1,right = n;
        while(left<=right){
            povit = ((right-left)>>1)+left;
            if(isBadVersion(povit)){
                //证明前面还有错误的版本
                first = povit;
                right = povit-1;
            }else{
                //说明错误版本在后面
                left = povit+1;
            }
        }
        return first;
    }

    private boolean isBadVersion(int povit) {
        return false;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 请必须使用时间复杂度为 O(log n) 的算法。
     *
     * @param nums   排序数组
     * @param target 目标值
     * @return 索引/被按顺序插入的位置
     * <p>
     * 注意：O(log n)
     */
    public int searchInsert(int[] nums, int target) {

        //因为限定了时间复杂度为O（logN）的算法，那么这里就可以使用二分法

        //声明轴（中间值），左右边界的下标值
        int piovt = 0, left = 0, right = nums.length - 1;

        while (left <= right) {
            piovt = ((right - left) >> 1) + left;
            if (target == nums[piovt]) {
                return piovt;
            }
            if (target < nums[piovt]) {
                //target左侧
                right = piovt - 1;

            } else {
                left = piovt + 1;
            }

        }
        return target > nums[piovt] ? piovt + 1 : piovt;
    }
}
