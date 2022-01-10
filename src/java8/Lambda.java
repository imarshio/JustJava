package java8;

import org.junit.Test;


/**
 * @author masuo
 * @data 27/12/2021 下午4:47
 * @Description java8 新特性之Lambda表达式 以及 函数式接口，
 * 这里有两个非常重要的地方，
 * 1.接口：顾名思义，这是一个接口，在Java8中，所有的Lambda的类型都是一个接口，，，的实例化对象
 * 2.函数：我们都知道，在Java中，接口都需要被实例化对象实现，上面说明了他已经有了一个实例化对象了，那么接下来就是实现接口的方法了，类似于之前的匿名函数
 * 但是，这种函数接口有一个前提，就是这个接口只有一个函数需要被实现
 * 表达式为：
 * （parameters ） -> expression
 * or
 * (parameters ) -> {statements}
 * <p>
 * 特点：
 * 1.可选类型声明：不需要声明参数类型
 * 2.可选的参数圆括号：一个参数无需定义圆括号，但多个参数要定义圆括号
 * 3.可选的大括号：如果主体只包含了一条语句，就不需要大括号
 * 4.可选的返回关键字：如果主体只有一个表达式返回值，则编译器自动返回值，大括号需要指定表达式返回了一个数值
 * <p>
 * 参考：https://baijiahao.baidu.com/s?id=1685401324435208513&wfr=spider&for=pc
 */

public class Lambda {

    //Lambda 也称 闭包，
    // 允许我们将函数当成参数传递给某个方法
    // 或者把代码本身当作数据处理
    @Test
    public void test0() {

        //例子
        //需要类型声明
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println(addition.getClass());
        System.out.println(addition.operation(11, 22));
        //不需要类型声明
        MathOperation subtraction = (a, b) -> a - b;
        System.out.println(subtraction.operation(50, 12));

        //大括号中的返回语句
        MathOperation multiplication = (a, b) -> {
            return a * b;
        };
        System.out.println(multiplication.operation(10, 3));

        //无大括号返回语句
        MathOperation division = (a, b) -> a / b;
        System.out.println(division.operation(50, 2));

        //不用括号
        GreetingService greetingService = message -> System.out.println("hello " + message);
        greetingService.sayMessage("这是lambda 表达式！无括号版本。");

        //用括号
        GreetingService greetingService1 = (message) -> System.out.println("hello " + message);
        greetingService1.sayMessage("这是lambda 表达式！有括号版本。");

    }

    @Test
    public void test1() {
        FunctionalInterfaceTest testLambda = (message) -> System.out.println("这是一个函数式接口的实现，" + message);
        testLambda.methodToImpl("FunctionalInterfaceTest");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

}
