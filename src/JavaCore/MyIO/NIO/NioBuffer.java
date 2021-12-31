package JavaCore.MyIO.NIO;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author masuo
 * @date: 2021/12/28/ 下午7:30
 * @description Nio 自动集成了缓存
 */
public class NioBuffer {

    /**
     * NIO 中自带缓存区
     * 下面我们来查看他是如何生成缓存区以及给缓存区赋值的
     * 为缓存区分配大小
     */
    @Test
    public void test0() {

        /*创建缓冲区*/

        //1、创建指定大小的缓存区
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);

        //2、根据传过来的内容自动创建缓存区
        Byte b = '0';
        byte[] bytes = new byte[]{'h', 'e', 'l', 'l', 'o', '!', '0'};
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes, 0, bytes.length);
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());

        /*缓存区的操作*/

        //向缓存区添加数据
        buffer1.put("hello".getBytes());
        //获取缓存区的最大容量
        System.out.println(buffer1.capacity());
        //最大限制
        System.out.println(buffer1.limit());
        //当前位置
        System.out.println(buffer1.position());

        /*对缓存区内容的操作*/

        //获取缓存区中的内容,通过array可以获取缓存区
        byte[] bytes1 = buffer1.array();
        //在将其转换成String类型
        String s = new String(bytes1);
        //这里会输出一大堆空白，因为指定大小的缓存区不一定能够全部用完，所以会存在空值（=0即为空）
        System.out.println(s);
        //此时我们可以通过获取ByteBuffer的当前位置来进行适当的截取，以获取有用数据
        new String(bytes1, 0, buffer1.position());

    }
}
