package JavaBase;

/**
 * @author masuo
 * @create 2021/7/14 11:01
 * @Description super
 */
public class SuperExample {


    public static void main(String[] args) {
        SuperExample se = new SuperExample();
        se.superTest();
    }

    public void superTest() {

        Son son = new Son(1, 2, 3);
        son.fun();
    }

    class Father {
        private int i;
        private int j;

        public Father() {
            // 默认构造函数
        }

        public Father(int i, int j) {
            // 带参数非默认构造函数
            this.i = i;
            this.j = j;
        }

        void fun() {
            System.out.println("father fun");
        }
    }

    class Son extends Father {
        private int i;
        private int j;
        private int x;

        public Son() {
            // 默认构造函数
        }

        public Son(int i, int j, int x) {
            super(i, j);
            this.x = x;
        }

        @Override
        public void fun() {
            super.fun();
            System.out.println("son fun");
        }
    }
}

