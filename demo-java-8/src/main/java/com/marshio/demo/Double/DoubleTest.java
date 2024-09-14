package com.marshio.demo.Double;

import java.math.BigDecimal;

/**
 * @author marshio
 * @desc ...
 * @create 2024/9/13 13:51
 */
public class DoubleTest {

    public static void main(String[] args) {
        int num = 83;
        BigDecimal threshold = BigDecimal.valueOf(num / 100.0);
        int MERGERECORDCOUNT = 10;
        BigDecimal result = threshold.add(BigDecimal.valueOf(MERGERECORDCOUNT / 100.0));
        System.out.println(result);
    }
}
