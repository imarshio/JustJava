package JavaCore.MyThread;

import java.text.SimpleDateFormat;

/**
 * @author masuo
 * @create 2021/7/27 8:47
 * @Description
 */
public class UseThreads {

    public void ThreadLocalTest(){
        //ThreadLocal一般用来存放线程不安全且经常使用的变量
        //在本地线程池建立一个simpleDateFormat，以后使用时，直接调用即可
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
        sdf.set(simpleDateFormat);

        //获取
        SimpleDateFormat simpleDateFormat1 = sdf.get();
    }
}
