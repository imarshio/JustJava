package designModel.Creational.abstractFactory.abstracts;

import designModel.Creational.abstractFactory.products.GeliTV;
import designModel.Creational.abstractFactory.products.GeliWM;
import designModel.Creational.abstractFactory.products.TV;
import designModel.Creational.abstractFactory.products.WM;

/**
 * @author masuo
 * @data 2021/9/6 10:23
 * @Description
 */

public class GeliFactory extends AbstractFactory{
    @Override
    public TV makeTV() {
        return new GeliTV();
    }

    @Override
    public WM makeWM() {
        return new GeliWM();
    }
}
