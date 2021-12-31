package java8;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author masuo
 * @data 27/12/2021 下午4:47
 * @Description java8 新特性之Lambda表达式 以及 函数式接口
 */

public class Lambda {

    //Lambda 也称 闭包，
    // 允许我们将函数当成参数传递给某个方法
    // 或者把代码本身当作数据处理
    @Test
    public void test0(){

        //
        Arrays.asList("a","b","c","d").forEach(System.out::println);
        Arrays.asList("a","b","c","d").forEach(e->{
            System.out.print(e + "    ");
        });
    }

}
