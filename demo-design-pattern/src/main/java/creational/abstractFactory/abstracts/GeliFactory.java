package creation.abstractFactory.abstracts;

import designModel.creation.abstractFactory.products.GeliTV;
import designModel.creation.abstractFactory.products.GeliWM;
import designModel.creation.abstractFactory.products.TV;
import designModel.creation.abstractFactory.products.WM;

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
