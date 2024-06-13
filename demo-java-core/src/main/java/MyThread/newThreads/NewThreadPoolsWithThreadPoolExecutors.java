package MyThread.newThreads;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author masuo
 * @data 27/4/2022 上午8:58
 * @Description 使用ThreadPoolExecutors创建线程池
 */

public class NewThreadPoolsWithThreadPoolExecutors {

    /* ThreadPoolFactory : 推荐 */

    // 1.newCachedThreadPool 不限容量的线程池
    @Test
    public void cachedThreadPoolWithSelfDefinedFactoryTest() {

        // 1.newCachedThreadPool
        /* ThreadPoolExecutor
         *
         *
         *
         * */
        ExecutorService service = new ThreadPoolExecutor(2,
                10,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new SelfDefinedPoolFactory("order", "test-1", "0"),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 模拟循环任务
        for (int i = 0; i < 6; i++) {
            int threadNum = i;
            service.execute(() -> {
                // job here
                System.out.println(Thread.currentThread() + String.valueOf(threadNum) + "线程池执行中。。。");
                if (threadNum == 2) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + String.valueOf(threadNum) + "线程池执行完成。。。");
            });
        }

        // 启动有序关机，执行以前提交的任务，但不接受新任务
        // service.shutdown();
        try {
            boolean finished = service.awaitTermination(6, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("执行完成");
            } else System.out.println("执行没完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // 自定义线程工厂
    public static class SelfDefinedPoolFactory implements ThreadFactory {

        private final StringBuilder prefix = new StringBuilder();
        private final AtomicInteger nextId = new AtomicInteger(1);

        SelfDefinedPoolFactory(String serverName, String groupName, String pcNumber) {
            prefix.append(serverName).append("pool-").append(groupName).append("-").append(pcNumber).append("-");
        }

        @Override
        public Thread newThread(Runnable r) {
            String name = prefix.toString() + nextId.getAndIncrement();
            Thread thread = new Thread(null, r, name, 0);
            System.out.println(thread.getName());
            return thread;
        }
    }
}
