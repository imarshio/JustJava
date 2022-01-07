package JavaCore.MyIO.NIO;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author masuo
 * @date: 2021/12/28/ 下午8:23
 * @description 客户端 SocketChannel
 */
public class NioClientSocketChannel {

    public static void main(String[] args) {
        NioClientSocketChannel nsc = new NioClientSocketChannel();
        nsc.test1();
    }

    //对应ServerSocketChannel的 test 1-3
    @Test
    public void test1() {

        try {
            //1.创建客户端channel
            SocketChannel socketChannel = SocketChannel.open();

            //2.设置为非阻塞
            socketChannel.configureBlocking(false);


            //3.创建选择器（多路复用器）
            Selector selector = Selector.open();

            //4.将客户端管道注册到多路复用器,
            /*
             * 监听客户端是否连接到服务端，没有连接时则阻塞
             * 当客户端连接到服务端时，会进入
             * */
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            //5.连接服务端
            socketChannel.connect(new InetSocketAddress("172.16.6.8", 30000));

            //6.连接客户端通道的状态
            selector.select();

            //7.获取每一个被监听到的状态
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //8.遍历获取到的状态
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                //9.判断客户端是否连接了服务器
                if (next.isConnectable()) {
                    System.out.println("客户端可以连接服务端了。");
                    System.out.println("此时，客户端连接状态：" + socketChannel.isConnected());
                    System.out.println(socketChannel);
                    //connection-pending remote=/127.0.0.1:9999
                    // 连接挂起

                    //此时服务端与客户端并没有真正的进行连接，只是代表可以进行连接

                    //10.让默认客户端管道挂起状态变为真正的连接状态
                    if (socketChannel.isConnectionPending()) {
                        System.out.println("客户端连接状态：" + socketChannel.isConnected());
                        /*
                         * 注意，在发起连接请求之后，完成finishConnect之前，客户端一直都是处于未连接的状态
                         * */
                        socketChannel.finishConnect();
                        System.out.println("客户端连接状态：" + socketChannel.isConnected());
                        System.out.println(socketChannel);
                        //connected local=/127.0.0.1:54158 remote=/127.0.0.1:9999
                    }

                    //11.向服务端输出一句话
                    /*
                     * Nio中存在缓存区，先将数据发送至缓存区，然后再将数据发送到服务端
                     * */
                    ByteBuffer wrap = ByteBuffer.wrap("服务端你好".getBytes());

                    //12.将缓存区中的数据输出到服务端
                    socketChannel.write(wrap);

                    //13.优化第12步，我们需要在可写时，才进行写入
                    //if (next.isWritable()) {
                    //    socketChannel.write(wrap);
                    //}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //对应test4
    @Test
    public void test2() {

        try {
            //1.创建客户端channel
            SocketChannel socketChannel = SocketChannel.open();

            //2.设置为非阻塞
            socketChannel.configureBlocking(false);

            //3.创建选择器（多路复用器）
            Selector selector = Selector.open();

            //4.将客户端管道注册到多路复用器,
            /*
             * 监听客户端是否连接到服务端，没有连接时则阻塞
             * 当客户端连接到服务端时，会进入
             * */
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            //5.连接服务端
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

            //6.连接客户端通道的状态,轮询获取选择器上已就绪的事件，selector.select() > 0代表该事件已就绪
            while (selector.select() > 0) {

                //7.获取每一个被监听到的状态
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                //8.遍历获取到的状态
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    //9.判断客户端是否连接了服务器
                    if (next.isConnectable()) {
                        System.out.println("客户端连接了服务端。");
                        System.out.println(socketChannel);
                        //connection-pending remote=/127.0.0.1:9999
                        // 连接挂起

                        //此时服务端与客户端并没有真正的进行连接，只是代表可以进行连接

                        //10.让默认客户端管道挂起状态变为真正的连接状态
                        if (socketChannel.isConnectionPending()) {
                            socketChannel.finishConnect();
                            System.out.println(socketChannel);
                            //connected local=/127.0.0.1:54158 remote=/127.0.0.1:9999
                        }

                        //11.向服务端输出一句话
                        /*
                         * Nio中存在缓存区，先将数据发送至缓存区，然后再将数据发送到服务端
                         * */
                        ByteBuffer wrap = ByteBuffer.wrap("服务端你好".getBytes());

                        //12.将缓存区中的数据输出到服务端
                        socketChannel.write(wrap);

                        //13.优化第12步，我们需要在可写时，才进行写入
                        if (next.isWritable()) {
                            socketChannel.write(wrap);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {

        try {
            //1.创建客户端channel
            SocketChannel socketChannel = SocketChannel.open();

            //2.设置为非阻塞
            socketChannel.configureBlocking(false);

            //3.创建选择器（多路复用器）
            Selector selector = Selector.open();

            //4.将客户端管道注册到多路复用器,
            /*
             * 监听客户端是否连接到服务端，没有连接时则阻塞
             * 当客户端连接到服务端时，会进入
             * */
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            //5.连接服务端
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

            while (selector.select() > 0) {
                //6.连接客户端通道的状态

                //7.获取每一个被监听到的状态
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                //8.遍历获取到的状态
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    //9.判断客户端是否连接了服务器
                    if (next.isConnectable()) {
                        System.out.println("客户端连接了服务端。");
                        //System.out.println(socketChannel);
                        //connection-pending remote=/127.0.0.1:9999
                        // 连接挂起

                        //此时服务端与客户端并没有真正的进行连接，只是代表可以进行连接

                        //10.让默认客户端管道挂起状态变为真正的连接状态
                        if (socketChannel.isConnectionPending()) {
                            socketChannel.finishConnect();
                            //System.out.println(socketChannel);
                            //connected local=/127.0.0.1:54158 remote=/127.0.0.1:9999
                        }

                        //对管道进行监听
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (next.isReadable()) {
                        //11.读取服务端信息
                        // 建立缓冲区
                        ByteBuffer wrap = ByteBuffer.allocate(1024);

                        //12.将消息读取到缓冲区
                        socketChannel.read(wrap);

                        //输出消息
                        byte[] array = wrap.array();
                        String s = new String(array);
                        System.out.println("读取到的服务端消息为：" + s);

                        // 向服务端写数据
                        socketChannel.write(ByteBuffer.wrap("服务端你好".getBytes()));
                        //监听
                        //socketChannel.register(selector, SelectionKey.OP_WRITE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
