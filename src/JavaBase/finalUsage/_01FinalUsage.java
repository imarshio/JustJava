package JavaBase.finalUsage;

import org.junit.Test;

/**
 * @author masuo
 * @data 6/5/2022 下午4:05
 * @Description final用法
 * -- final可以用来修饰
 * ---- 变量
 * ---- 方法
 * ---- 类
 * ---- 参数
 */

public class _01FinalUsage {

    private final int x = 0;

    // final 修饰变量
    @Test
    public void finalVar() {
        // 该变量是在编译期就被赋初值的，且不可更改，如果变量是对象，那么指向对象的引用不可更改，但是对象的值可以更改
        System.out.println(x);

        
        System.exit(0);

        System.out.println("这里就执行不到了哦");
    }

    public final void aMethod() {
        System.out.println("final 修饰方法");
    }

    public final void aMethod(String s) {
        System.out.println("final 修饰方法被重载");
    }

    // final 修饰方法
    @Test
    public void finalMethod() {
        // 该方法不可被继承，但是可以被重载
        aMethod();
    }

    // final 修饰类
    @Test
    public void finalClass() {
    }

    // final 修饰参数
    @Test
    public void finalParameter() {
    }


}


