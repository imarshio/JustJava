package com.marshio.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author masuo
 * @data 27/12/2021 下午4:47
 * @Description Java8新特性之 Stream API
 * Stream是一个来自数据源的元素队列，支持聚合操作
 * 1.元素是特定类型的对象，形成一个队列，Java中的Stream不会存储对象，而是按需计算
 * 2.数据源： 流的来源，可以是集合、数组、IO流，generator生成器
 * 3.聚合操作：类似SQL语句，如filter，map，reduce，find，match，sorted
 * 和collection不同，Stream还有两个基础特征
 * Pipelining：中间操作都会返回流对象本身，这样多个操作可以串联成一个管道，如同流式风格。这样做可以对操作进行优化，如优化延迟的短路
 * 内部迭代：以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现
 */

public class StreamAPITest {

    @Test
    // 1.生成流,内部流处理
    public void generateStream() {

        System.out.println("********Arrays.asList*********");
        // 生成数据源 - 集合、数组...
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        strings.forEach(s -> System.out.println(s + " "));

        System.out.println("********Stream.of/List.stream*********");
        // 数组 转化成 stream
        /*
         * Stream.of:类似于Arrays.asList()
         * */
        Stream<List<String>> stream1 = Stream.of(strings, strings, strings);
        Stream<String> stream2 = Stream.of("abc", "", "bc", "efg", "abcd", "", "jkl");
        Stream<String> stream3 = strings.stream();

        System.out.println(stream1);
        System.out.println(stream2);
        System.out.println(stream3);

        System.out.println("********Stream.filter*********");
        // stream() - 串行流 过滤
        List<String> collect = strings.stream().filter(string -> !(string.isEmpty())).collect(Collectors.toList());
        // 迭代输出数组中的数据
        collect.forEach(s -> System.out.print(s + " "));
        // filter() - 设置条件过滤元素,其结果仍然是一个stream类
        Stream<String> stream4 = strings.stream().filter(string -> (string.contains("b")));
        // collect(),将stream转化为数组
        collect = stream4.collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("********Stream.parallelStream*********");
        // parallelStream() - 并行流
        System.out.println(strings.parallelStream().filter(s -> (!s.isEmpty())).count());

        collect.forEach(System.out::println);

        System.out.println("********Random.ints().limit*********");
        Random random = new Random();
        System.out.println(random.nextInt(20));
        random.ints().limit(10).forEach(i -> System.out.print(i + " "));

        System.out.println("********Stream.map*********");
        // map 用于映射每个元素的对应的结果
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> numberList = numbers.stream().map(i -> (i * i)).collect(Collectors.toList());
        numberList.forEach(System.out::println);


        // limit() - 获取指定数量的流
        random.ints().limit(10).forEach(System.out::println);
    }

    // Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串
    @Test
    public void streamOP() {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
