package CodeTest;

import com.ms.math.Random;


import java.util.HashSet;

/**
 * @author masuo
 * @data 2021/8/13 10:18
 * @Description
 */


public class JarTest {

    /**
     * 需求：
     * 首先，随机数都是 大于等于0的数
     * 随机数区间为，右边界减去左边界，
     * 随机数起点取决于，左边界
     * 综上所述，区间等于：Math.random()*(right-left)+left
     *
     * 四种情况，每种情况都需要考虑，int和double，以下方法，默认左边界为0，
     *      左右边界都没给，按默认边界处理【0，1）
     *      给出一个，即默认是右边界，默认左边界是0
     *      给出左右边界，取区间之间的数
     *
     *      还有两种特殊情况，
     *      大于左边界，右边界无穷，biggerThan(int/double border,int capacity)
     *      小于右边界，左边界无穷，smallerThan(int/double border,int capacity)
     *
     *      检查左右大小是否符合，
     * 获取10以下的随机数/随机整数
     * 获取0-10之间的随机数/随机整数
     * 获取数组
     * 获取10以下的随机数/随机整数 数组
     * 获取0-10之间的随机数/随机整数 数组
     *
     * 当获取数组长度够大时才会使用Hash Set
     */
    public static void main(String[] args) {

        //test1();
        //
        //test2();

        JarTest jt = new JarTest();
        //try {
        //    test3();
        //} catch (Exception exception) {
        //    exception.printStackTrace();
        //}

    }

    private static void test3() throws Exception {
        System.out.println("3333");
        double[] dl1 = RandomTest.randoms(10);
        double[] dl2 = RandomTest.randoms(20.0,0);
        double[] dl3 = RandomTest.randoms(10.0,20.0,20);

        int[] i1 = RandomTest.smallerThan(10,20);
        int[] i4 = RandomTest.biggerThan(10,2);
        int[] i2 = RandomTest.randomInts(20,10);
        int[] i3 = RandomTest.randomInts(-100,20,2000);

        //outputd(RandomTest.smallerThan(0.0,1000000));
        //outputd(dl2);
        //outputd(dl3);
        outputi(i1);
        outputi(i4);
        //outputi(i2);
        //outputi(i3);

        //System.out.println(0-Double.MAX_VALUE);
    }

    static void outputd(double[] ds){
        for (double d:ds) {
            System.out.println(d);
        }
    }

    static void outputi(int[] is){
        for (int i:is) {
            System.out.println(i);
        }
    }
    private static void test2() {

        System.out.println("test2");
        System.out.println(Random.origin());
        System.out.println(Random.random(10));
        System.out.println(Random.randomInt(10));
    }

    private static void test1() {

        double d = 10;
        float f = 10.0f;
        for (int i = 0; i < 10; i++) {
            //System.out.println(RandomTest.origin());
            //System.out.println(Random.origin());
            System.out.println(RandomTest.random(-10.00, 20));
        }
        double d1 = RandomTest.random(10);
        System.out.println("d1:" + d1);


        int i1 = RandomTest.randomInt(10);
        System.out.println("i1:" + i1);

        int i2 = RandomTest.randomInt(-1, 10);
        System.out.println("i2:" + i2);
    }
}
