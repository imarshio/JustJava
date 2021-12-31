package JavaCore.MyThread.multiThread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author masuo
 * @data 2021/9/27 13:27
 * @Description 实现Executor
 */

public class ExtExecutor {
    public static void main(String[] args) {
        execute();
    }

    private static void execute() {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println("这是第"+ finalI +"个线程");
            });

        }
    }
}


class exeThread implements Executor{

    @Override
    public void execute(Runnable command) {

    }
}