package MyJVM.Heap;

/**
 * @author: masuo
 * @data: 2021/8/2 20:45
 * @Description: 设置堆内存中老年代和新生代的比例新生代默认为1，老年代为2
 * -XX:NewRatio：设置新生代与老年代的比例,默认值为2，代表老年代比新生代的比例为2：1
 * -XX:SurvivorRatio，设置新生代中Eden区与Survivor的比例，默认值是8，代表Eden：S0：S1 = 8：1：1
 * <p>
 * -XX:-UseAdaptiveSizePolicy：关闭自适应分配策略
 * -Xmn:设置新生代的空间大小（一般不设置，拥有较高的优先级）
 */

public class EdenSurvivor {

    public static void main(String[] args) {
        System.out.println("00000");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
