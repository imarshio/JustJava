package newcoder.top101;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author masuo
 * @data 18/4/2022 上午9:42
 * @Description 大数加法
 */

public class Solution_001BigInteger {

    public String solve(String s, String t) {
        // write code here
        int len1 = s.length();

        // ArrayList<Integer> sl = new ArrayList<>();
        // sl.stream().reduce()
        int len2 = t.length();
        int max = Math.max(len1, len2);
        char[] sum = new char[max + 1];

        //进位
        int carry = 0;
        int index = 0;
        for (int i = max; i >= 0; i--,index++) {
            int ad = carry;

            if (index < len1) {
                ad += (s.charAt(len1 - index - 1) - 48);
            }
            if (index < len2) {
                ad += (t.charAt(len2 - index - 1) - 48);
            }
            sum[i] = (char) (ad % 10 + 48);

            // 更新carry，进位
            carry = ad / 10;
        }

        //拼接字符串
        StringBuilder sb = new StringBuilder(max + 1);
        for (char c : sum) {
            sb.append(c);
        }
        String rt = sb.toString();
        if (rt.startsWith("0")) {
            rt = rt.substring(1);
        }
        return rt;
    }

    public String solve1 (String s, String t) {
        // write code here
        BigInteger bi1 = new BigInteger(s);
        BigInteger bi2 = new BigInteger(t);
        bi1 = bi1.add(bi2);
        return bi1.toString();
    }

    @Test
    public void test() {
        System.out.println(solve("0", "0"));
    }

}
