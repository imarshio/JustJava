package JavaCore.MyJVM.MyStack;

/**
 * @author: masuo
 * @data: 2021/7/29 16:19
 * @Description: 设置虚拟机栈的大小
 * 在Edit Configuration--》VM Operation--》-Xss256k,数字自己设置
 * Xss:虚拟机栈的内存大小，JVM调优之一
 */

public class VMStackCapacity {
    public static int i = 1;
    public static void main(String[] args) {
        System.out.println(i);//设置之前：11412,设置之后：2454
        i++;
        //main(args);//会造成内存溢出
    }
}
