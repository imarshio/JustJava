package JavaBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author masuo
 * @create 2021/7/12 22:53
 * @Description 探究一下Object
 */

public class SeeObject {

    public static void main(String[] args) {

        test1();

        hashcodeTest();

        toStringTest();

        cloneTest();

        skinCloneTest();

        BoneCloneTest();

        CloneConstructorTest();

        iteratorTest();


    }

    private static void iteratorTest() {
        System.out.println("iterator test");
        List<String> ls = new ArrayList<>();
        ls.add("1230");
        ls.add("1230");
        ls.add("1230");
        ls.add("1230");
        Iterator<String> it = ls.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }
    }

    private static void CloneConstructorTest() {

        System.out.println("CloneConstructor");
        class CloneConstructor {
            private String s;

            // 构造函数
            public CloneConstructor() {
                s = "";
            }

            /**
             * 克隆构造方法，原理与构造方法类似，不同点在于需要获取原始对象数据，并将拷贝对象的数据赋值给拷贝对象
             */
            public CloneConstructor(CloneConstructor original) {
                // 代替clone方法，
                // 浅拷贝实现
                this.s = original.s;
                // 深拷贝实现
                this.s = new String(original.s);
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }

        }

        CloneConstructor cc1 = new CloneConstructor();
        cc1.setS("ms");
        CloneConstructor cc2 = new CloneConstructor(cc1);
        System.out.println(cc2.getS());
        System.out.println(cc1 == cc2);
        // true
        System.out.println(cc1.getS() == cc2.getS());
    }

    private static void BoneCloneTest() {

        System.out.println("深拷贝");

        class BoneClone implements Cloneable {

            private String s;

            @Override
            protected BoneClone clone() throws CloneNotSupportedException {
                // 深拷贝需要new操作，来新建对象
                BoneClone clone = (BoneClone) super.clone();
                // 新建一个对象表示引用不同的对象
                clone.s = new String(s);
                return clone;
            }

            // 构造类
            public BoneClone() {
                s = "s";
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }
        }

        BoneClone bc1 = new BoneClone();
        bc1.setS("555");
        BoneClone bc2 = null;
        try {
            bc2 = bc1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assert bc2 != null;
        System.out.println(bc2.getS() == bc1.getS());// 555
    }

    private static void skinCloneTest() {
        System.out.println("浅拷贝");
        class SkinClone implements Cloneable {

            private int i;// default 0

            // 首先，声明clone方法
            @Override
            protected SkinClone clone() throws CloneNotSupportedException {
                return (SkinClone) super.clone();
            }

            // 构造方法
            public SkinClone() {
                i = 10;
            }

            public int getI() {
                return i;
            }

            public void setI(int i) {
                this.i = i;
            }

        }


        SkinClone sct1 = new SkinClone();
        sct1.setI(50);
        try {
            SkinClone sct2 = sct1.clone();
            System.out.println(sct2.getI() == sct1.getI());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void cloneTest() {
        class CloneTest1 implements Cloneable {
            @Override
            protected Object clone() throws CloneNotSupportedException {
                return (CloneTest1) super.clone();
            }
        }

        class CloneTest2 {
            // 无clone方法
        }

        CloneTest1 ct1 = new CloneTest1();
        CloneTest2 ct2 = new CloneTest2();

        try {
            ct1.clone();// java.lang.CloneNotSupportedException: JavaBase.SeeObject$1CloneTest1
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // ct2.clone();//'clone()' has protected access in 'java.lang.Object'

        // CloneTest2.clone();
    }

    /**
     * toString
     */
    private static void toStringTest() {

        ToString ts = new ToString();
        System.out.println(ts.toString());// JavaBase.ToString@1b6d3586

        String s = "123";
        s.toString();

        Integer i = 1;
        i.toString();
    }

    /**
     * hashcode
     */
    private static void hashcodeTest() {
        // 等价性
        System.out.println("hashcode");
        String s1 = "12333999";
        String s2 = "123";
        System.out.println(s1.equals(s2));// true
        System.out.println(s1.hashCode());// 48690
        System.out.println(s2.hashCode());// 48690


        char[] val = {'1', '2', '3', '4', '5', '6', '7', '9', '9', '9', '9'};
        int h = 0;
        // int最大值是2的31次方-1，这里int类型是32位，一位符号位标识正负，初始化为0，表示正数，
        // 当数值大于2的31次方-1时会产生进位操作，导致数值变负数，
        // 2的31次方-1 = 2147483647
        for (char c : val) {
            System.out.println(Integer.valueOf(c));
            h = 31 * h + c;
            System.out.println(h);
        }

    }


    private static void test1() {

        String s1 = "123";
        String s2 = "123";

        // getClass()
        System.out.println(s1.getClass());

        // equals()
        System.out.println(s1.equals(s2));// true

    }
}

class ToString {

}