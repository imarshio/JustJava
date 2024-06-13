package creation.singleton;

/**
 * @author masuo
 * @data 2021/9/1 13:01
 * @Description 单例模式
 */

public class SingletonPattern implements Cloneable {

    /**
     * 单例模式，单例模式的类只能创建一个子类，且只能由该类创建
     * 对外提供一个访问该单例类的全局访问点:getInstance(),也可以是其他的方法，能够访问到即可
     */

    private static SingletonPattern sp;

    private SingletonPattern() {
        // 私有化构造类，保证不被外界创建
        System.out.println("被创建啦。");
    }

    public static SingletonPattern getInstance() {
        if (sp == null) {
            sp = new SingletonPattern();
        }
        return sp;
    }

    @Override
    public SingletonPattern clone() throws CloneNotSupportedException {
        return (SingletonPattern) super.clone();
    }
}


// 饿汉式单例模式，
class HungerSingleTon {

    // 同样的为了避免被多次创建，私有化构造器
    private HungerSingleTon() {

    }

    // 因为是饿汉式，所以一上来我们就需要创建对象，
    // 当然，这样也会造成内存空间的浪费，因为如果你用不到的化，就不需要在堆空间声明
    private final static HungerSingleTon hst = new HungerSingleTon();

    // 对外提供统一访问入口
    public static HungerSingleTon getInstance() {
        return hst;
    }


}


/*会造成内存空间的浪费，因为如果你用不到的化，就不需要在堆空间声明*/

// 懒汉式单例模式
class LazySignleTon {

    // 同样私有化构造器
    private LazySignleTon() {

    }

    // 与饿汉式单例模式不同点在于，不会一开始就加载类的实例对象
    // 我们只有在需要的时候才会加载该类的唯一对象，这样就避免了空间的浪费
    private static LazySignleTon lst;

    public static LazySignleTon getInstance() {
        // 在这里我们需要判断该类对象是否被加载
        if (lst == null) {
            // 如果没有被加载，我们就需要创建一下该类
            lst = new LazySignleTon();
        }
        // 如果被加载过了，直接返回该对象即可
        return lst;
    }

}

// 但是这样会产生一个新的问题，因为我们的环境不止单线程，我们经常是在多线程的环境下使用单例模式，
// 如：文件资源管理器
//  为了解决多线程问题，我们需要对单例类进行加锁
// 枷锁可以直接加在类上，也可以直接加在方法上

// 多线程安全懒汉式单例模式
class LazySingleTonWithSynchronized {

    // 私有化构造器
    private LazySingleTonWithSynchronized() {
    }

    private static LazySingleTonWithSynchronized lstws;

    // 双重检测锁模式，DCL
    public static LazySingleTonWithSynchronized getInstance() {
        if (lstws == null) {
            // 对其加锁
            synchronized (LazySingleTonWithSynchronized.class) {
                if (lstws == null) {
                    lstws = new LazySingleTonWithSynchronized();
                    // 由于当前操作不是原子性操作，需依次执行以下步骤
                    // 1.JVM分配内存空间
                    // 2.执行构造函数，初始化对象
                    // 3.将当前对象指向JVM分配的内存空间

                    // 但是由于CPU的特性，可以进行指令重排，将执行顺序打乱
                    // 此时，在执行时可能发生132的顺序，
                    // 在多线程环境下当执行到第二布，该线程分配的时间片结束，执行下一线程，此时lstws还没完成构造，还是null
                    // 此时，在进入程序，由于JVM分配的内存空间已被占用，所以第一个判断条件不成立，直接返回lstws
                    // 就会出现空指针的错误。

                    // 为了避免指令重排，我们需要加上volatile
                }
            }
        }
        return lstws;
    }
}

// 多线程安全且无指令重排懒汉式单例模式
// 为了避免指令重排，我们需要加上volatile
class LazySingleTonWithSynchronizeAndVolatile {

    // 私有化构造器
    private LazySingleTonWithSynchronizeAndVolatile() {
    }

    // 为了避免指令重排，我们需要加上volatile
    private static volatile LazySingleTonWithSynchronizeAndVolatile lstws;

    // 双重检测锁模式，DCL
    public static LazySingleTonWithSynchronizeAndVolatile getInstance() {
        if (lstws == null) {
            // 对其加锁
            synchronized (LazySingleTonWithSynchronizeAndVolatile.class) {
                if (lstws == null) {
                    lstws = new LazySingleTonWithSynchronizeAndVolatile();
                }
            }
        }
        return lstws;
    }
}

// 静态内部类实现单例模式
class OuterClass {

    private OuterClass() {

    }

    // 静态内部类
    public static class InnerClass {

        private static final OuterClass outer = new OuterClass();
    }

    public static OuterClass getInstance() {

        return InnerClass.outer;
    }
}


// 为了避免被反射多次加载该类
class LazySingleTonDefence {

    // 私有化构造器
    private LazySingleTonDefence() {

        // 为了避免被反射多次调用构造器

        // 需要在构造器里面对其进行判断
        synchronized (LazySingleTonDefence.class) {
            if (lstd != null) {
                throw new RuntimeException("nono");
            }
        }
    }

    // 为了避免指令重排，我们需要加上volatile
    private static volatile LazySingleTonDefence lstd;

    // 双重检测锁模式，DCL
    public static LazySingleTonDefence getInstance() {
        if (lstd == null) {
            // 对其加锁
            synchronized (LazySingleTonDefence.class) {
                if (lstd == null) {
                    lstd = new LazySingleTonDefence();

                }

            }
        }
        return lstd;
    }
}

// 枚举--Enum
enum EnumSignton implements Cloneable {

    // 枚举是什么？
    // 其本身也是一个class类

    INSTANCE;

    public static EnumSignton getInstance() {
        return INSTANCE;
    }

}

