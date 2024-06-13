package MyIO.AIO;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @author masuo
 * @date: 2021/12/29/ 下午8:09
 * @description AIO 异步非阻塞  客户端
 * 服务端发起调用后，不需要等待调用结果，客户端在完成操作后，会以事件回调的方式返回，不会阻塞线程
 * 从jdk1.7开始
 */
public class AioAsynchronousSocketChannel {

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
            // 1.创建客户端
            AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();

            // 2.连接服务端
            clientChannel.connect(new InetSocketAddress("127.0.0.1", 9999), null, new CompletionHandler<Void, Object>() {
                @Override
                public void completed(Void result, Object attachment) {
                    // 连接服务端成功

                    // 向服务端发送消息
                    clientChannel.write(ByteBuffer.wrap("服务端你好。".getBytes()));
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    // 连接服务端失败
                }
            });

            // 向服务端发送消息时，阻塞主线程
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
