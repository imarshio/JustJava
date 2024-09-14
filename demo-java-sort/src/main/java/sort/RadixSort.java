package sort;

// import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author masuo
 * @data 2021/9/18 9:58
 * @Description 基数排序
 */

public class RadixSort {

    // @Test
    public void test() {
        int num = 54;
        getDigitNum(num, 1);
    }

    // @Test
    public void sort() {
        int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0, 11, 22, 41, 51, 82, 92, 72, 42, 85, 10};

        // 获取最大值
        int max = Arrays.stream(unsort).max().getAsInt();
        // 获取最大值的长度,如数组中最大值为125，那么digits = 3，
        int digits = String.valueOf(max).length();
        // 获取最小值
        int min = Arrays.stream(unsort).min().getAsInt();

        // 从0到9一共10个数字，所以数组长度为10
        int length = 10;

        // 添加桶
        ArrayList<ArrayList<Integer>> sorted = new ArrayList<>();


        // 循环位数，从个位数开始，个位数就是 digits = 0
        for (int i = 0; i < digits; i++) {
            // 每次进入都需要重新给桶赋值
            sorted.clear();
            for (int n = 0; n < length; n++) {
                sorted.add(new ArrayList<>());
            }
            // 获取数组的每一位数字，0是个位数，1是十位数
            for (int j : unsort) {
                // 按照个位数数值将其放入桶中
                int digitNum = getDigitNum(j, i);
                ArrayList<Integer> bucket = sorted.get(digitNum);
                bucket.add(j);
            }
            // 按照顺序取出放到unsort里
            int size = 0;
            for (ArrayList<Integer> integers : sorted) {
                for (Integer integer : integers) {
                    unsort[size] = integer;
                    ++size;
                }
            }
        }


        // 获取
        for (int i : unsort) {
            System.out.println(i);
        }
    }

    /**
     * 获取num在digit位上的值，即当 num 为1205.digit为0时，返回5
     *
     * @param num   待操作数字
     * @param digit 位数（个位数，十位数，百位数。。。）
     * @return num在digit位上的值，一个数字【0，9】
     */
    private int getDigitNum(int num, int digit) {
        // num是传进来的数字，
        char[] nums = String.valueOf(num).toCharArray();
        if (nums.length <= digit) {
            return 0;
        }
        return nums[nums.length - digit - 1] - 48;
    }
}
