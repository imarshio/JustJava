package JavaBase;

/**
 * @author masuo
 * @create 2021/7/10 9:36
 * @Description static 的使用
 */
public class StaticType {

    private static int s;

    public static void main(String[] args) {
        staticOnData();
    }

    /**
     * static对数据的使用
     * 1、静态变量
     * 2、实例变量
     */
    private static void staticOnData() {
        //在声明的地方打个断点，在没有执行这一步之前，就已经存在static变量了，
        // 即在调用函数之前static变量就已经存在了，说明静态变量在类加载的时候就存在了，
        StaticClassOne sco1 = new StaticClassOne();
        StaticClassOne sco2 = new StaticClassOne();

        //在执行完声明之后，实例变量x被初始化，
        // 在这里，0是系统默认赋值的，
        int x = sco1.x;
        int y = StaticClassOne.y;
        System.out.println("没有改变之前的StaticClassOne.y的值为："+y);
        //改变静态变量
        for (int i = 0; i < 10; i++) {
            StaticClassOne.y = StaticClassOne.y+i;
            System.out.println("改变之后的StaticClassOne.y值为："+StaticClassOne.y);
        }

        //改变之后
        System.out.println("改变之后的StaticClassOne.y的值为："+StaticClassOne.y);
    }

}

class StaticClassOne{

    public int x; //实例变量
    public static int y ; //静态变量

    public static void getY(){}
}


abstract class StaticClassTwo{

    public static void get(){}
}