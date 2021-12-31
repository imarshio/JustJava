package CodeTest;

/**
 * @author: masuo
 * @data: 2021/7/29 14:50
 * @Description: 设置栈得大小：configuration:vm options:-Xss256k
 */


public class Test2 {
    public static int i = 1;
    public static void main(String[] args) {
        System.out.println(i);//设置之前：11412,设置之后：2454
        i++;
        main(args);
    }
}
