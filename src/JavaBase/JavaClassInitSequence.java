package JavaBase;

/**
 * @author masuo
 * @create 2021/7/11 22:34
 * @Description java类的初始化循序
 */
public class JavaClassInitSequence {


    /** 输出顺序为
     *  1.父类静态（变量或者代码块）显式赋值，
     *  2.子类静态（变量或者代码块）显式赋值，
     *  3.父类非静态显式赋值（变量或代码块）
     *  4.父类构造函数
     *  5.子类非静态显式赋值（变量或代码块）
     *  6.子类构造函数
     *
     *  ***注意这里是显式赋值，首先必须是赋值语句，如果没有赋值语句，只是单纯的声明变量/或初始化变量，则不会输出，看注释1
     */
    public static void main(String[] args) {
        Cat d = new Cat();
        //①父类静态fm,fa.②子类静态ss,sm.③父类非静态ft,fn,a.④子类非静态sa,st,sd
        System.out.println("000");
        Cat o = new Cat();
        //
    }


}
class Animal{
    private int i = test();
    private static int j = method();
    //注释1：下面1行代码不会输出，因为他们不是显式赋值语句,隐式赋值即implicit(x)
    private static int x;

    static {
        System.out.println("fa");
    }
    Animal(){
        System.out.println("a");
    }
    {
        System.out.println("fn");
    }

    private int test() {
        System.out.println("ft");
        return 1;
    }

    private static int method() {
        System.out.println("fm");
        return 1;
    }

    private int implicit(int i) {
        System.out.println("ft");
        return i;
    }
}

class Cat extends Animal{
    {
        System.out.println("sa");
    }
    private int i = test();
    static {
        System.out.println("ss");
    }
    private static int j = method();

    Cat(){
        System.out.println("sd");
    }
    public int test(){
        System.out.println("st");
        return 1;
    }
    public static int method(){
        System.out.println("sm");
        return 1;
    }
}
