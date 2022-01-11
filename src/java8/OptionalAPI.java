package java8;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author masuo
 * @data 11/1/2022 上午11:01
 * @Description Optional API 是一个存储单值的容器
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。
 * Optional 提供很多有用的方法，这样我们就不用显式进行空值检测。
 * Optional 类的引入很好的解决空指针异常。
 */

public class OptionalAPI {

    @Test
    public void optionalTest(){
        /* 生成Optional
        * 构造函数被私有化，我们只能通过共有api调用生成
        * Optional有三种生成方式
        */
        // 返回一个空的Optional
        Optional<String> optionalNull1 = Optional.empty();

        //Optional.ofNullable 允许传递为空的参数，且生成的Optional允许为空
        Optional<String> optionalNull2 = Optional.ofNullable(null);

        // Optional.of 传递的参数不为空，且生成的Optional不为空
        Optional<String> optionalNotNull = Optional.of("123");

        //其他api
        //isPresent 判断改Optional是否有值，没值就返回false，有值就返回true
        System.out.println(optionalNotNull.isPresent());
        System.out.println(optionalNull2.isPresent());

        //orElse 判断该Optional是否为空，为空就返回参数，不为空就返回它的值本身
        System.out.println(optionalNull2.orElse("1"));
        System.out.println(optionalNotNull.orElse("1"));

        //get 返回该值,为空抛异常
        System.out.println(optionalNotNull.get());
    }
}
