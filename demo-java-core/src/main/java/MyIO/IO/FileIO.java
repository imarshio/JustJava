package MyIO.IO;

import org.junit.Test;

import javax.annotation.processing.FilerException;
import java.io.*;

/**
 * @author masuo
 * @date: 2021/12/20/ 下午9:07
 * @description IO流--FileIO
 * 按照流的流向可划分为 【输入流】 和 【输出流】
 * 按照操作单元可划分为 【字节流】 和 【字符流】
 * IO即输入输出（Input/Output）。
 * 冯诺依曼结构中指出，计算机由：运算器、控制器、存储器、输入设备、输出设备组成。
 * 这里的输入输出描述了计算机系统与外界之间的通讯过程。
 * <p>
 * 在应用程序层面：
 * 为了保证操作系统的稳定性和安全性，进程的地址空间被划分为用户空间（User space）和内核空间（Kernel space）
 * 我们平时的应用程序都是运行在用户空间的，只有内核空间才能进行系统态级别的资源有关的操作，如文件管理，进程通信，内存管理等。
 * 并且，用户空间不能直接访问内核空间。当我们想要执行IO操作时，由于本身没有操作权限，我们需要向系统发起请求。
 * <p>
 * 最常见的IO就是磁盘IO（读写文件）和网络IO（网络请求和响应）。
 */
public class FileIO {

    // Java IO流涉及很多个类，这些类都是由以下四个抽象类派生出来的.
    // InputStream/Reader：输入流基类，字节/字符
    // OutputStream/Writer：输出流基类，字节/字符

    /**
     * 读取文件
     */
    @Test
    public void FileI() {
        String fileName = "../JavaCode/src/files/temp.txt";
        File file = new File(fileName);
        // 输入流类的使用

        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                }
            }

            // 1、InputStream -- FileInputStream
            {
                InputStream is;
                System.out.println("InputStream....");
                // 如果我们想要读取完整的文件，就需要循环读取
                // 方法一：循环读取字节
                {
                    is = new FileInputStream(file);
                    System.out.println("每次读取一个字节：" + is.read());// 会输出读取到的第一个字节
                    int date;
                    while ((date = is.read()) != -1) {
                        // 读取到的数据不是-1时，则证明可以继续读取
                        System.out.print(date);// 读取一个字节，返回字节的int值
                        System.out.print(' ');
                        System.out.print((char) (date));// 将字节转换成字符
                        System.out.print(' ');
                        System.out.print((byte) (date));// 将字节转换成字节
                        System.out.println();
                    }
                    is.close();
                    System.out.println("读取结束！");
                }
                // 方法二：利用缓冲一次性读取大量数据读取，将读取到的数据缓冲到数组中
                {
                    is = new FileInputStream(file);
                    // 这种缓冲读取，也是Buffer的实现原理
                    byte[] bytes = new byte[49];
                    // 这里返回的int值是读取到的最后一位的下标，也是读取字节的数量
                    int inputStreamIndex = is.read(bytes);
                    System.out.println("read(byte[])返回的是下标，下标为:" + inputStreamIndex);
                    for (byte aByte : bytes) {
                        System.out.print(aByte + " ");
                    }
                    System.out.println("读取结束！");
                    // 此时再读取将读到 -1
                    System.out.println("在读取则为：" + is.read());
                    is.close();
                }
            }

            // 2、Reader -- FileReader
            {
                Reader reader;
                System.out.println("Reader.....");
                // 方法一：循环读取
                {
                    reader = new FileReader(file);
                    // 每次读取一个字符
                    System.out.println("每次读取一个字符:" + reader.read());
                    int data;// 可以不声明，这里data作为读取记录
                    while ((data = reader.read()) != -1) {
                        System.out.print(data + "，字符为：");
                        System.out.print((char) data + " ");
                        System.out.println();
                    }
                    System.out.println("读取结束！");
                    System.out.println("继续读取则为：" + reader.read());
                    reader.close();
                }
                // 方法二：缓冲读取
                {
                    reader = new FileReader(file);
                    char[] chars = new char[25];
                    int readIndex = reader.read(chars);// 读取字符的数量
                    System.out.println("read(char[])返回的是下标，下标为:" + readIndex);
                    for (char aChar : chars) {
                        // System.out.print(aChar + ' '); // 这样读取会，，，，嗯，显示出错
                        System.out.print(aChar);
                    }
                    System.out.println("读取结束！");
                    System.out.println("在读取则为：" + reader.read());
                    reader.close();
                }
            }

            // 3、InputStream -- BufferedInputStream
            {
                InputStream is;
                System.out.println("BufferedInputStream....");
                // 方法一：循环读取
                {
                    is = new FileInputStream(file);
                    // 这里如果不指定缓存容量的话，默认的缓存容量为8KB
                    is = new BufferedInputStream(is);
                    // 这里如果你读取的是一个汉字等，一个汉字占两个字节，
                    // 所以使用字节流和字符流读取到的数据不一样
                    System.out.println("每次读取一个字节：" + is.read());
                    int data;
                    while ((data = is.read()) != -1) {
                        System.out.print(data);
                        System.out.print('\t');
                    }
                    System.out.println("读取结束！");
                    is.close();
                }
                // 法二：缓冲读取
                {
                    is = new FileInputStream(file);
                    is = new BufferedInputStream(is);
                    byte[] bufferBytes = new byte[49];
                    // 记录读取了多少数据
                    int count = is.read(bufferBytes);
                    System.out.println("read(byte[])返回的是读取的数据数量，为:" + count);
                    for (byte aByte : bufferBytes) {
                        System.out.print(aByte + " ");
                    }
                    System.out.println("读取完毕！");
                    is.close();
                }
                // 方法三：字节读取时没有按行读取方法，但是可以设置缓存大小
                {
                    is = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is, 49);
                    byte[] bufferBytes = new byte[49];
                    // 记录读取了多少数据
                    int count = bis.read(bufferBytes);
                    System.out.println("read(byte[])返回的是读取的数据数量，为:" + count);
                    for (byte aByte : bufferBytes) {
                        System.out.print(aByte + " ");
                    }
                    System.out.println("读取完毕！");
                    is.close();
                }
            }

            // 4、Reader -- BufferedReader
            {
                Reader reader;

                // 方法一：循环读取
                {
                    reader = new FileReader(file);
                    reader = new BufferedReader(reader);
                    System.out.println("每次读取一个字符:" + reader.read());
                    int data;
                    while ((data = reader.read()) != -1) {
                        System.out.print(data + " ");
                    }
                    System.out.println("读取完毕！");
                    reader.close();
                }
                // 方法二：缓冲读取
                {
                    reader = new FileReader(file);
                    reader = new BufferedReader(reader);
                    char[] chars = new char[50];
                    int count = reader.read(chars);
                    System.out.println("read(char[])返回的是读取的数据数量，为:" + count);
                    for (char aChar : chars) {
                        System.out.print(aChar);
                    }
                    System.out.println("读取完毕！");
                    reader.close();
                }
                // 方法三：按行读取,需要注意，如果想使用BufferedReader的自己的方法，需要使用
                {
                    reader = new FileReader(file);
                    BufferedReader bfReader = new BufferedReader(reader);
                    // Stream<String> lines = bfReader.lines();
                    // System.out.println("bfReader.lines()读取的：" + lines.count());

                    String line = bfReader.readLine();
                    System.out.println("readLine()读取一行数据，为:" + line);
                    // 想要循环读取，需要配合使用bfReader.ready()，当他为true时，说明，读取流还未关闭
                    while (bfReader.ready()) {
                        System.out.println("readLine()返回的是读取的一行数据，为:" + bfReader.readLine());
                    }
                    System.out.println("读取完毕！");
                    bfReader.close();
                    reader.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件
     */
    @Test
    public void FileO() {
        String fileName = "../JavaCode/src/files/outputStream.txt";
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                }
            }

            // 1、 OutputStream  --  FileOutputStream
            {
                OutputStream os = new FileOutputStream(file);
                // 单个字节写入
                os.write(48);// 0 的 byte 值等于 48
                os.write(49);// 这里写入的是字节，会将值转换为字节，再写入
                os.write(50);
                os.write('\n');
                os.write('0');
                os.write('1');
                os.write('2');
                os.write('\n');
                // 利用缓存写入
                byte[] streamBytes = new byte[]{'h', 'e', 'l', 'l', 'o'};
                os.write(streamBytes);
                os.write('\n');
                // 写入bytes，从off位开始的len长度
                os.write(streamBytes, 1, 3);

                // 记得关闭
                os.flush();
                os.close();
            }
            // 2、 Writer  -- FileWriter,同上,第二个参数代表不覆盖写入
            {
                Writer writer = new FileWriter(file, true);
                // 单个字节写入
                writer.write(48);
                writer.write(49);
                writer.write(50);
                writer.write('\n');

                // 利用缓存写入
                char[] writerBytes = new char[]{'h', 'e', 'l', 'l', 'o'};
                writer.write(writerBytes);

                // 前面如果没有写完，将强制写入
                writer.flush();
                // 如果不关闭，可能写入失败,所以必须关闭流
                writer.close();
            }
            // 3、OutputStream -- BufferedOutputStream
            {
                OutputStream os = new FileOutputStream(file, true);
                os = new BufferedOutputStream(os);
                // 单个字节写入
                os.write(10);
                os.write(48);
                os.write(49);
                os.write(50);
                os.write('\n');
                os.write(20880);
                os.write('\n');

                // 缓存写入
                byte[] bytes = new byte[]{(byte) 20880, (byte) 20881, (byte) 20882};
                os.write(bytes);
                os.flush();
                os.close();
            }
            // 4、Reader  -- BufferedReader
            {
                Writer writer = new FileWriter(file, true);
                writer = new BufferedWriter(writer);
                // 单个字符写入
                writer.write('马');
                writer.write('硕');
                writer.write('喜');
                writer.write('欢');
                writer.write('写');
                writer.write('b');
                writer.write('u');
                writer.write('g');
                writer.write('！');
                writer.write('？');
                writer.write('\n');
                writer.write('N');
                writer.write('o');
                writer.write('\t');
                writer.write('s');
                writer.write('h');
                writer.write('i');
                writer.write('t');

                writer.write("你好啊");
                writer.write("hello world！");
                char[] chars = new char[]{'马', '硕', 'o', 'h'};
                writer.write(chars);
                writer.flush();
                writer.close();
            }
            System.out.println("写入完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     */
    @Test
    public void FileCopy() {
        // 这里利用读写，以及缓存读写来体验缓存的重要性

        String read = "../JavaCode/src/files/temp0.txt";
        String write = "../JavaCode/src/files/temp1.txt";
        File reading = new File(read);// 待读取文件
        File writing = new File(write);// 待写入文件
        // Reader,Writer,InputStream,OutputStream
        try {
            if (!reading.exists()) {
                if (!reading.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                }
            }
            if (!writing.exists()) {
                if (!writing.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                }
            }
            // 字符流 Reader Writer
            {
                Reader reader;
                Writer writer;
                // 在这里有一点需要注意，
                // 当文件被一对一复制时（即读取一个字节复制一个字节），文件是多大，复制出来的就是多大，
                // 但是当文件被一对多复制时（即使用手动缓冲复制），文件可能会被扩容，且文件的所占容量是缓冲区的整数倍
                {
                    reader = new FileReader(reading);
                    writer = new FileWriter(writing);
                    System.out.print("读取一个字符，写入一个字符用时：");
                    int data;
                    long start = System.currentTimeMillis();
                    while ((data = reader.read()) != -1) {
                        writer.write(data);
                    }
                    // 记得关闭
                    reader.close();
                    // 将缓冲区的内容写入文件，可直接close
                    writer.flush();
                    writer.close();
                    long end = System.currentTimeMillis();
                    System.out.println(end - start + " ms");
                }
                {
                    reader = new FileReader(reading);
                    writer = new FileWriter(writing);
                    System.out.print("缓冲读取用时:");
                    char[] buffer = new char[1024];// 1KB，速度提升了十倍以上，这里并不是缓冲越大越好，符合需要才是最好的
                    long start = System.currentTimeMillis();
                    while (reader.read(buffer) != -1) {
                        writer.write(buffer);
                    }

                    // 记得关闭
                    reader.close();
                    writer.flush();
                    writer.close();
                    long end = System.currentTimeMillis();
                    System.out.println(end - start + " ms");
                }
                {
                    // 通过上面两个例子，我们已经实现了复制，
                    // 但是，细心的人会发现一个问题，那就是上面的代码可能会使文件大小发生改变
                    // 因为我们最后一次读取的时候，不论读取了多少字符，
                    // 我们都是完整的写入1KB大小，（这里你可以思考一下）
                    // 这样造成了复制前后文件大小不一样
                    // 如果我们想让复制前后文件的大小不发生改变
                    // 我们需要使用read的另外一个方法
                    reader = new FileReader(reading);
                    writer = new FileWriter(writing);
                    System.out.print("缓冲读取2用时:");
                    char[] buffer = new char[2 * 1024];// 1KB
                    int count;
                    long start = System.currentTimeMillis();
                    while ((count = reader.read(buffer)) != -1) {
                        // 这表示将buffer从0开始到count之间的字符写入文件
                        writer.write(buffer, 0, count);
                    }

                    // 记得关闭
                    reader.close();
                    writer.flush();
                    writer.close();
                    long end = System.currentTimeMillis();
                    System.out.println(end - start + " ms");
                }
            }
            // 字节流 InputStream OutputStream
            {
                InputStream is;
                OutputStream os;

                {
                    is = new FileInputStream(reading);
                    os = new FileOutputStream(writing);
                    int data;
                    long start = System.currentTimeMillis();
                    while ((data = is.read()) != -1) {
                        os.write(data);
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("单个字节读取用时：" + (end - start) + " ms");
                    // tips：当我们使用字节流读取txt等文档时，会非常非常非常非常的慢，
                    // 这也体现了字节流与字符流的不同之处
                    os.flush();
                    os.close();
                    is.close();
                }
                {
                    is = new FileInputStream(reading);
                    os = new FileOutputStream(writing);
                    int count;
                    byte[] bufferBytes = new byte[1024];
                    long start = System.currentTimeMillis();
                    while ((count = is.read(bufferBytes)) != -1) {
                        os.write(bufferBytes, 0, count);
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("缓存读取用时：" + (end - start) + " ms");
                    os.flush();
                    os.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buffered

        try {

            // 字符流 Reader - BufferedReader ，Writer - FileWriter
            {
                Reader reader;
                Writer writer;
                // 单个字节读取，写入
                {
                    reader = new FileReader(reading);
                    writer = new FileWriter(writing);
                    // 声明缓冲流
                    reader = new BufferedReader(reader);
                    writer = new BufferedWriter(writer);
                    if (reader.ready()) {
                        int data;
                        long start = System.currentTimeMillis();
                        while ((data = reader.read()) != -1) {
                            writer.write(data);
                        }
                        long end = System.currentTimeMillis();
                        System.out.println("BufferedReader用时:" + (end - start) + " ms");
                    }
                    writer.flush();
                    writer.close();
                    reader.close();
                }
                // 缓存写入
                {
                    reader = new FileReader(reading);
                    writer = new FileWriter(writing);
                    // 声明缓冲流
                    reader = new BufferedReader(reader);
                    writer = new BufferedWriter(writer);
                    if (reader.ready()) {
                        int count;
                        char[] bufferedChars = new char[1024];
                        long start = System.currentTimeMillis();
                        while ((count = reader.read(bufferedChars)) != -1) {
                            writer.write(bufferedChars, 0, count);
                        }
                        long end = System.currentTimeMillis();
                        System.out.println("BufferedReaderChars用时:" + (end - start) + " ms");
                    }

                    writer.flush();
                    writer.close();
                    reader.close();
                }
                // 按行读取写入
                {
                    reader = new FileReader(reading);
                    writer = new FileWriter(writing);
                    // 声明缓冲流
                    reader = new BufferedReader(reader);
                    writer = new BufferedWriter(writer);
                    if (reader.ready()) {
                        int count;
                        char[] bufferedChars = new char[1024];
                        long start = System.currentTimeMillis();
                        while ((count = reader.read(bufferedChars)) != -1) {
                            writer.write(bufferedChars, 0, count);
                        }
                        long end = System.currentTimeMillis();
                        System.out.println("BufferedReaderChars用时:" + (end - start) + " ms");
                    }

                    writer.flush();
                    writer.close();
                    reader.close();
                }
            }
            // 字节流 InputStream - BufferedInputStream , OutputStream - BufferedOutputStream
            {
                InputStream is;
                OutputStream os;
                // 单个字节读取。写入
                {
                    is = new FileInputStream(reading);
                    os = new FileOutputStream(writing);
                    is = new BufferedInputStream(is);
                    os = new BufferedOutputStream(os);

                    int data;
                    long start = System.currentTimeMillis();
                    while ((data = is.read()) != -1) {
                        os.write(data);
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("BufferedInputStream用时:" + (end - start) + " ms");
                    // 记得关闭
                    os.flush();
                    os.close();
                    is.close();
                }
                // 缓存读取，写入
                {
                    is = new FileInputStream(reading);
                    os = new FileOutputStream(writing);
                    // 声明缓冲流
                    is = new BufferedInputStream(is);
                    os = new BufferedOutputStream(os);
                    int count;
                    byte[] bufferedBytes = new byte[1024];
                    long start = System.currentTimeMillis();
                    while ((count = is.read(bufferedBytes)) != -1) {
                        os.write(bufferedBytes, 0, count);
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("bufferedBytes用时:" + (end - start) + " ms");

                    os.flush();
                    os.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在这里介绍一下关于mark()和reset()方法
     * 注意，不是所有的Reader子类或实现类都支持mark()方法
     */
    @Test
    public void someElse() {
        // mark()是Reader接口下的一个方法，其作用是标记当前读取的位置在流中的位置，
        // 例
        String read = "../JavaCode/src/files/temp0.txt";
        File reading = new File(read);// 待读取文件

        try {
            if (!reading.exists()) {
                if (!reading.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                }
            }
            Reader reader = new BufferedReader(new FileReader(reading));
            // 读取一个字符
            System.out.println(reader.read());
            System.out.println(reader.read());
            System.out.println(reader.read());
            // 标记位默认为 -1，设置值为 1 的意思是，将标记节点赋值为 当前读取节点，
            // 标记为 1 ，需要配合reset()方法使用
            reader.mark(1);
            // mark 之后，如果不进行 reset 即可正常读取
            System.out.println(reader.read());
            System.out.println(reader.read());
            System.out.println(reader.read());
            // reset()之后，当前读取节点会回到标记节点
            reader.reset();
            // 先说 reset() 的作用是重置读取节点，在没有设置mark节点的时候，
            // 调用这个方法是会抛出“invalid mark value”异常的

            // 此时在进行读取，将从标记位读取数据
            System.out.println(reader.read());
            System.out.println(reader.read());
            System.out.println(reader.read());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
