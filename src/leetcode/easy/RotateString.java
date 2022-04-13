package leetcode.easy;

/**
 * @author masuo
 * @data 7/4/2022 下午3:55
 * @Description 旋转字符串
 */

public class RotateString {
    public boolean rotateString(String s, String goal) {

        int len1 = s.length();
        int len2 = goal.length();
        if (len1 != len2) {
            return false;
        }
        // 双指针
        int i = 0, j = 0;
        // i 最多移动2 * len - 1 步，在移动就回到了最开始的位置
        while (i < 2 * len1 - 1) {
            // 旋转，找到与goal 的第一个字符匹配的字符
            while (i < len1 && goal.charAt(j) != s.charAt(i)) {
                i++;
            }
            int temp = i;
            // 找到之后
            do {
                temp++;
                j++;
            } while (j < len1 && s.charAt(temp % len1) == goal.charAt(j));
            // 跳出情况有两种情况 1、j  2、不等
            if(j == len1){
                return true;
            }
            j = 0;
            i++;
        }
        return false;
    }
}
