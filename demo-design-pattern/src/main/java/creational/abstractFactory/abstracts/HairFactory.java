package creational.abstractFactory.abstracts;

import creational.abstractFactory.products.HairTV;
import creational.abstractFactory.products.HairWM;
import creational.abstractFactory.products.TV;
import creational.abstractFactory.products.WM;

/**
 * @author masuo
 * @data 2021/9/6 10:08
 * @Description 抽象实体
 */

public class HairFactory extends AbstractFactory {


    @Override
    public TV makeTV() {
        return new HairTV();
    }

    @Override
    public WM makeWM() {
        return new HairWM();
    }
}
