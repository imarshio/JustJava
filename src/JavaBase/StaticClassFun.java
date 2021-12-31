package JavaBase;

/**
 * @author masuo
 * @create 2021/7/10 16:09
 * @Description 静态方法测试
 */
public abstract class StaticClassFun {
    static {
        System.out.println("这是静态语句块，你只会看到我出现一次哦！");
    }
    private static int x;//静态变量
    private int y;//实例变量
    //    public abstract static void get();
//    Illegal combination of modifiers: 'abstract' and 'static',不合法的修饰符组合
    public abstract void getNO();

    public static void getX() {
        //静态类
    }

    public void getAll(){
        //非静态类
    }

    public static void get(){

//        System.out.println(y);//Non-static field 'y' cannot be referenced from a static context,非静态变量不可被静态方法引用
        System.out.println(x);

        get();
        getX();
//        getAll();//Non-static method 'getAll()' cannot be referenced from a static context
    }

    public static void main(String[] args) {
        //在这里我们声明两个StaticClassOne类的实例对象，
        StaticClassOne sco1 = new StaticClassOne();
        StaticClassOne sco2 = new StaticClassOne();
    }
}
