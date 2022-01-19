package designModel.creation.simpleFactory;

/**
 * @author masuo
 * @data 2021/8/26 16:40
 * @Description 榨汁机工厂--设计模式的重点所在
 */

public class JuiceFactory {

    public static Juicer createJuicer(String name){
        Juicer juicer = null;

        if("apple".equals(name)){
            juicer = new AppleJuice();
        }else if("orange".equals(name)){
            juicer = new OrangeJuice();
        }
        return juicer;
    }
}
