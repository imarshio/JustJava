package designModel.Creational.abstractFactory.abstracts;

import designModel.Creational.abstractFactory.products.HairTV;
import designModel.Creational.abstractFactory.products.HairWM;
import designModel.Creational.abstractFactory.products.TV;
import designModel.Creational.abstractFactory.products.WM;

/**
 * @author masuo
 * @data 2021/9/6 10:08
 * @Description 抽象实体
 */

public class HairFactory extends AbstractFactory{


    @Override
    public TV makeTV() {
        return new HairTV();
    }

    @Override
    public WM makeWM() {
        return new HairWM();
    }
}
