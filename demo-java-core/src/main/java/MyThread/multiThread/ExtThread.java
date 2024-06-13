package MyThread.multiThread;

import org.junit.Test;

/**
 * @author masuo
 * @data 2021/9/27 12:47
 * @Description 方法一
 */

public class ExtThread {
    public static void main(String[] args) {
        ExtThread et = new ExtThread();

        System.out.println("当前线程名称：" + Thread.currentThread().getName());
        et.test();
    }

    @Test
    public void test() {
        myThread mt = new myThread();
        mt.start();
    }
}


class myThread extends Thread {

    @Override
    public void run() {

        System.out.println("重写run方法");
        for (int i = 0; i < 10; i++) {

            System.out.println(i);
        }
        System.out.println("当前线程名称：" + currentThread().getName());
    }
}