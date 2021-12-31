package leetcode.plan_1_arithmetic.solution;

/**
 * @author masuo
 * @data 2021/8/19 12:50 -
 * @Description day 2
 */

public class Solution_2 {

    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * nums = [-4,-1,0,3,10]
     * out:[0,1,9,16,100]
     *
     * @param nums 非递减顺序 排序的整数数组
     * @return 非递减顺序 排序 每个数字的平方 组成的新数组
     * 12：50-13：16
     * 用时：26 m
     */
    public int[] sortedSquares(int[] nums) {
        //平方之后都是大于等于0的，且由于数组是非递减，所以两侧的绝对值一定比中心的绝对值大，
        // 在两侧建立两个指针，对比两个指针所指向的元素大小然后插入数组
        int[] temp = new int[nums.length];
        int left = 0, right = nums.length - 1;
        int p = temp.length - 1;
        while (left <= right) {
            if (left == right) {
                temp[p] = Math.abs(nums[left]) * Math.abs(nums[left]);
                return temp;
            }
            if (Math.abs(nums[left]) == Math.abs(nums[right])) {
                //相等时，先放左侧，再放右侧
                temp[p] = Math.abs(nums[left]) * Math.abs(nums[left]);
                p--;
                left++;
                temp[p] = Math.abs(nums[right]) * Math.abs(nums[right]);

                right--;
            } else if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                //当左侧绝对值大于右侧绝对值时，将左侧放到数组的最后一位
                temp[p] = Math.abs(nums[left]) * Math.abs(nums[left]);

                left++;
            } else {
                //当左侧绝对值小于右侧绝对值时，将右侧放到数组的最后一位
                temp[p] = Math.abs(nums[right]) * Math.abs(nums[right]);

                right--;
            }
            p--;
        }

        return temp;
    }


    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * @param nums 数组
     * @param k    向右移动,k 是非负数 --> K>=0
     *             13：19 -40(sleep)-16:10
     *             存在缺陷，当数组元素的值是2的倍数且k是2的时候会循环
     */
    public void rotateOne(int[] nums, int k) {
        //首先一个指针指向最开始的位置，长度一定，下标加上k对7求余就是该下标位置上的数据的下一个位置


        int begin = 0, num = 0, next, temp = 0;//次数
        System.out.println("******");
        while (num < nums.length) {
            next = (begin + k) % nums.length;//下标+k求余
            int hold;
            if (num == 0) {
                hold = nums[num];
            } else {
                hold = temp;
            }

            temp = nums[next];
            nums[next] = hold;
            nums[begin] = temp;
            //swap
            begin = next;
            num++;


            for (int i : nums) {
                System.out.print(i + " ");
            }
        }

        for (int i : nums) {
            System.out.print(i + " ");
        }

    }


    public void rotateTwo(int[] nums, int k) {
        //首先一个指针指向最开始的位置，长度一定，下标加上k对7求余就是该下标位置上的数据的下一个位置
        int length = nums.length;
        int num = 0;
        System.out.println("******");
        for (int i = 0; i < length; i++) {

            int current = i;
            int prev = nums[i];
            do {
                if(num==length){
                    break;
                }
                //一次执行，步数较小的时候可以跳多次
                int next = (i + k) % length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                i = next;
                num++;
            } while (current != i);
        }

        for (int i : nums) {
            System.out.print(i + " ");
        }

    }
}
