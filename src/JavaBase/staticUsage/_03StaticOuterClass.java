package JavaBase.staticUsage;

import org.junit.Test;

/**
 * @author masuo
 * @create 2021/7/11 12:58
 * @Description 静态内部类与非静态内部类
 *  - 根据下面这些例子，可以知道，静态的变量、类不可以在非静态方法中使用
 */
public class _03StaticOuterClass {
    class NonStaticInnerClass{
        // 非静态内部类
        int count;
    }

    static class StaticInnerClass{
        // 静态内部类不能访问外部类的非静态变量和方法。
    }

    private static class innerClass{
        // 私有静态内部类
        int i;
        int j;
    }

    @Test
    public void nonStaticInnerClassTest01() {
        NonStaticInnerClass nsic = new NonStaticInnerClass();
        System.out.println(nsic.count);
    }

    @Test
    public static void nonStaticInnerClassTest02() {
        // 非静态内部类不能在静态方法中引用
        // NonStaticInnerClass nsic = new NonStaticInnerClass();
        // System.out.println(nsic.count);
    }

    public static void main(String[] args) {
        //非静态内部类不能单独声明
        //NonStaticInnerClass nsic = new NonStaticInnerClass();//'JavaBase.staticUsage._03StaticOuterClass.this' cannot be referenced from a static context,该类不能从静态上下文引用

        //静态内部类可以不依赖实例对象直接声明
        StaticInnerClass sic = new StaticInnerClass();

        //声明外部类的实例对象
        _03StaticOuterClass soc = new _03StaticOuterClass();

        //利用外部类的实例对象声明非静态内部类
        NonStaticInnerClass nsic = soc.new NonStaticInnerClass();


    }
}
