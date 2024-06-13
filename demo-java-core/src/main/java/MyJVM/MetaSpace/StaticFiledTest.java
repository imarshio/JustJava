package MyJVM.MetaSpace;

/**
 * @author: masuo
 * @data: 2021/8/8 12:18
 * @Description: 静态对象在new得过程中存放位置
 * 设置参数：静态对象大小为10m，为了测试他具体该存放在那个位置，我们将堆空间设置的比10m大，
 * 设置堆空间大小为：15m（最大也是15）
 * Xms:15m Xmx:15m
 * <p>
 * 设置方法区（元空间）大小为：20m
 * XX:MetaspaceSize=20m
 * <p>
 * 打印GC详细信息：
 * XX:+PrintGCDetails
 */

public class StaticFiledTest {

    public static Byte[] bytes = new Byte[1204 * 10]; // 10m

    public static void main(String[] args) {
        System.out.println(bytes);
    }
}
