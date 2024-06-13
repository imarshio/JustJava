package chapter1.version2;

/**
 * @author masuo
 * @data 27/1/2022 上午8:55
 * @Description 红头鸭
 */

public class RedheadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("I am a Redhead Duck..");
        swim();
        fly();
        quack();
    }
}
