package JavaBase;

import JavaCore.MyJVM.Heap.EdenSurvivor;

/**
 * @author: masuo
 * @data: 2021/8/12 22:04
 * @Description:
 */

public class StringTest {

    public static void main(String[] args) {
        StringTest st = new StringTest();
        st.test1();
        st.test2();
        st.test3();
        st.test4();
        st.test5();
        st.test6();
        st.test7();

    }

    private void test7() {

        EdenSurvivor es = new EdenSurvivor();
        System.out.println(es);
    }

    private void test6() {
        System.out.println("math");
        for (int i = 0; i < 100; i++) {
            //Math.random()  ==>  [0.0,1.0)
            int d = (int) (Math.random() * 10);//  0 <= d <  10,    [0,10)
            System.out.println(Math.random());
        }


        System.out.println(System.nanoTime());
    }

    private void test5() {

        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        sb.length();
        String s = sb.toString();
        s.length();
    }


    /**
     * 例题
     * 首先，new String()到底会创建几个对象？两个，
     * 一个是new关键字在堆空间创建的
     * 另一个对象是字符串常量池中的对象，字节码指令：类ldc
     * <p>
     * 思考？
     * String s = new String("a") + new String("b");创建了几个对象？
     * 对象1：new StringBuilder（）
     * 对象2：new String("a")
     * 对象3：对应常量池的a
     * 对象4：new String("b")
     * 对象5：对应常量池的b
     * <p>
     * 深入：
     * String Builder的toString()
     * 对象6：new String(”ab“)
     * 强调：toString的调用没有在常量池中生成”ab“
     */
    private void test4() {
        System.out.println("*********** 4 ************");
        String s1 = new String("1");//new 的对象都放在堆中
        // String s1 = "a" + "b";
        s1.intern();//放入字符串常量池
        String s2 = "1";//直接声明是放入字符串常量池
        System.out.println(s1 == s2);//false

        String s3 = new String("1") + new String("1");//s3变量记录的地址为：new String("11")
        //执行完上一句代码之后，字符串常量池中是否存在“11”？，由上面得知，这涉及到字符串拼接操作且涉及到了变量，所以不会存在
        s3.intern();//在字符串常量池中 “生成”  “11”.
        // 如何理解这个生成？
        //因为JDK８中字符串常量池转移到了堆区，当字符串常量池中，没有创建此对象，但是堆区中存在该对象了，
        // 此时调用intern（）方法时，处于节省空间的想法，将堆区的此对象的地址作为常量池的对象引用，即此时常量池中该对象存放的是堆空间该对象的地址
        //这一步代码使用的上一步代码执行的结果，即常量池中生成的“11”
        String s4 = "11";//此时常量池中没有创建对象，而是创建一个指向堆空间中new String("11")的地址
        System.out.println(s3 == s4);//false-->true，所以这里是true（在JDK8）
    }


    /**
     * 如何保证变量s指向字符串常量池中的数据？
     * 1.直接声明：  String s = "111";
     * 2.使用intern()方法
     * String s = new String("111").intern();
     * String s = new StringBuffer().toString().intern();
     */
    private void test3() {
        String s1 = "111";
        String s2 = new String("111").intern();
        String s3 = new StringBuffer().toString().intern();
    }

    /**
     * 查看append与拼接操作的效率差距
     * 虽然测试会受到硬件的一定影响，但是在一定程度上影响是相对的，所以对于测试结果并没有影响
     * <p>
     * 结果很明显，append是非常快的
     * 至于拼接慢的原因是因为每次拼接都需要新建一个String对象
     * 而append只需要建立一个String Builder对象，剩下只是往里面添加数据罢了，
     * 当然String Builder也是长度固定的，但是由于底层是数组实现，每次扩容都会废弃之前的数组，
     * 所以在我们调用String Builder时，要尽量根据数组长度来初始化长度，这样可以减少扩容次数，也就可以减少中间时间
     */
    private void test2() {

        appendTest(100000);//100000：8----》初始化之后：100000：15

        pinjieTest(1000);//6164
    }

    private void appendTest(int timeLeval) {
        long start = System.currentTimeMillis();
        StringBuilder s = new StringBuilder(100000);
        for (int i = 0; i < timeLeval; i++) {
            s.append("a");
        }
        long end = System.currentTimeMillis();

        System.out.println("times:" + (end - start));
    }

    private void pinjieTest(int timeLeval) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < timeLeval; i++) {
            s += "a";
        }
        long end = System.currentTimeMillis();

        System.out.println("times:" + (end - start));
    }

    public void test1() {
        String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = "hello" + "world";//编译期优化

        //拼接前后出现了变量，则相当于在堆空间中new String().具体内容为拼接的结果。
        String s5 = s1 + "world";
        String s6 = "hello" + s2;
        String s7 = s1 + s2;
        /* 如下展示：String s5 = s1+"world";的执行细节
         * 第一步：新建StringBuilder对象
         *          StringBuilder sb = new StringBuilder();
         * 第二步：在sb实例化对象的末尾依次添加
         *          sb.append(s5);-->sb.append("hello");
         * 第三步：继续添加
         *          sb.append("world");
         * 第四步：返回String而不是StringBuilder
         *          sb.toString();
         *          这一步约等于：new String("helloworld");
         *  补充：在JDK5.0之后使用的是String Builder，之前使用的是String Buffer
         */


        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s5 == s6);
        System.out.println(s5 == s7);
        System.out.println(s6 == s7);
        System.out.println(s3 == s7);

        //这里会产生一个特例,
        final String s9 = "1";
        final String s10 = "2";
        final String s11 = "12";
        final String s12 = s9 + s10;
        System.out.println(s11 == s12);//true
        //被final声明的变量在编译器已经变成了常量，

        //intern方法，需要在常量池中做校验，如果常量池中有则返回地址，
        // 如果没有则在常量池中加载一份此对象，并返回地址
        String s8 = s6.intern();
        System.out.println(s3 == s8);
    }

}
