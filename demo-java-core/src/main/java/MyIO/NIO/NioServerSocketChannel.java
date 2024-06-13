package MyIO.NIO;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author masuo
 * @date: 2021/12/28/ 下午7:29
 * @description NIO 模式下特有的 ServerSocketChannel  -- 服务端的管道，一般一个服务对应一个管道
 */
public class NioServerSocketChannel {

    @Test
    public void test1() {
        try {

            // 1.通过ServerSocketChannel的open()方法创建一个管道
            /*
             * 注意这个ServerSocketChannel在服务端只有在没有接收到连接请求时才会被用到，
             * 一旦客户端连接到了服务端，此时服务端就会获取客户端的管道，
             * 并通过获得的客户端的管道与客户端进行通信
             * 到这里，
             * 客户端有一个SocketChannel的对象，
             * 服务端有一个ServerSocketChannel的对象和一个SocketChannel的对象
             * 并且只能通过Socket Channel与客户端通信
             * 在这个管道中，可以使用Byte Buffer读写数据，
             * 每次读写数据后都要对客户端管道加上对应的状态监听
             *
             */
            ServerSocketChannel serverChannel = ServerSocketChannel.open();

            // 2.默认管道位阻塞的，在Nio模式下，需要我们手动打开，使其非阻塞运行
            System.out.println("默认的blocking状态：" + serverChannel.isBlocking());
            serverChannel.configureBlocking(false);

            // 3.绑定对应的ip和port
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 9999));

            // 4.创建选择器（多路复用器）
            Selector selector = Selector.open();

            // 5.将管道注册到选择器
            /*
             * 参数1：选择器
             * 参数2：多路复用器监听服务端管道接受客户端的状态（此时仅仅是监听，没有状态 ）
             *       如果管道发生了对应的状态，此时管道为该状态，
             *       状态有四个常量，管道发生以下状态时，对应以下状态时，
             *           OP_READ = 1 << 0 = 1
             *           OP_WRITE = 1 << 2 = 4
             *           OP_CONNECT = 1 << 3 = 8
             *           OP_ACCEPT = 1 << 4 = 16
             * */
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 6.根据多路复用器，选择管道的状态
            /*
             * 没有状态，则会阻塞
             * 有状态，继续运行
             * */
            selector.select();

            // 7.获取所有被监听到的状态
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 获取状态的迭代器，方便后续操作
            Iterator<SelectionKey> keys = selectionKeys.iterator();

            System.out.println("------客户端连接成功------");

            while (keys.hasNext()) {
                // 8.服务端接收到了客户端的状态被监听到，此时我们需要接收客户端
                SelectionKey next = keys.next();
                SocketChannel client;
                if (next.isAcceptable()) {
                    // 9.服务端接收到了客户端，则获取客户端管道，为获取客户端数据做准备，
                    // 注意，这里获取的是客户端的管道
                    client = serverChannel.accept();
                    System.out.println(client);
                    // connected local=/127.0.0.1:9999 remote=/127.0.0.1:53517
                    // local : 本地（服务端）           remote ：客户端

                    {
                        // 10.通过客户端管道接收数据,需要先创建一个缓冲区，将读取到的数据放入缓冲区
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        client.read(allocate);

                        // 11.获取缓存区中的内容
                        byte[] array = allocate.array();
                        String msg = new String(array);
                        System.out.println("获取到的数据为：" + msg);
                    }

                    // 12.优化第11步，
                    // 存在的问题，如果客户端只是单纯的进行连接，并没有发送数据，
                    // 那么，直接调用读取就会获取空数据，甚至是报错
                    // 需要在可读时，且有内容时，才进行读取
                    // test2

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {

            // 1.通过ServerSocketChannel的open()方法创建一个管道
            /*
             * 注意这个ServerSocketChannel在服务端只有在没有接收到连接请求时才会被用到，
             * 一旦客户端连接到了服务端，此时服务端就会获取客户端的管道，
             * 并通过获得的客户端的管道与客户端进行通信
             * 到这里，
             * 客户端有一个SocketChannel的对象，
             * 服务端有一个ServerSocketChannel的对象和一个SocketChannel的对象
             * 并且只能通过Socket Channel与客户端通信
             * 在这个管道中，可以使用Byte Buffer读写数据，
             * 每次读写数据后都要对客户端管道加上对应的状态监听
             *
             */
            ServerSocketChannel serverChannel = ServerSocketChannel.open();

            // 2.默认管道位阻塞的，在Nio模式下，需要我们手动打开，使其非阻塞运行
            System.out.println("默认的blocking状态：" + serverChannel.isBlocking());
            serverChannel.configureBlocking(false);

            // 3.绑定对应的ip和port
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 9999));

            // 4.创建选择器（多路复用器）
            Selector selector = Selector.open();

            // 5.将管道注册到选择器
            /*
             * 参数1：选择器
             * 参数2：多路复用器监听服务端管道接受客户端的状态（此时仅仅是监听，没有状态 ）
             *       如果管道发生了对应的状态，此时管道为该状态，
             *       状态有四个常量，管道发生以下状态时，对应以下状态时，
             *           OP_READ = 1 << 0 = 1
             *           OP_WRITE = 1 << 2 = 4
             *           OP_CONNECT = 1 << 3 = 8
             *           OP_ACCEPT = 1 << 4 = 16
             * */
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 6.根据多路复用器，选择管道的状态
            /*
             * 没有状态，则会阻塞
             * 有状态，继续运行
             * */
            selector.select();

            // 7.获取所有被监听到的状态
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 获取状态的迭代器，方便后续操作
            Iterator<SelectionKey> keys = selectionKeys.iterator();

            System.out.println("------客户端连接成功------");

            while (keys.hasNext()) {
                // 8.服务端接收到了客户端的状态被监听到，此时我们需要接收客户端
                SelectionKey next = keys.next();
                if (next.isAcceptable()) {
                    // 9.服务端接收到了客户端，则获取客户端管道，为获取客户端数据做准备，
                    /*
                     * 注意，这里获取的是客户端的管道
                     * 管道是有两个口的，两个口需要分别处理,
                     * 类似蓄水池的进水口和出水口，需要由进水方和出水方分别管理
                     * 在服务端设置非阻塞，相当于有多个进水口（出水口）
                     * 在客户端设置非阻塞，相当于有多个出水口（进水口）
                     */
                    SocketChannel clientChannel = serverChannel.accept();
                    // 在这里，如果不设置非阻塞，那么就会报错，非法阻塞模式
                    clientChannel.configureBlocking(false);
                    System.out.println(clientChannel);
                    // connected local=/127.0.0.1:9999 remote=/127.0.0.1:53517
                    // local : 本地（服务端）           remote ：客户端


                    // 12.优化第11步，
                    // 需要在可读时，且有内容时，才进行读取，所以我们需要在获取连接之后重新注册读动作的监听
                    /*
                     * 注意，这里，我们是对获取到的客户端及逆行监听，而不是服务端，
                     * 服务端获取了客户端管道，并通过客户端管道与客户端通信，这里我们已经强调了很多遍，是获取客户端管道，与客户端通信
                     * 所以，我们想要对客户端管道进行读监听，那么必须将获取到的客户端管道注册到多路复用器上
                     * 之后，我们就可以通过多路复用器获取客户端管道了。
                     * */
                    clientChannel.register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    // 此时，表明selector读取到了读的状态，
                    // 但是，此时我们不能使用accept来接收客户端管道，因为客户端管道已经被接受了，再次接受的前提是客户端与服务端已经断开连接
                    // 所以我们需要使用另外的方法获取客户端管道，在我们接收客户端管道时，为了读取管道中的内容，
                    // 我们对其加了可读的监听，此时，我们将其注册到了多路复用器上，所以，我们可以通过多路复用器来获取服务端管道
                    // 我们已经知道这是客户端管道，所以在这里我们直接将其强转成客户端管道
                    SocketChannel clientChannel = (SocketChannel) next.channel();

                    // 10.通过客户端管道接收数据,需要先创建一个缓冲区，将读取到的数据放入缓冲区
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    clientChannel.read(allocate);

                    // 11.获取缓存区中的内容
                    byte[] array = allocate.array();
                    String msg = new String(array);
                    System.out.println("获取到的数据为：" + msg);

                    // 13.此时与接收到服务端时做相同的处理，如果我们想给客户端发消息则需要对写进行监听
                    clientChannel.register(selector, SelectionKey.OP_WRITE);
                } else if (next.isWritable()) {
                    // 14.此时，表明选择器读到了写的状态,
                    // 首先，我们还是先获取是哪个管道被接收了（即获取客户端管道）
                    SocketChannel clientChannel = (SocketChannel) next.channel();

                    // 其次，向缓冲区写入数据，
                    /*
                     * 注意，客户端与服务端的数据通信虽然跟以前在本质上没区别，都是使用数据流传输数据
                     * 但是，Nio自带缓冲区，所以我们需要先将数据写入缓冲区在发送，这样效率会高一点
                     * 再者，使用缓冲，本身就能提升性能
                     * */
                    ByteBuffer wrap = ByteBuffer.wrap("客户端，你好".getBytes());
                    clientChannel.write(wrap);

                    // 15.及时注册监听
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }

                // 16.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 会报空指针错误
    @Test
    public void test3() {
        try {

            // 1.通过ServerSocketChannel的open()方法创建一个管道
            /*
             * 注意这个ServerSocketChannel在服务端只有在没有接收到连接请求时才会被用到，
             * 一旦客户端连接到了服务端，此时服务端就会获取客户端的管道，
             * 并通过获得的客户端的管道与客户端进行通信
             * 到这里，
             * 客户端有一个SocketChannel的对象，
             * 服务端有一个ServerSocketChannel的对象和一个SocketChannel的对象
             * 并且只能通过Socket Channel与客户端通信
             * 在这个管道中，可以使用Byte Buffer读写数据，
             * 每次读写数据后都要对客户端管道加上对应的状态监听
             *
             */
            ServerSocketChannel serverChannel = ServerSocketChannel.open();

            // 2.默认管道位阻塞的，在Nio模式下，需要我们手动打开，使其非阻塞运行
            System.out.println("默认的blocking状态：" + serverChannel.isBlocking());
            serverChannel.configureBlocking(false);

            // 3.绑定对应的ip和port
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 9999));

            // 4.创建选择器（多路复用器）
            Selector selector = Selector.open();

            // 5.将管道注册到选择器
            /*
             * 参数1：选择器
             * 参数2：多路复用器监听服务端管道接受客户端的状态（此时仅仅是监听，没有状态 ）
             *       如果管道发生了对应的状态，此时管道为该状态，
             *       状态有四个常量，管道发生以下状态时，对应以下状态时，
             *           OP_READ = 1 << 0 = 1
             *           OP_WRITE = 1 << 2 = 4
             *           OP_CONNECT = 1 << 3 = 8
             *           OP_ACCEPT = 1 << 4 = 16
             * */
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 我们想要和客户端进行多次交互，就必须多次获取管道的状态
            while (true) {
                // 6.根据多路复用器，选择管道的状态
                /*
                 * 没有状态，则会阻塞
                 * 有状态，继续运行
                 * */
                selector.select();

                // 7.获取所有被监听到的状态
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 获取状态的迭代器，方便后续操作
                Iterator<SelectionKey> keys = selectionKeys.iterator();

                System.out.println("------客户端连接成功------");

                while (keys.hasNext()) {
                    // 8.服务端接收到了客户端的状态被监听到，此时我们需要接收客户端
                    SelectionKey next = keys.next();
                    if (next.isAcceptable()) {
                        // 9.服务端接收到了客户端，则获取客户端管道，为获取客户端数据做准备，
                        /*
                         * 注意，这里获取的是客户端的管道
                         * 管道是有两个口的，两个口需要分别处理,
                         * 类似蓄水池的进水口和出水口，需要由进水方和出水方分别管理
                         * 在服务端设置非阻塞，相当于有多个进水口（出水口）
                         * 在客户端设置非阻塞，相当于有多个出水口（进水口）
                         */
                        SocketChannel clientChannel = serverChannel.accept();
                        // 在这里，如果不设置非阻塞，那么就会报错，非法阻塞模式
                        clientChannel.configureBlocking(false);
                        System.out.println(clientChannel);
                        // connected local=/127.0.0.1:9999 remote=/127.0.0.1:53517
                        // local : 本地（服务端）           remote ：客户端


                        // 12.优化第11步，
                        // 需要在可读时，且有内容时，才进行读取，所以我们需要在获取连接之后重新注册读动作的监听
                        /*
                         * 注意，这里，我们是对获取到的客户端及逆行监听，而不是服务端，
                         * 服务端获取了客户端管道，并通过客户端管道与客户端通信，这里我们已经强调了很多遍，是获取客户端管道，与客户端通信
                         * 所以，我们想要对客户端管道进行读监听，那么必须将获取到的客户端管道注册到多路复用器上
                         * 之后，我们就可以通过多路复用器获取客户端管道了。
                         * */
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (next.isReadable()) {
                        // 此时，表明selector读取到了读的状态，
                        // 但是，此时我们不能使用accept来接收客户端管道，因为客户端管道已经被接受了，再次接受的前提是客户端与服务端已经断开连接
                        // 所以我们需要使用另外的方法获取客户端管道，在我们接收客户端管道时，为了读取管道中的内容，
                        // 我们对其加了可读的监听，此时，我们将其注册到了多路复用器上，所以，我们可以通过多路复用器来获取服务端管道
                        // 我们已经知道这是客户端管道，所以在这里我们直接将其强转成客户端管道
                        SocketChannel clientChannel = (SocketChannel) next.channel();

                        // 10.通过客户端管道接收数据,需要先创建一个缓冲区，将读取到的数据放入缓冲区
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        clientChannel.read(allocate);

                        // 11.获取缓存区中的内容
                        byte[] array = allocate.array();
                        String msg = new String(array);
                        System.out.println("获取到的数据为：" + msg);

                        // 13.此时与接收到服务端时做相同的处理，如果我们想给客户端发消息则需要对写进行监听
                        clientChannel.register(selector, SelectionKey.OP_WRITE);
                    } else if (next.isWritable()) {
                        // 14.此时，表明选择器读到了写的状态,
                        // 首先，我们还是先获取是哪个管道被接收了（即获取客户端管道）
                        SocketChannel clientChannel = (SocketChannel) next.channel();

                        // 其次，向缓冲区写入数据，
                        /*
                         * 注意，客户端与服务端的数据通信虽然跟以前在本质上没区别，都是使用数据流传输数据
                         * 但是，Nio自带缓冲区，所以我们需要先将数据写入缓冲区在发送，这样效率会高一点
                         * 再者，使用缓冲，本身就能提升性能
                         * */
                        ByteBuffer wrap = ByteBuffer.wrap("客户端，你好".getBytes());
                        clientChannel.write(wrap);

                        // 15.及时注册监听
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }

                    // 16.
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test4() {
        try {

            // 1.通过ServerSocketChannel的open()方法创建一个管道
            /*
             * 注意这个ServerSocketChannel在服务端只有在没有接收到连接请求时才会被用到，
             * 一旦客户端连接到了服务端，此时服务端就会获取客户端的管道，
             * 并通过获得的客户端的管道与客户端进行通信
             * 到这里，
             * 客户端有一个SocketChannel的对象，
             * 服务端有一个ServerSocketChannel的对象和一个SocketChannel的对象
             * 并且只能通过Socket Channel与客户端通信
             * 在这个管道中，可以使用Byte Buffer读写数据，
             * 每次读写数据后都要对客户端管道加上对应的状态监听
             *
             */
            ServerSocketChannel serverChannel = ServerSocketChannel.open();

            // 2.默认管道位阻塞的，在Nio模式下，需要我们手动打开，使其非阻塞运行
            System.out.println("默认的blocking状态：" + serverChannel.isBlocking());
            serverChannel.configureBlocking(false);

            // 3.绑定对应的ip和port
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 9999));

            // 4.创建选择器（多路复用器）
            Selector selector = Selector.open();

            // 5.将管道注册到选择器
            /*
             * 参数1：选择器
             * 参数2：多路复用器监听服务端管道接受客户端的状态（此时仅仅是监听，没有状态 ）
             *       如果管道发生了对应的状态，此时管道为该状态，
             *       状态有四个常量，管道发生以下状态时，对应以下状态时，
             *           OP_READ = 1 << 0 = 1
             *           OP_WRITE = 1 << 2 = 4
             *           OP_CONNECT = 1 << 3 = 8
             *           OP_ACCEPT = 1 << 4 = 16
             * */
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 我们想要和客户端进行多次交互，就必须多次获取管道的状态
            while (true) {
                // 6.根据多路复用器，选择管道的状态
                /*
                 * 没有状态，则会阻塞
                 * 有状态，继续运行
                 * */
                selector.select();

                // 7.获取所有被监听到的状态
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 获取状态的迭代器，方便后续操作
                Iterator<SelectionKey> keys = selectionKeys.iterator();

                System.out.println("------客户端连接成功------");

                while (keys.hasNext()) {
                    // 8.服务端接收到了客户端的状态被监听到，此时我们需要接收客户端
                    SelectionKey next = keys.next();
                    if (next.isAcceptable()) {
                        // 9.服务端接收到了客户端，则获取客户端管道，为获取客户端数据做准备，
                        /*
                         * 注意，这里获取的是客户端的管道
                         * 管道是有两个口的，两个口需要分别处理,
                         * 类似蓄水池的进水口和出水口，需要由进水方和出水方分别管理
                         * 在服务端设置非阻塞，相当于有多个进水口（出水口）
                         * 在客户端设置非阻塞，相当于有多个出水口（进水口）
                         */
                        SocketChannel clientChannel = serverChannel.accept();
                        // 在这里，如果不设置非阻塞，那么就会报错，非法阻塞模式
                        clientChannel.configureBlocking(false);
                        System.out.println(clientChannel);
                        System.out.println("接收客户端管道，一次就好。");
                        // connected local=/127.0.0.1:9999 remote=/127.0.0.1:53517
                        // local : 本地（服务端）           remote ：客户端


                        // 12.优化第11步，
                        // 需要在可读时，且有内容时，才进行读取，所以我们需要在获取连接之后重新注册读动作的监听
                        /*
                         * 注意，这里，我们是对获取到的客户端及逆行监听，而不是服务端，
                         * 服务端获取了客户端管道，并通过客户端管道与客户端通信，这里我们已经强调了很多遍，是获取客户端管道，与客户端通信
                         * 所以，我们想要对客户端管道进行读监听，那么必须将获取到的客户端管道注册到多路复用器上
                         * 之后，我们就可以通过多路复用器获取客户端管道了。
                         * */
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (next.isReadable()) {
                        // 此时，表明selector读取到了读的状态，
                        // 但是，此时我们不能使用accept来接收客户端管道，因为客户端管道已经被接受了，再次接受的前提是客户端与服务端已经断开连接
                        // 所以我们需要使用另外的方法获取客户端管道，在我们接收客户端管道时，为了读取管道中的内容，
                        // 我们对其加了可读的监听，此时，我们将其注册到了多路复用器上，所以，我们可以通过多路复用器来获取服务端管道
                        // 我们已经知道这是客户端管道，所以在这里我们直接将其强转成客户端管道
                        SocketChannel clientChannel = (SocketChannel) next.channel();

                        // 10.通过客户端管道接收数据,需要先创建一个缓冲区，将读取到的数据放入缓冲区
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        clientChannel.read(allocate);

                        // 11.获取缓存区中的内容
                        byte[] array = allocate.array();
                        String msg = new String(array);
                        System.out.println("获取到的数据为：" + msg);

                        // 13.此时与接收到服务端时做相同的处理，如果我们想给客户端发消息则需要对写进行监听
                        clientChannel.register(selector, SelectionKey.OP_WRITE);
                    } else if (next.isWritable()) {
                        // 14.此时，表明选择器读到了写的状态,
                        // 首先，我们还是先获取是哪个管道被接收了（即获取客户端管道）
                        SocketChannel clientChannel = (SocketChannel) next.channel();

                        // 其次，向缓冲区写入数据，
                        /*
                         * 注意，客户端与服务端的数据通信虽然跟以前在本质上没区别，都是使用数据流传输数据
                         * 但是，Nio自带缓冲区，所以我们需要先将数据写入缓冲区在发送，这样效率会高一点
                         * 再者，使用缓冲，本身就能提升性能
                         * */
                        ByteBuffer wrap = ByteBuffer.wrap("客户端，你好".getBytes());
                        clientChannel.write(wrap);

                        // 15.及时注册监听
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }

                    // 16.test4（）空指针是因为，没有移除状态，
                    // 所以能够多次进入isAcceptable,但是服务端不能多次获取（accept）客户端管道
                    // 去除被监听到的重复状态
                    keys.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
