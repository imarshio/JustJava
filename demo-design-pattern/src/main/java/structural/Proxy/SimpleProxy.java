package Proxy;

import org.junit.Test;

/**
 * @author masuo
 * @data 2021/10/12 17:17
 * @Description 代理模式---也叫中介模式，最典型的现实的例子就是婚介所、房产中介。
 * 在代码实现时，代理模式就是完全按照中介的作用实现的。
 */

public class SimpleProxy {

    // 代理模式的优点：
    // 1、代理模式在客户端与目标对象之间起到了一个中介作用和保护目标对象的作用
    // 2、代理对象可以扩展目标对象的功能
    // 3、代理模式将客户端与目标对象分离，在一定程度上降低了系统的耦合度，增加了程序的可扩展性

    // 代理模式的缺点：
    // 1、代理模式会造成系统中类的增加，增加系统负担
    // 2、由于存在中间代理，会造成访问时需要跳转多次，降低请求处理速度
    // 3、增加系统复杂度

    // 解决方法：使用动态代理，案例：Spring的核心IOC和AOP都用到了动态代理

    // 主要角色：
    // 1、抽象类
    // 2、具体类
    // 3、代理类

    public static void main(String[] args) {
        SimpleProxy sp = new SimpleProxy();
        sp.test();
    }

    @Test
    public void test() {
        HouseProxy hp = new HouseProxy();
        // 中介来代替实际购买
        hp.buy();
    }
}

// 抽象类
interface Buy {
    void buy();
}

// 实体类
class Buyer implements Buy {

    @Override
    public void buy() {
        System.out.println("买 house 。");
    }
}

class HouseProxy implements Buy {

    private Buyer buyer;

    @Override
    public void buy() {
        if (buyer == null) {
            buyer = new Buyer();
        }
        // 预处理
        preOP();
        // 实际操作
        buyer.buy();
        // 实际操作结束之后的处理
        afterOP();
    }

    private void afterOP() {
        System.out.println("实际操作结束之后的处理");
    }

    private void preOP() {
        System.out.println("预处理");
    }
}

