package JavaCore.MyJVM.MetaSpace;

/**
 * @author: masuo
 * @data: 2021/8/7 14:31
 * @Description: 设置Metaspace的大小
 *     -XX:MetaspaceSize:初始化方法区的大小，设置方法【-XX:MetaspaceSize=100m】
 *     -XX:MaxMetaspaceSize:方法区最大值,设置方法【-XX:MaxMetaspaceSize=100m】
 */

public class MetaSpaceSizeTest {

    public static void main(String[] args) {

        System.out.println("开始了");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("结束了");

    }
}
