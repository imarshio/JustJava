package designModel.creation.singleton;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author masuo
 * @date: 2021/09/01/ 22:36
 * @description
 */
public class SignleTonTest {


    //几乎所有的类的私有变量都可以被反射所获取
    public static void main(String args[]) throws Exception {



        SignleTonTest stt = new SignleTonTest();
        stt.regect();
        stt.defence();
        stt.enumTest();

    }

    @Test
    public void enumTest() {

        try {

            //这样获取的是正常的单例类
            EnumSignton enumSignton1 = EnumSignton.INSTANCE;
            EnumSignton enumSignton2 = EnumSignton.INSTANCE;
            System.out.println(enumSignton1==enumSignton2);//true
            //证明这个单例类是可行的


            //当我们试图使用反射来创建该单例类的另外一个子类时，由于它的.class文件告诉我们他是无参构造函数，所以我们传入一个null
            //private EnumSignton() {}
            //
            //Constructor<EnumSignton> instance = EnumSignton.class.getDeclaredConstructor(null);
            //将他的私有变量设置为可达对象，即可以获取她的私有对象
            //instance.setAccessible(true);
            //声明新的对象
            //EnumSignton enumSignton1 = instance.newInstance();
            //会告诉我们没有这个方法。

            //说明他不是无参构造，那我们如何找到/获取正确的构造函数呢？

            //通过使用jad反编译class文件，可以查看他真实的源码
            //可以得知他的构造器时双参构造器，非别为String和int

            //那么我们使用双参构造器进行构造

            Constructor<EnumSignton> instance = EnumSignton.class.getDeclaredConstructor(String.class,int.class);
            instance.setAccessible(true);

            EnumSignton enumSignton3 = instance.newInstance();
            //在此处报错提示：Cannot reflectively create enum objects，不能通过反射创建枚举对象
            //说明枚举单例模式确实不能被破坏
            System.out.println(enumSignton2==enumSignton3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void defence() throws Exception{
        LazySingleTonDefence instance = LazySingleTonDefence.getInstance();

        Constructor<LazySingleTonDefence> declaredConstructor = LazySingleTonDefence.class.getDeclaredConstructor(null);

        declaredConstructor.setAccessible(true);

        LazySingleTonDefence instance2 = declaredConstructor.newInstance();
        //反射可以破坏单例模式
        System.out.println(instance);
        System.out.println(instance2);
    }

    public void regect() throws Exception {
        LazySingleTonWithSynchronizeAndVolatile instance = LazySingleTonWithSynchronizeAndVolatile.getInstance();

        Constructor<LazySingleTonWithSynchronizeAndVolatile> declaredConstructor = LazySingleTonWithSynchronizeAndVolatile.class.getDeclaredConstructor(null);

        declaredConstructor.setAccessible(true);

        LazySingleTonWithSynchronizeAndVolatile instance2 = declaredConstructor.newInstance();
        //反射可以破坏单例模式
        System.out.println(instance);
        System.out.println(instance2);
    }
}
