package JavaCore.MyThread.newThreads;

import org.junit.Test;

import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author masuo
 * @data 10/1/2022 下午3:07
 * @Description 新建线程池的方法
 */

public class NewThreadPools {

    /* Executors */

    //1.newCachedThreadPool 不限容量的线程池
    @Test
    public void cachedThreadPoolTest() {

        //1.newCachedThreadPool
        /*
         * 可缓存线程池，线程数量没有限制，可灵活回收线程
         * 如果设置线程空闲时间1分钟，则该线程自动终止，
         * 缺点，需要控制任务的数量，不能直接涌入大量任务，否则会导致系统瘫痪
         * */
        ExecutorService service = Executors.newCachedThreadPool();

        //模拟循环任务
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            service.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程池执行中。。。");
                if (finalI == 2) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程池执行完成。。。");
            });
        }

        //启动有序关机，执行以前提交的任务，但不接受新任务
        service.shutdown();
        try {
            boolean finished = service.awaitTermination(1, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成");
            } else System.out.println("执行没完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //2.newFixedThreadPool 容量有限制的线程池
    @Test
    public void fixedThreadPoolTest() {

        //newFixedThreadPool(线程池容量)
        /*
         * 在达到指定大小前，每提交一个任务都会创建一个线程，达到最大长度后，进入队列
         * 不会自动回收线程
         * */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        //模拟循环任务
        for (int i = 0; i < 11; i++) {
            int finalI = i;
            fixedThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程池执行中。。。");
                if (finalI % 2 == 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程执行完成。。。");
            });
        }

        //关闭线程池，防止资源浪费
        fixedThreadPool.shutdown();

        try {
            boolean finished = fixedThreadPool.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                System.out.println("执行完成");
            } else System.out.println("执行没完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //3.newSingledThreadPool 单一线程，挂掉后会有另外一个线程取代
    @Test
    public void singledThreadPoolTest() {

        //newSingleThreadExecutor 单一线程执行器
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        //模拟循环任务
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            singleThreadExecutor.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程池执行中。。。");
                if (finalI == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (finalI == 1) {
                    throw new NoSuchElementException("1");
                }
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程执行完成。。。");
            });
        }

        //关闭线程池
        singleThreadExecutor.shutdown();
        try {
            boolean finished = singleThreadExecutor.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                System.out.println("执行完成");
            } else System.out.println("执行没完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //4.1newScheduledThreadPool 容量有限的线程池，支持定时周期的执行任务 -- execute
    @Test
    public void scheduledThreadPoolNoScheduledTest() {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        System.out.println("***************无schedule***************");
        //模拟执行任务
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            //execute 跟普通线程池无区别
            scheduledThreadPool.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程池执行中。。。");
                if (finalI == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + String.valueOf(finalI) + "线程执行完成。。。");
            });
        }

        //关闭线程池
        scheduledThreadPool.shutdown();
        try {
            boolean finished = scheduledThreadPool.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                System.out.println("执行完成");
            } else System.out.println("执行没完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //4.2newScheduledThreadPool 容量有限的线程池，支持定时周期的执行任务 -- schedule
    @Test
    public void scheduledThreadPoolScheduledTest() {

        //newScheduledThreadPool(容量)
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        System.out.println("***************有schedule***************");
        //模拟执行任务
        System.out.println(LocalTime.now());
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            //schedule，一次性操作，在给定的时间后执行
            scheduledThreadPool.schedule(() -> {
                // job here
                System.out.println(LocalTime.now().toString() + Thread.currentThread() + String.valueOf(finalI) + "线程池执行中。。。");
                if (finalI == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(LocalTime.now().toString() + Thread.currentThread() + String.valueOf(finalI) + "线程执行完成。。。");
            }, 5, TimeUnit.SECONDS);
            //这里，在程序启动5s后，线程池开始执行
        }
        //关闭线程池
        scheduledThreadPool.shutdown();
        try {
            boolean finished = scheduledThreadPool.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                System.out.println("执行完成");
            } else System.out.println("执行没完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //4.3newScheduledThreadPool 容量有限的线程池，支持定时周期的执行任务 --scheduleAtFixedRate
    @Test
    public void scheduledThreadPoolScheduleAtFixedRateTest() {

        //newScheduledThreadPool(容量)
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        System.out.println("***************有schedule***************");
        //模拟执行任务
        System.out.println(LocalTime.now());
        for (; ; ) {
            //scheduleAtFixedRate，周期性操作，在给定的时间后执行，
            // 第一次是在initDelay时间后启动，之后是initialDelay+period，在之后是initialDelay+2*period。。。
            scheduledThreadPool.scheduleAtFixedRate(() -> {
                // job here
                System.out.println(LocalTime.now().toString() + Thread.currentThread() + "线程池执行中。。。");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LocalTime.now().toString() + Thread.currentThread() + "线程执行完成。。。");
            }, 5, 10, TimeUnit.SECONDS);
            //这里，在程序启动5s后，线程池开始执行，之后每隔10s执行一次，你看到的打印结果无序
        }
        //因为是周期性执行，无需关闭连接池
    }
}

