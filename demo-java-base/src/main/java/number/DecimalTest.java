package JavaBase.number;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @author masuo
 * @data 5/5/2022 上午9:28
 * @Description Decimal 测试
 * 总结：DecimalFormat 主要是通过 ‘0’ 和 ‘#’ 来代表数字占位符，
 */

public class DecimalTest {

    @Test
    public void decimalFormat() {
        double pi = 123.1415926;

        // 取整数部分
        System.out.println(new DecimalFormat("0").format(pi));

        // 取整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));

        // 取四位整数，三位小数，整数部分不足的地方会往前补0
        System.out.println(new DecimalFormat("0000.000").format(pi));

        // 取两位整数，三位小数，
        // 注意，虽然取的是两位整数，但是他会默认取所有的整数部分，即 0 代表整数部分，00也代表整数部分，当长度超过整数长度时会往前补0
        System.out.println(new DecimalFormat("00.000").format(pi));

        // 取整数部分，八位小数，同理，小数不足的部分也会补0
        System.out.println(new DecimalFormat("0.00000000").format(pi));

        // 取整数部分:new DecimalFormat("#") <==> new DecimalFormat("0")
        System.out.println(new DecimalFormat("#").format(pi));
        System.out.println(new DecimalFormat("0").format(pi));

        // 光速
        long lightSpeed = 299792458L;

        // 每三位以 ',' 隔开，从后往前
        System.out.println(new DecimalFormat(",000").format(lightSpeed));


    }
}
