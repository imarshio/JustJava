package JavaBase;

/**
 * @author masuo
 * @create 2021/7/11 12:58
 * @Description 静态内部类与非静态内部类
 */
public class StaticOuterClass {
    class NonStaticInnerClass{

    }

    static class StaticInnerClass{
        //静态内部类不能访问外部类的非静态变量和方法。
    }

    private static class innerClass{
        //私有静态内部类
        int i;
        int j;
    }

    public static void main(String[] args) {
        //非静态内部类不能单独声明
        //NonStaticInnerClass nsic = new NonStaticInnerClass();//'JavaBase.StaticOuterClass.this' cannot be referenced from a static context,该类不能从静态上下文引用

        //静态内部类可以不依赖实例对象直接声明
        StaticInnerClass sic = new StaticInnerClass();

        //声明外部类的实例对象
        StaticOuterClass soc = new StaticOuterClass();

        //利用外部类的实例对象声明非静态内部类
        NonStaticInnerClass nsic = soc.new NonStaticInnerClass();


    }
}
