package creational.abstractFactory.abstracts;

import creational.abstractFactory.products.GeliTV;
import creational.abstractFactory.products.GeliWM;
import creational.abstractFactory.products.TV;
import creational.abstractFactory.products.WM;

/**
 * @author masuo
 * @data 2021/9/6 10:23
 * @Description
 */

public class GeliFactory extends AbstractFactory {
    @Override
    public TV makeTV() {
        return new GeliTV();
    }

    @Override
    public WM makeWM() {
        return new GeliWM();
    }
}
