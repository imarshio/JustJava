package leetcode.plan_1_arithmetic.solution;


import com.ms.math.Random;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author masuo
 * @data 2021/8/23 11:07
 * @Description leet code day6
 */

public class Solution_6 {


    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: s = "abcabcbb" abcdecbb
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
     *
     * @param s 字符串 >=0
     * @return 不含有重复字符的 最长子串 的长度
     */
    public int lengthOfLongestSubstring(String s) {
        //滑动窗口,暴力解法，时间复杂度为O（N*N）
        int left = 0, length = s.length();
        if (length <= 1) {
            return length;
        }
        //length>=2
        int max = 1;
        for (int i = 1; i < length; i++) {
            //暴力解法
            if (isUnique(s.substring(left, i + 1))) {
                max = Math.max(max, i - left + 1);
            } else {
                left++;
            }

            //非暴力解法


        }
        return max;
    }


    public int lengthOfLongestSubstring2(String s) {
        //时间复杂度为O（N）
        int left = 0, length = s.length(), max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < length; right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(map.get(c), left);
            }
            max = Math.max(max, right - left + 1);
            map.put(c, right + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring3(String s) {
        //窗口滑动，最优解
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    /**
     * 该字符串是否没有重复，如果没有则返回true，如果有，则返回false
     *
     * @param s 字符串
     * @return 0/1
     */
    private boolean isUnique(String s) {
        int begin = 0;
        while (begin < s.length()) {
            if (s.substring(begin + 1).contains(String.valueOf(s.charAt(begin)))) {
                return false;
            }
            begin++;
        }
        return true;
    }

    private boolean isUnique2(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set.size() == s.length();
    }

    /**
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
     * <p>
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return 0/1
     * <p>
     * 超出时间限制
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        for (int start = 0; start < s2.length(); start++) {
            int length = s1.length();
            String temp = s1;
            int cur = start;
            while (temp.contains(String.valueOf(s2.charAt(cur))) && temp.length() > 0) {
                temp = temp.replaceFirst(String.valueOf(s2.charAt(cur)), "");
                cur++;
                length--;
                if (length == 0) {
                    return true;
                }
                if (cur == s2.length()) {
                    return false;
                }
            }
        }

        //System.out.println(Random.origin());
        return false;
    }

    public boolean checkInclusion1(String s1, String s2) {
        //使用滑动窗口方法，判断相同长度的字符串内是否每个字符串都有相同的数量
        //这个数组可用于存储每个字符有几个
        int[] num = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            int s = s1.charAt(i);
            num[s]++;
        }
        //此时num是一个存储了s1每个字符数量的数组
        for (int left = 0, right = s1.length()-1; right < s2.length(); right++) {
            if(checkDiff(num.clone(),s2.substring(left,right+1))) {
                //
                return true;
            }else left++;
        }
        return false;
    }

    private boolean checkDiff(int[] num, String s) {
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            int n = s.charAt(i);
            num[n]--;
            if(num[n]<0){
                return false;
            }
        }

        //得到对比之后的num，判断num里的数值是否都是0
        for (int i = 97; i < num.length; i++) {
            if(num[i]!=0){
                return false;
            }
        }

        return true;
    }
}
