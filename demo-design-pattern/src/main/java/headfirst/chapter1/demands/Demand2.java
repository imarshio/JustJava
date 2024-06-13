package headfirst.chapter1.demands;

import headfirst.chapter1.version2.MallardDuck;
import headfirst.chapter1.version2.RedheadDuck;
import headfirst.chapter1.version2.RubberDuck;
import org.junit.Test;

/**
 * @author masuo
 * @data 27/1/2022 上午9:01
 * @Description 需求版本2：由于市场竞争压力大，需要实现鸭子飞的动作，所以自然的我们想到去超类种添加fly的实现方法
 */

public class Demand2 {
    @Test
    public void testV2_1() {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.display();

        RedheadDuck redheadDuck = new RedheadDuck();
        redheadDuck.display();
    }

    // 经过测试，鸭子们都成功飞上了天。
    // 项目成功上线，但是顾客们看到一个橡皮鸭在天上呱呱乱叫，把顾客们吓一跳。
    @Test
    public void testV2_2() {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.display();

        RedheadDuck redheadDuck = new RedheadDuck();
        redheadDuck.display();

        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.display();
    }
    // 于是，OO程序员marshio被一顿训斥，同时劝他准备好简历。

    // 继承

    // OO程序员marshio想到了继承，利用继承的覆盖，只要在子类中将父类的fly方法覆盖掉就可以了，说做就做。

    @Test
    public void testV2_3() {
        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.display();
    }

    // 被覆盖后，橡皮鸭的问题终于解决了

}
