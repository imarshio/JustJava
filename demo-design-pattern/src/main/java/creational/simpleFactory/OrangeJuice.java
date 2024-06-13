package creation.simpleFactory;

/**
 * @author masuo
 * @data 2021/8/26 13:18
 * @Description orange
 */

public class OrangeJuice implements Juicer {


    @Override
    public void getJuice() {
        System.out.println("orange juice");
    }
}
