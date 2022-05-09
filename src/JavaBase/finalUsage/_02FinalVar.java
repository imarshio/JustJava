package JavaBase.finalUsage;

/**
 * @author masuo
 * @data 6/5/2022 下午5:03
 * @Description final 修饰变量
 *  -- 显示赋值的变量在编译器完成赋值
 */

public class _02FinalVar {

    // 编译期就已经完成了赋值
    private final int i = 0;

    // final 修饰的变量必须赋初始值，Variable 'j' might not have been initialized
    // private final int j;

    static class TestFinal1 {
        // 普通成员变量须在声明时赋值、或在代码块中赋值、或在构造器中赋值
        final int b = 0;

        final int a;
        {
            a = 2;
        }

        final int c;

        public TestFinal1() {
            c = 3;
        }

        // 类变量即static声明的变量同样需要在声明时赋值、或在静态代码块中赋值
        final static int d = 5;

        final static int e;
        static {
            e = 6;
        }

        // final修饰局部变量需在使用前显示初始化
        public void test() {
            final int f = 7;

            final int g;

            g = 8;
            // 使用前赋值即可
            System.out.println(g);
        }
    }

    static class TestFinal2 {

        // 局部变量 a、b
        public void test(final int a) {
            final int b = 10;

            // 匿名内部类  访问局部变量
            new Thread(() -> {
                System.out.println(a);
                System.out.println(b);
            }).start();
        }
    }

    static class TestFinal3 {

        private int x = 12;
        // 局部变量 a、b
        public void test(final int a) {
            final int b = 10;

            // 局部内部类  访问局部变量、全局变量
            class InnerClass {
                public void print() {
                    System.out.println(x);
                    System.out.println(b);
                }
            }
        }
    }


}
