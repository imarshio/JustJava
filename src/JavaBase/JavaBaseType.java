package JavaBase;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class JavaBaseType {
    /**
     * this used for the base type of java
     */
    public static void main(String[] args) {
       baseSize();
       packType();
       bufferPool();
       stringpool();
    }

    private static void stringpool() {
        System.out.println("string pool");
        String s1 = new String("123");
        String s2 = new String("123");
        System.out.println(s1==s2);//false
        String s3 = s1.intern();
        String s4 = s2.intern();
        System.out.println(s3==s4);//true
        System.out.println(s1==s3);//false
        // 在这里，本人有个疑问，如果s3和s4是引用的s1或者s2中的一个，那么s3和s4会等于s1或者s2中的一个，但是事实上却不等于

        // 通过对String pool的再一次了解，发现这个字面量的真正意义，他存储的是字符的字面量，也就是说，
        // 两次new操作在String pool中只进行了一次存储，因为他们是字面量相等的两个不相等的字符串
        // 而s3和s4在引用时，引用的是String pool中的”123“，所以他们两个相等，
        // 使用new 新建字符串会新建两个字符串（前提是String pool中不存在与新建变量值相等的对象），所以在执行完new s1，new s2之后，会有三个对象生成，一个在String pool中，剩下两个在堆中
        // 所以这也解释了s1/s2与s3/s4地址不相等的原因
        String s5 = "555";
        String s6 = "555";
        System.out.println(s5==s6);
        System.out.println();
    }

    private static void bufferPool() { System.out.println("缓冲池");
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x==y);//false

        Integer i = Integer.valueOf(123);
        Integer j = Integer.valueOf(123);
        System.out.println(i==j);//true
        char min = '\u0000';
        System.out.println(Integer.valueOf(min));
        char c = '\u007F';
        System.out.println( Integer.valueOf(c));
        System.out.println( (char)(-128));
    }

    private static void packType() {
        System.out.println("Java 包装类型测试");
        Integer i = 2;
        //当int的值在缓存池中时，直接从缓存池中拿所需值，不在缓存池中时，新建一个Integer对象
        //新建对象直接赋值，
        /**
         *      IntegerCache.low=-127,IntegerCache.high=128
         *
         *      public static Integer valueOf(int i) {
         *         if (i >= IntegerCache.low && i <= IntegerCache.high)
         *             return IntegerCache.cache[i + (-IntegerCache.low)];
         *         return new Integer(i);
         *      }
         *
         *      public Integer(int value) {
         *         this.value = value;
         *      }
         */
        int j = i;

    }

    private static void baseSize() {
        System.out.println("Java 基础类型长度测试");
        System.out.println(Byte.SIZE);//8
        System.out.println(Short.SIZE);//16
        System.out.println(Integer.SIZE);//32
        System.out.println(Long.SIZE);//64
        System.out.println(Character.SIZE);//16
        System.out.println(Float.SIZE);//32
        System.out.println(Double.SIZE);//64

        System.out.println(Integer.MAX_VALUE);
    }
}
