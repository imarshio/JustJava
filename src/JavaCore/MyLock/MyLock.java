package JavaCore.MyLock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author masuo
 * @data 11/1/2022 上午11:33
 * @Description Lock since 1.5
 * 是一个接口
 * 实现类有ReentrantLock、Segment、ReadLock、WriteLock、ReadLockView、WriteLockView
 */


public class MyLock {

    private static int Y;

    @Test
    public void lockTest1() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        //线程开启之前声明锁，线程开始后争夺锁
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + "尝试执行代码。。");
                //执行方法
                reentrantTryLock(lock);
            });
        }

        fixedThreadPool.shutdown();

        try {
            //这里需要等待线程执行完成，不然线程还未执行完成，进程就已经shutdown了，就看不到执行过程了
            boolean finished = fixedThreadPool.awaitTermination(100, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * tryLock()方法 非公平方式
     *
     * @param lock 线程公共锁
     */
    public void reentrantTryLock(Lock lock) {

        System.out.println(Thread.currentThread() + "reentrantTryLock尝试加锁。。");
        // tryLock 尝试获取锁，如果获取到锁，返回true，继续执行，没获取到锁，则返回false，不继续执行
        if (lock.tryLock()) {
            System.out.println(Thread.currentThread() + "加锁成功。。");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                // job here...
                System.out.println(Thread.currentThread() + "执行代码。。");
                for (int i = 0; i < 10; i++) {
                    Y++;
                    System.out.println(Thread.currentThread().getName() + " y=" + Y);
                }

            } finally {
                //解锁
                lock.unlock();
                System.out.println(Thread.currentThread() + "解锁成功。。");
            }
        } else {
            System.out.println(Thread.currentThread() + "尝试加锁失败。。");
        }
    }

    @Test
    public void lockTest2() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        //线程开启之前声明锁，线程开始后争夺锁
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + "尝试执行代码。。");
                //执行方法
                reentrantLock(lock);
            });
        }

        fixedThreadPool.shutdown();

        try {
            //这里需要等待线程执行完成，不然线程还未执行完成，进程就已经shutdown了，就看不到执行过程了
            boolean finished = fixedThreadPool.awaitTermination(100, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * lock()方法
     *
     * @param lock 线程公共锁
     */
    public void reentrantLock(Lock lock) {
        System.out.println(Thread.currentThread() + "reentrantLock尝试加锁。。");
        // lock 尝试获取锁，如果获取不到，线程会进入阻塞状态（禁用，休眠），直到获取锁
        lock.lock();
        System.out.println(Thread.currentThread() + "加锁成功。。");
        try {
            Thread.sleep(2000);
            // job here...
            System.out.println(Thread.currentThread() + "执行代码。。");
            for (int i = 0; i < 10; i++) {
                Y++;
                System.out.println(Thread.currentThread().getName() + " y=" + Y);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
            System.out.println(Thread.currentThread() + "解锁成功。。");
        }
    }

    @Test
    public void lockTest3() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        //线程开启之前声明锁，线程开始后争夺锁
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + "尝试执行代码。。");
                //执行方法
                reentrantTryWithLock(lock);
            });
        }

        fixedThreadPool.shutdown();

        try {
            //这里需要等待线程执行完成，不然线程还未执行完成，进程就已经shutdown了，就看不到执行过程了
            boolean finished = fixedThreadPool.awaitTermination(100, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * tryLock()方法
     *
     * @param lock 线程公共锁
     */
    public void reentrantTryWithLock(Lock lock) {

        System.out.println(Thread.currentThread() + "reentrantTryWithLock尝试加锁。。");
        try {
            // tryLock 尝试获取锁，如果获取到锁，返回true，继续执行，没获取到锁，则返回false，不继续执行
            // 带参数，指在参数给定的时间内，重复尝试获取锁
            if (lock.tryLock(1, TimeUnit.MINUTES)) {
                System.out.println(Thread.currentThread() + "加锁成功。。");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    // job here...
                    System.out.println(Thread.currentThread() + "执行代码。。");
                    for (int i = 0; i < 10; i++) {
                        Y++;
                        System.out.println(Thread.currentThread().getName() + " y=" + Y);
                    }

                } finally {
                    //解锁
                    lock.unlock();
                    System.out.println(Thread.currentThread() + "解锁成功。。");
                }
            } else {
                System.out.println(Thread.currentThread() + "尝试加锁失败。。");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lockTest4() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        //线程开启之前声明锁，线程开始后争夺锁,true表明是公平锁
        Lock lock = new ReentrantLock(true);

        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + "尝试执行代码。。");
                //执行方法
                reentrantInterruptLock(lock);
            });
        }

        fixedThreadPool.shutdown();

        try {
            //这里需要等待线程执行完成，不然线程还未执行完成，进程就已经shutdown了，就看不到执行过程了
            boolean finished = fixedThreadPool.awaitTermination(100, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成。。");
            } else System.out.println("未执行成功。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * tryLock()方法
     *
     * @param lock 线程公共锁
     */
    public void reentrantInterruptLock(Lock lock) {

        System.out.println(Thread.currentThread() + "reentrantTryWithLock尝试加锁。。");
        try {
            // tryLock 尝试获取锁，如果获取到锁，返回true，继续执行，没获取到锁，则返回false，不继续执行
            // 带参数，指在参数给定的时间内，重复尝试获取锁
            if (lock.tryLock(1, TimeUnit.MINUTES)) {
                System.out.println(Thread.currentThread() + "加锁成功。。");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    // job here...
                    System.out.println(Thread.currentThread() + "执行代码。。");
                    for (int i = 0; i < 10; i++) {
                        Y++;
                        System.out.println(Thread.currentThread().getName() + " y=" + Y);
                    }

                } finally {
                    //解锁
                    lock.unlock();
                    System.out.println(Thread.currentThread() + "解锁成功。。");
                }
            } else {
                System.out.println(Thread.currentThread() + "尝试加锁失败。。");
                lock.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
