package chapter1.version2;

/**
 * @author masuo
 * @data 27/1/2022 上午9:14
 * @Description 橡皮鸭
 */

public class RubberDuck extends Duck {

    @Override
    public void fly() {
        // do nothing
    }

    @Override
    public void display() {
        System.out.println("I am a RubberDuck...");
        fly();
        quack();
        swim();
    }
}
