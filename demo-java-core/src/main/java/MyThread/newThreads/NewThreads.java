package MyThread.newThreads;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author masuo
 * @data 10/1/2022 下午2:24
 * @Description 线程的几种创建方法, 推荐使用线程池创建线程，因为线程池能减少操作时间，如线程上下文切换
 */

public class NewThreads {

    @Test
    public void threadTest() {

        // 1.匿名类线程
        new Thread() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "匿名类线程执行。。。");
                super.run();
            }
        };

        // 2.继承Thread
        Thread extThread = new ExtThread();
        extThread.start();

        // 3.实现Runnable接口
        Runnable extRunnableThread = new ExtRunnableThread();
        extRunnableThread.run();

        // 4.实现Callable接口
        Callable<String> extCallableThread = new ExtCallableThread<>();
        try {
            extCallableThread.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 5.创建Future Task，在运行时执行给定的Callable
        FutureTask<String> futureTask = new FutureTask<>(extCallableThread);
        futureTask.run();

    }

    static class ExtThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "继承Thread线程执行中。。。");
        }
    }

    static class ExtRunnableThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "继承Runnable线程执行中。。。。");
        }
    }

    static class ExtCallableThread<V> implements Callable<V> {
        @Override
        public V call() {
            System.out.println(Thread.currentThread() + "继承Callable线程执行中。。。");
            return null;
        }
    }
}
