package JavaCore.MyLock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author masuo
 * @data 2021/9/27 15:47
 * @Description 锁
 * 几种常见的锁
 * 公平锁 - 非公平锁
 * 悲观锁 - 乐观锁
 * 独享锁 - 共享锁
 * 互斥锁 - 读写锁
 * 分段锁：一种锁的设计，细化锁的操作，例，当数组中有一个元素需要加锁，那么只给这个元素加锁即可
 * 偏向锁、轻量锁、重量锁：指Synchronized锁的状态，在对象头中的标识
 * 自旋锁：尝试获取锁的线程不会立即阻塞，而是尝试循环去获取
 * 可重入锁（递归锁）：在同一线程的外层方法获取锁的时候，进入内层方法时，会自动获取锁。
 * DCL：Double Check Lock 双重检测锁
 * AQS：Abstract Queued Synchronizer
 * CAS：Compare And Swap* 比较并交换 原子操作
 */

public class MySync {

    //Java中有两种加锁的方式，
    /*
     * 1.synchronized  关键字:同步的  1.5之前的唯一一种加锁方式
     *  对 对象，类，方法 等加锁
     * 2.Lock  接口  public interface Lock{} since 1.5
     *
     * */

    //用于在多个线程中需要对同一段数据进行访问时候，出现的不安全情况。
    // 因为多个线程执行同一段代码会造成数据不安全，所以需要用synchronized来同步代码，
    // 所以我们可以对代码加锁
    // 在定义接口方法时不能使用synchronized关键字。
    // 构造方法不能使用synchronized关键字，但可以使用synchronized代码块来进行同步。
    // synchronized 关键字不能被继承 ，如果要同步需要显式的加上关键字。
    // synchronized 关键字修饰的方法如果被重写默认不同步，如果要同步需要显式的加上关键字，或者super父类的方法也就相当于同步了。

    /* 锁类 -- 使用synchronized修饰类
     * 作用域：大括号中的代码
     * 业务场景：两个线程，都需要用到一个类，但是这个类不可共享
     * */
    @Test
    public void synchronizedClassTest() {

        //建立线程池,容量为2
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        //不加锁
        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(SyncClass::run);
            //Thread[pool-1-thread-1,5,main]静态代码块执行中。。
            //Thread[pool-1-thread-2,5,main]静态代码块执行中。。
            // wait 2s
            //Thread[pool-1-thread-1,5,main]静态代码块执行完成。。
            //Thread[pool-1-thread-2,5,main]静态代码块执行完成。。
        }
        //加锁
        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                //修饰一个类
                synchronized (SyncClass.class) {
                    // job here
                    System.out.println(Thread.currentThread() + "尝试调用类。。");
                    SyncClass.run();
                }
                //Thread[pool-1-thread-1,5,main]锁住了类。。
                //Thread[pool-1-thread-1,5,main]静态代码块执行中。。
                // wait 2s
                //Thread[pool-1-thread-1,5,main]静态代码块执行完成。。
                //Thread[pool-1-thread-2,5,main]锁住了类。。
                //Thread[pool-1-thread-2,5,main]静态代码块执行中。。
                // wait 2s
                //Thread[pool-1-thread-2,5,main]静态代码块执行完成。。
            });
        }
        fixedThreadPool.shutdown();
        try {
            boolean finished = fixedThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SyncClass {
        public static void run() {
            System.out.println(Thread.currentThread() + "静态代码块执行中。。");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "静态代码块执行完成。。");
        }
    }

    /* 锁方法 -- 使用synchronized修饰方法
     * 作用域：锁定的方法一次执行周期，直到方法执行完成
     * 业务场景：两个线程，都需要用到一个方法，但是这个方法不可共享
     * */
    @Test
    public void synchronizedMethodTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + "尝试执行方法。。");
                //执行方法
                this.syncMethod();
                //未加锁
                //Thread[pool-1-thread-1,5,main]锁住方法。。
                //Thread[pool-1-thread-1,5,main]静态方法执行中。。
                //Thread[pool-1-thread-2,5,main]锁住方法。。
                //Thread[pool-1-thread-2,5,main]静态方法执行中。。
                // wait 2s
                //Thread[pool-1-thread-2,5,main]静态方法执行完成。。
                //Thread[pool-1-thread-1,5,main]静态方法执行完成。。

                //加锁
                //Thread[pool-1-thread-1,5,main]锁住方法。。
                //Thread[pool-1-thread-2,5,main]锁住方法。。
                //Thread[pool-1-thread-1,5,main]静态方法执行中。。
                // wait 2s
                //Thread[pool-1-thread-1,5,main]静态方法执行完成。。
                //Thread[pool-1-thread-2,5,main]静态方法执行中。。
                // wait 2s
                //Thread[pool-1-thread-2,5,main]静态方法执行完成。。
            });
        }

        fixedThreadPool.shutdown();

        try {
            //这里需要等待线程执行完成，不然线程还未执行完成，进程就已经shutdown了，就看不到执行过程了
            boolean finished = fixedThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void syncMethod() {
        // job here
        System.out.println(Thread.currentThread() + "静态方法执行中。。");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + "静态方法执行完成。。");
    }

    /* 锁代码块 -- 修饰代码块
     * 作用域：锁定的方法一次执行周期，直到方法执行完成
     * 业务场景：两个线程，都需要用到一个方法，但是这个方法不可共享
     * */
    @Test
    public void synchronizedBlockTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + "尝试执行代码块。。");
                //执行方法
                this.syncBlock();
                //未加锁
                //Thread[pool-1-thread-1,5,main]锁住方法。。
                //Thread[pool-1-thread-1,5,main]静态方法执行中。。
                //Thread[pool-1-thread-2,5,main]锁住方法。。
                //Thread[pool-1-thread-2,5,main]静态方法执行中。。
                // wait 2s
                //Thread[pool-1-thread-2,5,main]静态方法执行完成。。
                //Thread[pool-1-thread-1,5,main]静态方法执行完成。。

                //加锁
                //Thread[pool-1-thread-1,5,main]锁住方法。。
                //Thread[pool-1-thread-2,5,main]锁住方法。。
                //Thread[pool-1-thread-1,5,main]静态方法执行中。。
                // wait 2s
                //Thread[pool-1-thread-1,5,main]静态方法执行完成。。
                //Thread[pool-1-thread-2,5,main]静态方法执行中。。
                // wait 2s
                //Thread[pool-1-thread-2,5,main]静态方法执行完成。。
            });
        }

        fixedThreadPool.shutdown();

        try {
            //这里需要等待线程执行完成，不然线程还未执行完成，进程就已经shutdown了，就看不到执行过程了
            boolean finished = fixedThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void syncBlock() {

        synchronized (this) {
            System.out.println(Thread.currentThread() + "代码块执行中。。");
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println(Thread.currentThread() + "代码块执行完成。。");
        }
    }

    /* 锁静态方法 -- 修饰静态方法
     * 作用域：这个类的所有对象，因为静态方法是属于类的
     * 业务场景：
     * */
    @Test
    public void synchronizedStaticMethod() {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + "尝试执行静态代码。。");
                //执行方法
                this.callStaticMethod();
            });
        }

        fixedThreadPool.shutdown();

        try {
            //这里需要等待线程执行完成，不然线程还未执行完成，进程就已经shutdown了，就看不到执行过程了
            boolean finished = fixedThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void syncStaticMethod() {
        System.out.println(Thread.currentThread() + "静态代码执行中。。");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
        System.out.println(Thread.currentThread() + "静态代码执行完成。。");
    }

    public void callStaticMethod() {
        syncStaticMethod();
    }

    //A. 无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；
    // 如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁。
    //B. 每个对象只有一个锁（lock）与之相关联，谁拿到这个锁谁就可以运行它所控制的那段代码。
    //C. 实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。
    //D.同步关键字锁的是对象
}
