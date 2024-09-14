package com.marshio.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/1 17:59
 */
public class DecimalTest {

    public static void main(String[] args) {
        BigDecimal number = new BigDecimal("27028420");
        System.out.println(number.divide(new BigDecimal("100000000"), 2, RoundingMode.HALF_UP));
    }
}
