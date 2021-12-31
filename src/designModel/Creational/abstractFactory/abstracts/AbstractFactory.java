package designModel.Creational.abstractFactory.abstracts;

import designModel.Creational.abstractFactory.products.TV;
import designModel.Creational.abstractFactory.products.WM;

/**
 * @author masuo
 * @data 2021/9/6 10:06
 * @Description 抽象工厂
 */

public abstract class AbstractFactory {

    public abstract TV makeTV();
    public abstract WM makeWM();//Washing machine
}
