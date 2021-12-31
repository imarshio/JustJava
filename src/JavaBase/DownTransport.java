package JavaBase;

/**
 * @author masuo
 * @create 2021/7/8 23:25
 * @Description 隐式向下转型
 */
public class DownTransport {

    public static void main(String[] args) {
        floatAndDouble();
        shortAndInt();
        intAndLong();
    }

    private static void intAndLong() {
        long l = 1;
        int i = (int) l;//Incompatible types. Found: 'long', required: 'int'
    }

    private static void shortAndInt() {
        short s = 1;
        int i = s;
//        short s1 = i;//Incompatible types. Found: 'int', required: 'short'
    }

    private static void floatAndDouble() {
        float f = 1.1f;
        double d = 1.1;
    }
}
