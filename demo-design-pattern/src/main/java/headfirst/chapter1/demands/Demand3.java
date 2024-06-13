package headfirst.chapter1.demands;

import headfirst.chapter1.version2.MallardDuck;
import headfirst.chapter1.version2.RedheadDuck;
import headfirst.chapter1.version2.RubberDuck;
import org.junit.Test;

/**
 * @author masuo
 * @data 27/1/2022 上午9:01
 * @Description 需求版本3：由于市场竞争压力大，需要每隔一个月更新一次产品
 */

// OO程序员想到以后每增加一款鸭子，都要覆盖所有的代码，就觉得很是苦恼

// 接口

// OO程序员又想到了接口，将fly和quack变成一个接口，让新的鸭子实现这些接口。

// 嗯，这个貌似还不错，但是如果以后还有新的动作要增加，那么就需要给每个鸭子逐一添加新动作。想想就头大

// 所以接口也不是问题的解决办法

// 此时，貌似遇到了系统的瓶颈，我们就需要停下来思考一下，
// 使用继承会导致所有的类都改变
// 使用接口无法实现代码的复用
// 此时出现了救世主---设计原则：找出应用中可能需要变化的地方，独立出来，不要和那些不需要变化的代码混在一起，即隔离

public class Demand3 {
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

    // 但是过了两天，问题又来了，老板们想要一款诱饵鸭（啥都不会干）、烤鸭（啥都不会干）、

    // OO程序员想到以后每增加一款鸭子，都要覆盖所有的代码，就觉得很是苦恼

    // 接口

    // OO程序员又想到了接口，将fly和quack变成一个接口，让新的鸭子实现这些接口。

    // 嗯，这个貌似还不错，但是如果以后还有新的动作要增加，那么就需要给每个鸭子逐一添加新动作。想想就头大

    // 所以接口也不是问题的解决办法

    // 此时，貌似遇到了系统的瓶颈，我们就需要停下来思考一下，
    // 使用继承会导致所有的类都改变
    // 使用接口无法实现代码的复用
    // 此时出现了救世主---设计原则：找出应用中可能需要变化的地方，独立出来，不要和那些不需要变化的代码混在一起，即隔离

}
