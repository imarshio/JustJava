package JavaCore.MyIO.BIO;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author masuo
 * @data 2021/12/15 13:19
 * @Description 客户端，
 */

public class Client {

    @Test
    public void client() {
        try {
            // 1、 客户端需要发送请求，发送请求之前需要先建立请求
            // 建立客户端socket，为发送请求做准备，此即建立与服务端之间得通道
            Socket socket = new Socket("127.0.0.1", 9999);
            {
                // 这样会自动连接服务端，自动连接
                //socket = new Socket("127.0.0.1", 9999);
            }
            //等同于
            {
                // 连接服务端,手动连接
                //socket = new Socket();
                //socket.connect(new InetSocketAddress("127.0.0.1", 9999));
            }


            // 2、 建立请求之后，需要获取待发送数据,
            // 获取客户端地址和服务端地址
            {
                System.out.println("获取客户端地址：" + socket.getLocalAddress());
                System.out.println("获取服务端地址：" + socket.getRemoteSocketAddress());
            }

            // 因为我们是通过socket发送数据，所以我们需要将待发送数据写入输出流，
            // 所以我们需要从socket中获取一个输出流，
            // 虽然上面建立了与服务端之间的通道，但是没有建立流通道，就相当于没有交通来往方式，只能看，不能沟通
            // 建立流通道之后就可以进行沟通了，此即建立与服务端之间的 流通道
            OutputStream os = socket.getOutputStream();

            // 3、 需要通过socket发送数据，需要将带发送数据包装成服务端想要的数据
            // 建立通道之后，我们就可以通过流通道发送自己想要的发送的数据，
            // 但是，由于建立的通道是字节流，所以我们需要将带发送数据转换成字节流，
            // 就好比，形状不匹配的物体，是不可以组合的

            // 这里我们可以使用文件输出字节流，打印流，
            // BufferedOutputStream
            // ByteArrayOutputStream
            // DataOutputStream
            // FilterOutputStream
            // PrintStream...等
            PrintStream ps = new PrintStream(os);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            //ByteArrayOutputStream baos = new ByteArrayOutputStream(10);
            DataOutputStream dos = new DataOutputStream(os);
            //FilterOutputStream fos = new FilterOutputStream(os);

            ps.print("这是带发送数据的第一行，不带换行即代表这一行信息还未发送结束，");
            ps.println("带换行即代表发送结束！");
            //
            dos.writeUTF("服务端你好！");

            bos.write(new byte[]{'h', 'e', 'l', 'l', 'o', '!', '0'}, 0, 5);
            bos.flush();

            dos.flush();
            bos.flush();
            ps.flush();
            ps.close();
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void singleClient() {
        try {
            // 1、 客户端需要发送请求，发送请求之前需要先建立请求
            Socket socket = new Socket("127.0.0.1", 12000);
            // 2、 建立请求之后，需要获取待发送数据,从socket中获取一个字节输出流
            OutputStream os = socket.getOutputStream();
            // 3、 需要通过socket发送数据，需要将带发送数据包装成服务端想要的数据
            PrintStream ps = new PrintStream(os);
            ps.print("这是带发送数据的第一行，不带换行即代表这一行信息还未发送结束，");
            ps.println("带换行即代表发送结束！");
            //
            ps.flush();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void multiClient() {
        try {
            // 1、 客户端需要发送请求，发送请求之前需要先建立请求
            Socket socket = new Socket("127.0.0.1", 12000);
            // 2、 建立请求之后，需要获取待发送数据,从socket中获取一个字节输出流
            OutputStream os = socket.getOutputStream();
            // 3、 需要通过socket发送数据，需要将带发送数据包装成服务端想要的数据
            PrintStream ps = new PrintStream(os);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("请说：");
                ps.println(sc.nextLine());
                ps.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
