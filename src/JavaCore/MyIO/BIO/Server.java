package JavaCore.MyIO.BIO;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author masuo
 * @data 2021/12/15 13:19
 * @Description BIO服务端，BIO，同步且阻塞
 */

public class Server {

    @Test
    public void server() {
        System.out.println("服务端启动");
        try {
            // 1、 定义一个Server Socket对象进行服务端端口的注册,启动服务，注册端口
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("等待客户端的连接...");

            // 2、 监听客户端的socket连接请求，等待客户端的连接，在接收到请求之前阻塞状态
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接成功。");

            // 3、 从socket管道中得到一个字节输入流对象，接口客户端消息
            // 注意这里，发送方怎么发，接收方就要怎么接
            // 接收消息之后，socket就不再使用，因为之后的socket
            InputStream is = socket.getInputStream();
            //这里对请求进行转换
            //BufferedInputStream bis = new BufferedInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("接收到请求：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //服务端启动，

    //BIO中，服务端会一直等待客户端发送消息，如果客户端没有进行发送消息，服务端将一直处于阻塞的状态
    // 同时，如果服务端是按照行获取消息的，这意味着客户端也必须按照行进行发送数据，否则服务端将一直处于等待状态，即阻塞状态
    @Test
    public void singleServer() {
        System.out.println("服务端启动");
        try {
            // 1、 定义一个Server Socket对象进行服务端端口的注册
            ServerSocket ss = new ServerSocket(12000);
            // 2、 监听客户端的socket连接请求
            Socket socket = ss.accept();
            // 3、 从socket管道中得到一个字节输入流对象，
            // 注意这里，发送方怎么发，接收方就要怎么接
            InputStream is = socket.getInputStream();
            //这里对请求进行转换
            //BufferedInputStream bis = new BufferedInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            if ((msg = br.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void multiServer() {
        System.out.println("服务端启动");
        try {
            // 1、 定义一个Server Socket对象进行服务端端口的注册
            ServerSocket ss = new ServerSocket(12000);

            Socket socket;
            InputStream is;
            while (true) {
                // 2、 监听客户端的socket连接请求
                socket = ss.accept();

                System.out.println("socket:" + socket);
                // 3、 从socket管道中得到一个字节输入流对象，
                // 注意这里，发送方怎么发，接收方就要怎么接
                is = socket.getInputStream();
                //这里对请求进行转换
                //BufferedInputStream bis = new BufferedInputStream(is);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String msg;
                // 循环接受消息
                while ((msg = br.readLine()) != null) {
                    System.out.println("服务端接收到的消息为：" + msg);
                }
                is.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
