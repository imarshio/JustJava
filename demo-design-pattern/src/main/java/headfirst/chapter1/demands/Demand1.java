package headfirst.chapter1.demands;

import headfirst.chapter1.version1.MallardDuck;
import headfirst.chapter1.version1.RedheadDuck;
import org.junit.Test;

/**
 * @author masuo
 * @data 27/1/2022 上午9:01
 * @Description 需求版本1：模拟不同的鸭子，需要能游泳和呱呱叫
 */

public class Demand1 {
    @Test
    public void testV1() {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.display();
        mallardDuck.quack();
        mallardDuck.swim();

        RedheadDuck redheadDuck = new RedheadDuck();
        redheadDuck.display();
        redheadDuck.quack();
        redheadDuck.swim();
    }
}
