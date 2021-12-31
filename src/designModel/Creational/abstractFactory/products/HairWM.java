package designModel.Creational.abstractFactory.products;

/**
 * @author masuo
 * @data 2021/9/6 10:14
 * @Description Washing machine
 */

public class HairWM extends WM{
    @Override
    void wash() {
        System.out.println("This is a Hair wash machine");
    }
}
