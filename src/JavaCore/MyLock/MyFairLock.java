package JavaCore.MyLock;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author masuo
 * @data 29/4/2022 上午9:08
 * @Description 公平锁 ReentrantLock(true)
 */

public class MyFairLock {

    @Test
    public void test() {
        ReentrantLock fairLock = new ReentrantLock(true);

        ExecutorService service = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5),new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println(new Date());
        try {
            int i = 0;
            while (i < 15){
                service.execute(() -> {
                    if (!fairLock.tryLock()) {
                        System.out.println(Thread.currentThread().getName() + "获取锁失败");
                        fairLock.lock();
                        System.out.println("入队");
                    }
                    System.out.println(Thread.currentThread().getName() + "获取锁成功");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fairLock.unlock();
                });
                i++;
            }

            service.shutdown();

            if (service.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("执行成功");
            }

            System.out.println("执行失败");
            System.out.println(new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.execute(()-> new Thread(() -> {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName());
            ReentrantLock lock = new ReentrantLock(true);
            lock.lock();

            if (Thread.interrupted()) {
                System.out.println("Thread is interrupted");
            }
        }));
    }
}
