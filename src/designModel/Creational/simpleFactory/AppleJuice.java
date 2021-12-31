package designModel.Creational.simpleFactory;

/**
 * @author masuo
 * @data 2021/8/26 13:17
 * @Description apple
 */

public class AppleJuice implements Juicer {

    @Override
    public void getJuice() {

        System.out.println("apple juice");
    }
}
