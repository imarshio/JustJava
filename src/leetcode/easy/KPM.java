package leetcode.easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author masuo
 * @data 29/3/2022 上午10:45
 * @Description KPM
 */

public class KPM {

    /**
     * 实现strStr()函数。
     * <p>
     * 给你两个字符串haystack 和 needle ，
     * 请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
     * 如果不存在，则返回 -1 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param haystack 主串
     * @param needle   模式串
     * @return 0
     */
    public int strStrBF(String haystack, String needle) {
        // 输入：haystack = "hello","heloll" needle = "ll" 输出：2

        int len1 = haystack.length();
        int len2 = needle.length();
        for (int i = 0; i + len2 <= len1; i++) {
            boolean flag = true;
            for (int j = 0; j < len2; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        // 暴力匹配
        // strStrBF("heloll", "ll");
        // System.out.println(strStrBF("mississippi", "issipi"));

        // KMP
        // System.out.println(strStrKMP("heloll", "aabaabc"));
        // System.out.println(strStrKMP("heloll", "ABCDABD"));
        // System.out.println(strStrKMP("heloll", "ABCDAasasdasdBD"));
        // System.out.println(strStrKMP("heloll", "ABCDasdasdasABD"));
        // System.out.println(strStrKMP("heloll", "ABCaasdasDABD"));
        // System.out.println(strStrKMP("heloll", "ABCDasdasdaABD"));
        // System.out.println(Arrays.toString(getNext(new char[]{'a','a','b','a','a','b','c'})));
        System.out.println(Arrays.toString(getNext(new char[]{'a', 'b', 'a', 'b', 'c'})));
    }

    /**
     * KMP -- 长串不回溯，从短串中
     *
     * @param haystack 主串
     * @param needle   模式串
     * @return i
     */
    private int strStrKMP(String haystack, String needle) {
        int len = needle.length();
        // if(){
        //
        // }
        return -1;
    }

    /**
     * needle的长度需要大于等于2
     * next数组的含义，当发生不匹配时，模式串需要回退的位置
     *
     * @param needle 模式串
     * @param len    模式串长度
     * @param next   next数组
     */
    private void getNext(String needle, int len, int[] next) {
        char[] strs = needle.toCharArray();
        next[0] = -1;
        int i = 1, j = 0;//next 待赋值位置
        while (i < len) {
            while (j > 0 && strs[i] != strs[j]) {
                //ab时，会越界
                j = next[j - 1];
            }
            if (strs[i] == strs[j]) {
                j++;
            }
            next[i] = j;
            i++;
        }
    }

    // -1
    private int[] getNext(char[] sub) {
        int[] next = new int[sub.length];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < sub.length; ++i) {
            while (k != -1 && sub[k + 1] != sub[i]) {
                k = next[k];
            }
            if (sub[k + 1] == sub[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

    private int strStrKMP1(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }

        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            // j指向 模式串 ， i指向 next
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }

        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    public void getNextM1(String needle) {
        //在这种方法里，需要知道next代表的是0到i位置的字符串公共前后缀长度
        int len = needle.length();
        int[] next = new int[len];
        char[] chars = needle.toCharArray();
        next[0] = 0;
        for (int i = 1, j = 0; i < len; i++) {
            while (j > 0 && chars[i] != chars[j]) {
                j = next[j - 1];// ?
                // 再次尝试写前缀表时，还是在这里遇到了问题，至于原因就是回退的位置
                // 首先，我们需要知道chars[i] 和 chars[j] 已经不匹配了，所以我们就不能考虑chars[j]了
                // 我们需要考虑的是不匹配字符之前的字符的最大公共前后缀长度，
                // 回到上面，我们说到，这个next数组的含义表示 {0，i} 位置上的字符串的公共前后缀长度
                // 所以我们就需要让 j = next[j - 1]
            }
            if (chars[i] == chars[j]) {
                j++;
            }
            next[i] = j;
        }
    }

    public void getNextM2(String needle) {
        // len >= 2
        int len = needle.length();
        // 这个next数组的含义是 {0，i - 1}区间的字符串的最长公共前后缀的长度
        int[] next = new int[len];
        char[] chars = needle.toCharArray();
        next[0] = -1;
        next[1] = 0;
        int i = 2, j = 0;
        while (i < len) {
            if (j > 0 && chars[i-1] != chars[j]) {
                j = next[j];
            } else if (chars[i-1] == chars[j]) {
                next[i++] = ++j;
            } else {
                next[i++] = 0;
            }
        }
    }

    public void getNextM3(String needle) {
        // len >= 2
        char[] chars = needle.toCharArray();
        int[] next = new int[chars.length];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < chars.length; ++i) {
            while (k != -1 && chars[k + 1] != chars[i]) {
                k = next[k];
            }
            if (chars[k + 1] == chars[i]) {
                ++k;
            }
            next[i] = k;
        }
    }
}
