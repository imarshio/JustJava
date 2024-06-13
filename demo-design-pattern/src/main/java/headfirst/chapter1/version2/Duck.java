package chapter1.version2;

/**
 * @author masuo
 * @data 27/1/2022 上午8:49
 * @Description 实现模拟鸭子 - version1
 */

public abstract class Duck {
    public void quack() {
        // 鸭子呱呱叫
        System.out.println("duck qua qua qua...");
    }

    public void swim() {
        // 鸭子游泳
        System.out.println("duck swimming...");
    }

    public void fly() {
        // 鸭子游泳
        System.out.println("duck flying...");
    }

    //
    public abstract void display();
}
