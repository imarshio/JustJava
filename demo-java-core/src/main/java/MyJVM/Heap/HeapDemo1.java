package MyJVM.Heap;

/**
 * @author: masuo
 * @data: 2021/8/1 22:11
 * @Description: -Xms20m，设置堆空间的大小,-Xmx20m
 */

public class HeapDemo1 {

    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
