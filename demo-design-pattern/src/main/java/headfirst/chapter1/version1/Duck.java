package headfirst.chapter1.version1;

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

    //
    public abstract void display();
}
