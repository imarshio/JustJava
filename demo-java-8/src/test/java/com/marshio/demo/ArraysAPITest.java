package com.marshio.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author masuo
 * @data 16/3/2022 下午2:28
 * @Description Arrays使用
 */

public class ArraysAPITest {

    @Test
    public void test1() {
        int[] numbers = {45, 4, 9, 16, 25};
        // 转成List
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list.stream().sorted().collect(Collectors.toList()));
    }

    @Test
    public void test2() {
        List<Integer> integers = Arrays.asList(45, 4, 9, 16, 25);
        System.out.println(integers.stream().sorted());

        List<Integer> nums = new ArrayList<>();

        nums.add(5);
        nums.add(6);
        nums.add(7);

        nums.forEach(i -> {
            i = i * 10;
            System.out.println(i);
        });

        System.out.println(nums);


        Stream<Object> objectStream = nums.stream().map((Integer) -> {
            return null;
        });

        Stream<Object> objectStream1 = nums.stream().map((Integer) -> add());
    }

    private Consumer<Integer> add() {
        return null;
    }


}
