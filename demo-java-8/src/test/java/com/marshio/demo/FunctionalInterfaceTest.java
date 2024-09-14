package com.marshio.demo;

/**
 * @author masuo
 * @data 31/12/2021 下午3:44
 * @Description 函数式接口
 * <p>
 * 使用FunctionalInterface这个注解需要提供一个方法
 * 但是不能在写另外的方法
 */

@FunctionalInterface
interface FunctionalInterfaceTest {
    void methodToImpl(String msg);
}

