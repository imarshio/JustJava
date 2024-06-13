package MyThread.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author masuo
 * @data 2021/9/27 13:09
 * @Description TODO
 */

public class ExtCallable {
    public static void main(String[] args) {
        callThread<Integer> ct = new callThread<>();
        ct.setItem(10);
        try {
            Integer call = ct.call();
            System.out.println(call);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("使用FutureTask");
        FutureTask<Integer> ft = new FutureTask<>(ct);
        new Thread(ft).start();
    }
}

class callThread<V> implements Callable<V> {

    V item;

    public callThread() {
    }

    public callThread(V item) {
        this.item = item;
    }

    @Override
    public V call() throws Exception {

        System.out.println(Thread.currentThread().getName());
        System.out.println(item);

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        return item;
    }

    public void setItem(V item) {
        this.item = item;
    }
}

