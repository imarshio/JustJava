package JavaCore.MyThread.multiThread;

/**
 * @author masuo
 * @data 2021/9/27 12:53
 * @Description 方法二-继承Runnable
 */

public class ExtRunnable {
    public static void main(String[] args) {

        ExtRunnable er = new ExtRunnable();
        er.test();
    }

    private void test() {
        System.out.println("当前线程名称"+Thread.currentThread().getName());

        System.out.println("继承Runnable");
        myRunThread rt = new myRunThread();

        //注意这里不能直接调用Runnable重写的run方法，那样是不规范的

        Thread thread = new Thread(rt);
        thread.start();
    }
}


class myRunThread implements Runnable{

    @Override
    public void run() {
        System.out.println("当前线程名称"+Thread.currentThread().getName());
        System.out.println("重写run方法");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}