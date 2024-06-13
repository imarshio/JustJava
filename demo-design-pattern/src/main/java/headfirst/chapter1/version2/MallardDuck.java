package chapter1.version2;

/**
 * @author masuo
 * @data 27/1/2022 上午8:54
 * @Description 绿头鸭
 */

public class MallardDuck extends Duck {

    @Override
    public void display() {
        System.out.println("I am a Mallard Duck.");
        swim();
        quack();
        fly();
    }
}
