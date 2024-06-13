package creation.singleton;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author masuo
 * @data 19/1/2022 上午9:15
 * @Description 单例与反射
 */

public class SingletonWithReflect {

    @Test
    public void hungerModel1() {
        // 饿汉式
        HungerModel1 hunger1 = HungerModel1.getInstance();
        HungerModel1 hunger2 = HungerModel1.getInstance();
        System.out.println(hunger1);
        System.out.println(hunger2);

        // 反射的几种方式
        // 1、 ClassName.class  ==>  Hunger.class
        Class<HungerModel1> aClass1 = HungerModel1.class;

        // 2、 Class.forName("类的全限定名")  ==> Class.forName("designModel.creation.singleton.Hunger")
        try {
            Class<?> aClass2 = Class.forName("designModel.creation.singleton.HungerModel1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 3、 对象.getClass();
        Class<? extends HungerModel1> aClass3 = hunger1.getClass();

        // 然后通过获取到的反射类来破坏单例模式
        try {
            // 首先获取该类的构造函数，
            /*
             * 参数可选，参数是构造函数的参数类型
             * 如果有无参构造函数时，可以不传参数类型
             * 如果没有无参构造函数，则需要提供参数类型
             * */
            Constructor<HungerModel1> declaredConstructor1 = aClass1.getDeclaredConstructor(int.class);
            Constructor<? extends HungerModel1> declaredConstructor2 = aClass3.getDeclaredConstructor();

            System.out.println(declaredConstructor1);
            System.out.println(declaredConstructor2);
            /*
             * 得到的是：
             * private designModel.creation.singleton.Hunger()
             * private designModel.creation.singleton.Hunger(int)
             * */

            // 设置构造器可达，针对私有构造器的
            declaredConstructor1.setAccessible(true);
            declaredConstructor2.setAccessible(true);

            // newInstance即调用构造器生成一个新的实例，如果获取到的构造器是有参构造器，就需要传入一个参数,无参构造则不需要参数
            HungerModel1 hunger3 = declaredConstructor1.newInstance(1);
            HungerModel1 hunger4 = declaredConstructor2.newInstance();

            System.out.println(hunger3);
            System.out.println(hunger4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hungerModel2() {
        // 饿汉式
        HungerModel2 hunger1 = HungerModel2.getInstance();
        HungerModel2 hunger2 = HungerModel2.getInstance();

        // 验证单例
        System.out.println(hunger1);
        System.out.println(hunger2);

        // 反射的几种方式
        Class<HungerModel2> aClass1 = HungerModel2.class;

        // 然后通过获取到的反射类来破坏单例模式
        try {
            // 首先获取该类的构造函数，
            Constructor<HungerModel2> declaredConstructor1 = aClass1.getDeclaredConstructor();

            System.out.println(declaredConstructor1);

            // 设置构造器可达，针对私有构造器的
            declaredConstructor1.setAccessible(true);

            // newInstance即调用new HungerModel2(),所以必定会生成一个新的对象，来破坏单例
            HungerModel2 hunger3 = declaredConstructor1.newInstance();

            System.out.println(hunger3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 懒汉模式，尝试破坏懒汉模式
     * 1.反射，成功
     * 2.并发，成功
     */
    @Test
    public void lazierModel1() {
        testBrokeLazierModel1();
        testBrokeLazierModel2();
    }

    /**
     * 测试破坏初始懒汉模式--反射
     */
    private void testBrokeLazierModel1() {
        LazierModel1 lazier1 = LazierModel1.getInstance();
        LazierModel1 lazier2 = LazierModel1.getInstance();

        // 验证唯一性
        System.out.println(lazier1);
        System.out.println(lazier2);

        // 尝试反射
        Class<LazierModel1> lazierClass = LazierModel1.class;
        try {
            Constructor<LazierModel1> constructor = lazierClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            // 这里，相当于我们直接调用了new LazierModel1(),所以，必定会生成一个新的对象，破坏单例。
            LazierModel1 lazier3 = constructor.newInstance();
            System.out.println(lazier3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试破坏初始懒汉模式--并发
     */
    private void testBrokeLazierModel2() {
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                try {
                    LazierModel1 lazier3 = LazierModel1.getInstance();
                    // 可以看到，在不加锁的情况下，单例模式还是被破坏了
                    System.out.println(lazier3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // 执行完毕，调用shutdown
        service.shutdown();

        try {
            boolean b = service.awaitTermination(10, TimeUnit.MINUTES);
            if (b) {
                System.out.println("成功！");
            } else {
                System.out.println("失败！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试给懒汉模式加锁
     */
    @Test
    public void lazierModel2() {

        testSyncLazierModel1();
        testSyncLazierModel2();
        testSyncLazierModel3();
        testSyncLazierModel4();

    }

    /**
     * 类锁 -- 不需要修改类
     */
    private void testSyncLazierModel1() {
        ExecutorService service = Executors.newFixedThreadPool(100);
        // 尝试并发,设置一个100个线程的线程池，在十分钟内循环执行
        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                try {
                    synchronized (LazierModel2_class.class) {
                        LazierModel2_class lazier3 = LazierModel2_class.getInstance();
                        // 可以看到，在加锁的情况下，单例模式没有被破坏
                        System.out.println(lazier3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // 执行完毕，调用shutdown
        service.shutdown();

        try {
            boolean b = service.awaitTermination(10, TimeUnit.MINUTES);
            if (b) {
                System.out.println("成功！");
            } else {
                System.out.println("失败！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 代码块锁 -- 需要修改代码，
     */
    @Test
    public void testSyncLazierModel2() {
        ExecutorService service = Executors.newFixedThreadPool(100);
        // 尝试并发,设置一个100个线程的线程池，在十分钟内循环执行
        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                try {
                    LazierModel2_codeBlock lazier3 = LazierModel2_codeBlock.getInstance();
                    // 可以看到，在加锁的情况下，单例模式没有被破坏
                    System.out.println(lazier3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // 执行完毕，调用shutdown
        service.shutdown();

        try {
            boolean b = service.awaitTermination(10, TimeUnit.MINUTES);
            if (b) {
                System.out.println("成功！");
            } else {
                System.out.println("失败！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法锁 -- 需要修改代码
     */
    @Test
    public void testSyncLazierModel3() {
        ExecutorService service = Executors.newFixedThreadPool(100);
        // 尝试并发,设置一个100个线程的线程池，在十分钟内循环执行
        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                try {
                    LazierModel2_method lazier3 = LazierModel2_method.getInstance();
                    // 可以看到，在加锁的情况下，单例模式没有被破坏
                    System.out.println(lazier3);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // 执行完毕，调用shutdown
        service.shutdown();

        try {
            boolean b = service.awaitTermination(10, TimeUnit.MINUTES);
            if (b) {
                System.out.println("成功！");
            } else {
                System.out.println("失败！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * DCL -- 双重检测锁，Double Check Lock
     */
    @Test
    public void testSyncLazierModel4() {
        ExecutorService service = Executors.newFixedThreadPool(100);
        // 尝试并发,设置一个100个线程的线程池，在十分钟内循环执行
        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                try {
                    LazierModel3_DCL lazier3 = LazierModel3_DCL.getInstance();
                    // 可以看到，在加锁的情况下，单例模式没有被破坏
                    System.out.println(lazier3);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // 执行完毕，调用shutdown
        service.shutdown();

        try {
            boolean b = service.awaitTermination(10, TimeUnit.MINUTES);
            if (b) {
                System.out.println("成功！");
            } else {
                System.out.println("失败！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 饿汉式 -- 静态变量
// 缺点：一加载类就生成对象，在一定程度上，造成了空间的浪费,且能被反射破坏
// 优点：线程安全，因为在加载类时就会创建对象。
class HungerModel1 {

    // 饿汉式就是一加载类就生成单例对象
    private static final HungerModel1 hunger = new HungerModel1();

    private HungerModel1(int i) {
        // 私有化构造器
    }

    private HungerModel1() {
        // 私有化构造器
    }

    // 提供外部访问接口
    public static HungerModel1 getInstance() {
        return hunger;
    }
}

// 饿汉式 -- 静态代码块
// 缺点：一加载类就生成对象，在一定程度上，造成了空间的浪费，与静态变量没啥区别，只是改变了初始化对象的位置
class HungerModel2 {

    // 饿汉式就是一加载类就生成单例对象
    private static HungerModel2 hunger;

    static {
        // 静态代码块只会被执行一次，
        hunger = new HungerModel2();
    }

    private HungerModel2() {
        // 私有化构造器
    }

    // 提供外部访问接口
    public static HungerModel2 getInstance() {
        return hunger;
    }
}

// 懒汉式 -- 需要时在new对象
// 优点：优化了饿汉式一加载类就生成对象的缺点，避免了空间的浪费
// 缺点：线程不安全，且能被反射破坏
class LazierModel1 {
    private static LazierModel1 lazier;

    private LazierModel1() {
        // 私有化构造器
    }

    // 提供外部访问接口
    public static LazierModel1 getInstance() {
        if (lazier == null) {
            // 为防止每次调用该接口都生成一个对象，需要判断该对象是否已经生成
            lazier = new LazierModel1();
        }
        return lazier;
    }
}

/*
 *  思考
 *  由于线程不安全，且易被反射机制破坏单例性，我们首先考虑线程的安全性问题，
 *  要保证线程的安全，就需要加锁，
 *  加锁可以分为
 *      1、给类加锁，给类加锁，不需要修改类
 *      2、给方法加锁，
 *      3、给代码块加锁，
 *      4、给对象加锁
 * */

// 懒汉式 -- 给类加锁
// 优点：实现线程安全
// 缺点：不太现实，这样会造成并发效率低
class LazierModel2_class {
    private static LazierModel2_class lazier;

    private LazierModel2_class() {
    }

    // 提供外部访问接口
    public static LazierModel2_class getInstance() {
        if (lazier == null) {
            try {
                // 模拟处理工作的过程，即有可能在此时该线程时间片运行结束，该运行其他时间片（线程）了，
                // 这就导致了，好多个线程都判断到了lazier为空，且都进来了，那么在等到他们再次执行时，
                // 他们会继续往下执行，即生成单例对象，这就不能保证单例特性了
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 为防止每次调用该接口都生成一个对象，需要判断该对象是否已经生成
            lazier = new LazierModel2_class();
        }
        return lazier;
    }
}

// 懒汉式 -- 给方法加锁
// 优点：保证了线程安全
// 缺点：每次获取该单例类的时候，都会尝试获取锁，即锁粒度太粗,如果业务场景对单例的请求频繁会造成线程阻塞
class LazierModel2_method {
    private static LazierModel2_method lazier;

    private LazierModel2_method() {
    }

    // 提供外部访问接口
    public static synchronized LazierModel2_method getInstance() {
        if (lazier == null) {
            try {
                // ext job here
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 为防止每次调用该接口都生成一个对象，需要判断该对象是否已经生成
            lazier = new LazierModel2_method();
        }
        return lazier;
    }
}

/*
 * 思考
 * 不应该每次加载都获取锁，因为不管单例对象是否已经生成，我们都会尝试获取锁，这样就浪费了实例变量的值
 * 那么我们应该尝试在获取锁之前进行判断，判断单例对象的值是否为 null，为 null 就尝试获取锁，即 细化锁粒度
 * */
// 懒汉式 -- 给方法内的代码块加锁
// 优点：...
// 缺点：并不能保证单例特性，同上面LazierModel1，只限制为空是不行的
class LazierModel2_codeBlock {
    private static LazierModel2_codeBlock lazier;

    private LazierModel2_codeBlock() {
    }

    // 提供外部访问接口
    public static LazierModel2_codeBlock getInstance() {
        if (lazier == null) {
            try {
                // ext job here
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LazierModel2_codeBlock.class) {
                // 为防止每次调用该接口都生成一个对象，需要判断该对象是否已经生成
                lazier = new LazierModel2_codeBlock();
            }

        }
        return lazier;
    }
}

/*
 * 思考
 * 上面的加锁实验中，给类加锁，不可行，给方法加锁降低并发效率，给代码块加锁，并不能保证单例性
 * 我们想要保证并发特性的同时，又想保证单例特性，那么我们就需要综合上面的
 * */

// 懒汉式 -- DCL
// 优点：即小粒度锁，又保证线程安全
// 缺点：不能保证代码有序执行，即不能保证指令重排
class LazierModel3_DCL {
    private static LazierModel3_DCL lazier;

    private LazierModel3_DCL() {
    }

    // 提供外部访问接口
    public static LazierModel3_DCL getInstance() {
        if (lazier == null) {
            try {
                // ext job here
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 方法加锁，保证了小粒度锁，且保证了线程能按顺序执行
            synchronized (LazierModel3_DCL.class) {
                // 为防止每次调用该接口都生成一个对象，需要判断该对象是否已经生成
                if (lazier == null) {
                    // 保证了顺序执行的同时，又保证了只能生成一个对象
                    lazier = new LazierModel3_DCL();
                }
            }

        }
        return lazier;
    }
}

/*
 * 思考
 * 给单例实现DCL之后，就一定能保证安全性嘛？
 * 答案是不一定。
 * 我们需要再往深处挖掘一下，涉及计算机编译原理，我们的代码会被编译成汇编语言，然后再在计算机上执行
 * 此时就涉及到了一个问题，指令重排，指令重排会发生在当指令重排不影响最总结果时，就可能发生指令重排。
 * 我们这里是会发生指令重排的，它说的不影响结果，并不是指我们这种生成一个对象这么具体，
 * 例。参考：https://www.zhihu.com/search?type=content&q=%E6%8C%87%E4%BB%A4%E9%87%8D%E6%8E%92
 * 为了解决，指令重排的问题，我们需要加上volatile关键字
 * */

// 懒汉式 -- 指令有序执行
// 优点：绝对的安全
// 缺点：较为复杂逻辑
class LazierModel4 {
    private static volatile LazierModel4 lazier;

    private LazierModel4() {
    }

    // 提供外部访问接口
    public static LazierModel4 getInstance() {
        if (lazier == null) {
            try {
                // ext job here
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 方法加锁，保证了小粒度锁，且保证了线程能按顺序执行
            synchronized (LazierModel4.class) {
                // 为防止每次调用该接口都生成一个对象，需要判断该对象是否已经生成
                if (lazier == null) {
                    // 主要是为了防止指令重排发生在这里，所以我们需要给变量加volatile原语及时通知其他线程更新变量
                    lazier = new LazierModel4();
                }
            }

        }
        return lazier;
    }
}

// 懒汉式 -- 静态内部类
// 优点：线程安全，体积小，懒加载
// 缺点：较为复杂逻辑
class LazierModel5 {

    private static class Lazier {
        private static final LazierModel5 lazier = new LazierModel5();
    }

    private LazierModel5() {
    }

    // 提供外部访问接口
    public static LazierModel5 getInstance() {
        return Lazier.lazier;
    }
}

// 枚举式
// 优点：线程安全，体积小
// 缺点：
enum LazierModel6 {
    Lazier;

    public static LazierModel6 getInstance() {
        return Lazier;
    }
}