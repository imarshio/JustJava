package JavaCore.MyJVM.Heap;

/**
 * @author: masuo
 * @data: 2021/8/1 22:09
 * @Description: 堆的唯一性
 *
 *  堆空间大小的设置 ：-Xms10m（初始堆空间） ,-Xmx10m(最大堆空间)
 *  -Xms:10m -Xmx:10m
 *  -XX:+PrintGCDetials:打印细节
 *  -X: 是jvm运行参数
 *  ms是memory start
 *
 * 手动设置：-Xms10m -Xmx10m
 *      开发过程中建议将初始值和最大的内存设置成一样的，
 *      原因是频繁的扩容与释放会浪费系统性能，
 */

public class HeapUniqueness {

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
