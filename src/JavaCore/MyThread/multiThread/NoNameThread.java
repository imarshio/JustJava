package JavaCore.MyThread.multiThread;

/**
 * @author masuo
 * @data 2021/9/27 13:01
 * @Description 无名称线程
 */

public class NoNameThread {

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });
    }
}
