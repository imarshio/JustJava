package designModel.creation;


import designModel.creation.ProtoType.RelizeType;
import designModel.creation.abstractFactory.abstracts.AbstractFactory;
import designModel.creation.abstractFactory.abstracts.HairFactory;
import designModel.creation.abstractFactory.products.TV;
import designModel.creation.builder.Lenovo;
import designModel.creation.builder.abstracts.CPUS;
import designModel.creation.builder.abstracts.Disks;
import designModel.creation.builder.abstracts.MainBoards;
import designModel.creation.builder.products.AmdCPU;
import designModel.creation.builder.products.GigabateMainBoard;
import designModel.creation.builder.products.SamsungDisk;
import designModel.creation.factoryMethod.WoodenDoor;
import designModel.creation.factoryMethod.WoodenDoorFactory;
import designModel.creation.singleton.SignletonPattern;
import designModel.creation.simpleFactory.JuiceFactory;
import designModel.creation.simpleFactory.Juicer;
import org.junit.Test;



/**
 * @author masuo
 * @data 2021/8/25 11:39
 * @Description 工厂模式
 * 工厂模式又分
 *      简单工厂模式，不在23种设计模式之中
 *      工厂方法模式
 *      抽象工厂模式
 */

public class Pattern_Factory {



    public static void main(String[] args) {

        Pattern_Factory pf = new Pattern_Factory();
        pf.simpleFactory();
        pf.factoryMethod();
        pf.abstractFactory();
        pf.signleTon();
        pf.builder();
        pf.protoType();
        pf.cloneVSSingleton();

    }

    @Test
    public void cloneVSSingleton() {
        //clone会破坏单例模式嘛
        SignletonPattern sp = SignletonPattern.getInstance();
        try {
            SignletonPattern clone = sp.clone();
            System.out.println(sp.toString());
            System.out.println(clone.toString());
            //designModel.Creational.signleton.SignletonPattern@506c589e
            //designModel.Creational.signleton.SignletonPattern@69d0a921

            //可以看到，单例模式已经被破坏了
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    //原型模式

    /**
     * 原型模式优点：
     *      比直接new一个对象性能更优
     *      使用深克隆方式保存对象以及其状态，简化了创建对象的过程，更可以用在还原对象在历史上的某一时刻
     *      缺点：
     *      每个类都需要有一个clone方法，
     *      clone方法位于方法内部，对象修改时需要修改clone方法，违背了开闭原则
     *      使用深克隆时，需要实现较为复杂的代码，并需要修改大量代码
     */
    @Test
    public void protoType() {

        RelizeType rt1 = new RelizeType("D");
        RelizeType rt2 =  new RelizeType("S");

        //浅克隆
        rt1.getType();
        //深克隆:要对所以非基本数据类型的属性实现克隆
        rt2.getType();
    }

    //建造者模式

    /**
     * 建造者模式主要是实现了复杂对象的简单组合完成（不一定分顺序），
     * 使得类的创建更加灵活，选择多种多样，就如捏脸，我们需要一个部位一个部位的去捏，选择自己喜欢的尺寸。
     */
    @Test
    public void builder() {
        //建造模型
        Lenovo lenovo = new Lenovo();

        //一步一步搭建，
        //首先，创建CPU，
        CPUS cpus = new AmdCPU();
        cpus.makeCPU();
        //装载CPU
        lenovo.loadCPU(cpus);
        //其次，创建Disk
        Disks disks = new SamsungDisk(100);
        disks.makeDisk();
        //装载disk
        lenovo.loadDisk(disks);
        //最后，创建主板
        MainBoards mbs = new GigabateMainBoard();
        mbs.makeMainBoard(cpus,disks);
        //装载主板
        lenovo.loadMainBoard(mbs);

        //以上就是建造者模式一步一步建造实体的，最典型的例子就是StringBuilder
        System.out.println(lenovo.toString());

        //StringBuilder,我们都知道，数组在JDK1.8中是数组，而数组都是需要初始化长度的，
        // 在JDK1.8中，StringBuilder的默认长度是16
        // 它最主要的功能就是动态的添加字符串,并且由于底层是数组，他的性能要比String优秀不少（在添加字符串方面，
        // 因为String单例的机制，他每次需要new一个String来替换之前的String）
        StringBuilder sb = new StringBuilder();

        sb.append("aaa");
        sb.append("bbb");
        sb.append("ccc");
        sb.append("111");
        System.out.println(sb);
        sb.append(222);
        sb.append(222);
        //可以添加所有的基础数据类型和一些其他数据类型

        //最后我们只需要将其转化为String类型即可
        String s = sb.toString();

        System.out.println(s);
    }

    //单例模式
    @Test
    public void signleTon() {
        //单线程环境下测试饿汉单例模式


        //单线程环境下测试懒汉单例模式
        SignletonPattern.getInstance();

        //多线程环境下测试饿汉单例模式
        for (int i = 0; i < 5; i++) {
            //lambda表达式
            //new Thread(()->{SignletonPattern.getInstance()}).start();
            new Thread(SignletonPattern::getInstance).start();
        }

        //多线程环境下测试懒汉单例模式


    }

    /**
     * 抽象工厂模式：扩大工厂的定义，上升至更大的一个层面，如门工厂--》家具工厂
     *      官方定义：是一种为访问类提供一个创建一组相关或相互依赖对象的接口，且访问类无须指定所要产品的具体类就能得到同族的不同等级的产品的模式结构
     *      抽象工厂是工厂方法的升级，工厂方法只能生产一个产品等级，如门，冰箱，灯等中的一种产品。
     *      抽象工厂可以生产多个产品等级，可以包括家具下的多种家具
     */
    @Test
    public void abstractFactory() {

        AbstractFactory hair = new HairFactory();
        TV hairTV = hair.makeTV();
        hairTV.show();
        hair.makeWM();

    }

    /**
     * 工厂方法模式：为了解决简单工厂产生的“if{}else{}”问题，
     *          工厂方法模式为每个对象子类（如水果下的苹果类）添加了相应的工厂子类（appleFactory）来解决问题，
     *          这些工厂子类实现同一个抽象工厂接口。这样当我们创建不同的水果类时，只需要调用不同的水果工厂，
     *          新增水果类时（banana），我们只需要实现响应的工厂子类（bananaFactory extends Juicer）即可。
     *
     *    实现与简单工厂的不同点在于，将工厂的具体实现放到了工厂子类中，有工厂子类代替实现，实现一对一服务，
     *    这样在我们添加新的产品时，只需要添加新的子工厂即可，实现了开闭原则。
     *
     *    缺点：每次新增子类都需要添加对应的工厂子类，这样大大增加了类的个数，增加系统负担。
     */
    @Test
    public void factoryMethod() {
        //造木门
        WoodenDoorFactory woodenDoorFactory = new WoodenDoorFactory();
        WoodenDoor woodenDoor = woodenDoorFactory.makeDoor();
        woodenDoor.setHeight(10);
        System.out.println(woodenDoor.getHeight());

    }


    /**
     * 简单工厂模式：
     *       专门创建一个类（工厂类）用于创建其他类的实例，
     *       可以根据创建方法的参数来返回不同类的实例，被创建的实例通常具有共同的父类
     *    *官方定义*：简单工厂模式又称为静态工厂模式，简单工厂模式的作用就是创建一个工厂类
     *    用来创建其它类的实例，至于类是怎么样创建的对用户来说是不可见的「屏蔽细节」
     * 例子：比如我有苹果、桔子、等水果，然后有一个榨汁机，我给一个苹果就给我榨出苹果汁，给桔子就出桔汁。
     * 优点：
     *      使用简单，只需传入对应的参数就可以得到想要的东西，充分利用了多态的机制
     *      解耦，创建对象由工厂完成，而不是调用者主动去创建对象，对内部实现细节进行屏蔽
     *      分工明确
     * 缺点：
     *      静态方法，无法继承，不可扩展
     *      代码维护不易，需要新的对象时，如新的水果葡萄，首先需要在基类（Juicer）中改动代码，
     *      其次需要修改工厂，添加“if{}else{}”分支，违反了开闭原则
     *
     *
     * 个人理解：一个拥有一类对象（如水果，动物等）的共同特征的工厂，根据传入的不同参数来制造不同的对象
     */
    @Test
    public void simpleFactory() {
        Juicer aplle = JuiceFactory.createJuicer("apple");
        aplle.getJuice();
        Juicer orange = JuiceFactory.createJuicer("orange");
        orange.getJuice();
    }

}
