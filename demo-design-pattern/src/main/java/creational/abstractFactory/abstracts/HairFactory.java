package creation.abstractFactory.abstracts;

import designModel.creation.abstractFactory.products.HairTV;
import designModel.creation.abstractFactory.products.HairWM;
import designModel.creation.abstractFactory.products.TV;
import designModel.creation.abstractFactory.products.WM;

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
