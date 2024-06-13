package MyIO.AIO;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @author masuo
 * @date: 2021/12/29/ 下午8:09
 * @description AIO 异步非阻塞 服务端
 * 服务端发起调用后，不需要等待调用结果，客户端在完成操作后，会以事件回调的方式返回，不会阻塞线程
 * 从jdk1.7开始
 */
public class AioAsynchronousServerSocketChannel {

    @Test
    public void test1() {

        try {

            // 计数器
            /*
             * CountDownLatch
             *   .await():CountDownLatch数字不等于0时，阻塞
             *   .countDown():调用一次该方法 -1
             * */

            CountDownLatch countDownLatch = new CountDownLatch(1);
            // 1.创建服务端
            AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();

            // 2.绑定端口
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 9999));

            // 3.接收客户端
            /*
             * 注意，这里在AIO中没有多路复用器了，直接接受客户端即可
             *
             * */
            /*
             * 第一个参数：附件，没有写空
             * 第二个参数：接口 CompletionHandler<AsynchronousSocketChannel,? super A> handler
             * 完成处理程序，这个会在客户端完成操作之后，调用回调事件
             * */
            serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                /**
                 *
                 * @param result 客户端管道
                 * @param attachment 附件
                 */
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    // 完成操作后，回调函数
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    result.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {
                        @Override
                        public void completed(Integer result, Object attachment) {
                            // 成功接收到客户端时执行

                            // 接受数据
                            byte[] array = byteBuffer.array();
                            // 转换
                            String s = new String(array);
                            System.out.println("服务端获取到的数据：" + s);

                            countDownLatch.countDown();
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            // 接收客户端失败时执行
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    // 失败后调用失败的回调函数

                }
            });

            // 阻塞主线程
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
