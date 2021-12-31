package JavaCore.MyIO.IO;

import org.junit.Test;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author masuo
 * @date: 2021/12/25/ 下午7:20
 * @description 转换流 - InputStreamReader  -  OutputStreamWriter
 */
public class ExchangeStream {

    /**
     * 由于本地文件大多是字符文件，但我们程序使用时又经常使用字节，
     * 所以我们需要将字符文件读取时直接读取为字节流，所以就会用到转换流
     * InputStreamReader 从本地读取字符流转换成字节流
     */
    @Test
    public void ByteToCharInReading() {
        //这里temp是utf-8 的编码格式
        String read = "../JavaCode/src/files/temp.txt";
        File reading = new File(read);

        try {
            if (!reading.exists()) {
                if (!reading.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                }
            }
            InputStream is;
            InputStreamReader isr;

            // 默认编码读取
            {
                is = new FileInputStream(reading);
                isr = new InputStreamReader(is);
                //UTF8的编码方式对中文编码是用三个字节表达，
                // 所以使用FileInputStream读取到的是字符的三分之一个字节
                System.out.println("获取文件编码方式：" + isr.getEncoding());
                System.out.println("FileInputStream获取第一个字节的字节：" + is.read());
                //InputStreamReader会读取整个字符的字节
                System.out.println("InputStreamReader获取第一个字符的字节：" + isr.read());
                //循环读取
                int data;
                while ((data = isr.read()) != -1) {
                    System.out.print((char)data);
                }

                isr.close();
                is.close();
            }
            //指定读取文件的编码格式
            {
                is = new FileInputStream(reading);
                isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                //UTF8的编码方式对中文编码是用三个字节表达，
                // 所以使用FileInputStream读取到的是字符的三分之一个字节
                System.out.println("获取文件编码方式：" + isr.getEncoding());
                System.out.println("FileInputStream获取第一个字节的字节：" + is.read());
                //InputStreamReader会读取整个字符的字节
                System.out.println("InputStreamReader获取第一个字符的字节：" + isr.read());
                //循环读取
                int data;
                while ((data = isr.read()) != -1) {
                    System.out.print((char)data);
                }

                isr.close();
                is.close();
            }
            //指定读取文件的编码格式
            {
                is = new FileInputStream(reading);
                isr = new InputStreamReader(is, "GBK");
                //UTF8的编码方式对中文编码是用三个字节表达，
                // 所以使用FileInputStream读取到的是字符的三分之一个字节
                System.out.println("获取文件编码方式：" + isr.getEncoding());
                System.out.println("FileInputStream获取第一个字节的字节：" + is.read());
                //InputStreamReader会读取整个字符的字节
                System.out.println("InputStreamReader获取第一个字符的字节：" + isr.read());
                //循环读取
                int data;
                while ((data = isr.read()) != -1) {
                    System.out.print((char)data);
                }

                isr.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同上，写入文件时，需要按字符写入
     * OutputStreamWriter 从本地读取字符流转换成字节流
     */
    @Test
    public void CharToByteInWriting() {
        String read = "../JavaCode/src/files/temp.txt";
        File writing = new File(read);

        try {
            if (!writing.exists()) {
                if (!writing.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                }
            }
            OutputStream os;
            OutputStreamWriter osw;

            //按默认字符编码写入
            {
                os = new FileOutputStream(writing, true);
                osw = new OutputStreamWriter(os);

                osw.write(10);
                osw.append('!');
                osw.write("好好学习！");

                osw.flush();
                osw.close();
                os.close();
            }
            //指定字符编码格式
            {
                os = new FileOutputStream(writing, true);
                osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);

                osw.write(10);
                osw.append('!');
                osw.write("好好学习！");

                osw.flush();
                osw.close();
                os.close();
            }

            //指定字符编码格式
            {
                //os = new FileOutputStream(writing, true);
                //osw = new OutputStreamWriter(os, "GBK");
                //
                //osw.write(10);
                //osw.append('!');
                //osw.write("好好学习！");
                //
                //osw.flush();
                //osw.close();
                //os.close();
            }

            System.out.println("写入完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
