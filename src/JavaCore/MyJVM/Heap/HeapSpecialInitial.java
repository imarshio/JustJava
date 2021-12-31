package JavaCore.MyJVM.Heap;

/**
 * @author: masuo
 * @data: 2021/8/2 16:26
 * @Description:
 */

public class HeapSpecialInitial {



    public static void main(String[] args) {

        //获取JVM的总容量,默认等于：系统容量/64，我这里是512G
        long initialMemory = Runtime.getRuntime().totalMemory() /1024 /1024;

        //获取JVM最大内存，默认等于：系统容量/4
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;


        System.out.println("初始容量："+initialMemory+"M");
        System.out.println("最大容量："+maxMemory+"M");

        System.out.println("系统内存："+initialMemory*64/1024+"G");
        System.out.println("系统内存："+maxMemory*4/1024+"G");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
