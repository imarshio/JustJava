package JavaBase;

/**
 * @author masuo
 * @create 2021/7/14 9:11
 * @Description 抽象类与接口
 */
public class AbstractAndInterface {

    static final public int x = 0;

    public abstract class AbstractTest {
        public int i;
        private int j;
        protected int x;
        // static int y;
        final int z = 0;

        // 抽象方法不可以存在方法体
        abstract void fun1();

        // 非抽象方法可以存在方法体
        void fun2() {

        }

    }

    public interface InterfaceTest {
        // 接口中的字段和方法默认都是public的，无需添加
        // 接口声明常量时必须初始化，且都是 public static final的
        static int i = 0; // Modifier 'static' is redundant for interface fields
        final int j = 0;  // Modifier 'final' is redundant for interface fields
        public int y = 0; // Modifier 'public' is redundant for interface fields

        // 接口中的方法都是public abstract
        public abstract void fun1();

        abstract void fun2();

        void fun3();

        default void fun4() {
            // default 方法 是 java8新增的，用于防止修改接口后有大量的实现类也需要跟着修改
        }

        public default void fun5() {
            // Modifier 'public' is redundant for interface methods
            // 默认方法，默认是public的
        }


    }

    class AbstractTest1 extends AbstractTest {

        @Override
        void fun1() {

        }
    }

    InterfaceTest interfaceTest = new InterfaceTest() {

        @Override
        public void fun1() {

        }

        @Override
        public void fun2() {

        }

        @Override
        public void fun3() {

        }
    };
}
