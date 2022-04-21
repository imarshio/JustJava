package newcoder.top101;

/**
 * @author masuo
 * @data 19/4/2022 上午9:25
 * @Description 最长公共子串
 */

public class Solution_127LCS {

    /**
     * longest common substring
     *
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String getLcs(String str1, String str2) {
        // KMP算法，求较短字符串的next数组
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        int maxLastIndex = 0, maxLength = 0;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1] > maxLength) {
                        maxLength = dp[i + 1][j + 1];
                        maxLastIndex = i;
                    }
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }

        return str1.substring(maxLastIndex - maxLength + 1, maxLastIndex + 1);
    }

    // 求next数组
    private int[] getNext(String s) {
        int len = s.length();
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        int i = 2, j = 0;
        while (i < len) {
            if (s.charAt(i - 1) == s.charAt(j)) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


}
