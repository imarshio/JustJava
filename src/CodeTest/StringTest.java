package CodeTest;

import org.junit.Test;

/**
 * @author masuo
 * @data 16/3/2022 上午10:01
 * @Description 字符串操作测试
 */

public class StringTest {


    /**
     * 字符串 根据 '.' split 然后再拼接
     */
    @Test
    public void stringSplitTest() {
        String str = "192.168.64.136:8080";

        String[] ary = str.split(":");
        str = ary[0];

        String[] strs = str.split("\\.");
        boolean isIP = false;
        for (String domain : strs) {
            isIP = Integer.parseInt(domain) >= 0 && Integer.parseInt(domain) <= 255;
        }
        if (isIP) {
            System.out.println(strs[0] + "." + strs[1] + "." + strs[2] + "." + strs[3]);
        }
    }
}
