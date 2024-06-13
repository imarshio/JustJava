package MyIO.IO;

import org.junit.Test;

import java.io.*;
import java.util.Stack;

/**
 * @author masuo
 * @data 2021/12/9 17:56
 * @Description 读文件 即【输出流】
 * 基础为OutputStream，字节流的基本单位为字节（Byte），用B来标识，
 * 1字节等于8位，即 1Byte = 1B = 8bit = 8b
 */

public class ReadFile {

    /*FileReader*/
    @Test
    public void test1() {
        String fileName = "D:/code/MasuoCode/JavaCode/src/JavaBase/file/temp2.txt";
        File file = new File(fileName);

        try {
            FileReader reader = new FileReader(file);
            int t = 't';
            byte s = '\\';
            // System.out.println(t);
            // System.out.println(Integer.valueOf(' '));

            int read = reader.read();
            // System.out.println(read);
            while (reader.ready()) {
                // reader.read()会读取一个字符，并将其转换成int类型，如果想输出字符型，需要转换一下
                System.out.print((char) reader.read());
            }

            char[] chars = new char[100];
            int reads = reader.read(chars);
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // fr.read()
    public void test2() {
        String fileName = "D:/code/MasuoCode/JavaCode/src/JavaBase/file/temp2.txt";
        File file = new File(fileName);

        try {
            FileReader fr = new FileReader(file);

            while (fr.ready()) {
                // 输出整数类型的
                System.out.print(fr.read());
            }

            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //(char) fr.read()
    public void test3() {
        String fileName = "D:/code/MasuoCode/JavaCode/src/JavaBase/file/temp2.txt";
        File file = new File(fileName);

        try {
            FileReader fr = new FileReader(file);

            while (fr.ready()) {
                // 输出字符类型的
                System.out.print((char) fr.read());
            }

            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // char[] chars = new char[100];
    // fr.read(chars);
    public void test4() {
        String fileName = "D:/code/MasuoCode/JavaCode/src/JavaBase/file/temp2.txt";
        File file = new File(fileName);

        try {
            FileReader fr = new FileReader(file);

            char[] chars = new char[100];// 长度自取，根据文件的字符数量，
            int read = fr.read(chars);// 将文件内容读入chars

            for (char aChar : chars) {
                System.out.print(aChar);
            }

            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*BufferReader*/
    @Test
    public void test5() {

        String read = "D:/code/MasuoCode/JavaCode/src/dataStructure/date/temp1.txt";
        String writ = "D:/code/MasuoCode/JavaCode/src/dataStructure/date/temp.txt";
        File file1 = new File(read);
        File file2 = new File(writ);

        try {
            if (!file1.exists()) {
                boolean newFile = file1.createNewFile();
            }
            if (!file2.exists()) {
                boolean newFile = file2.createNewFile();
            }

            FileReader reader = new FileReader(read);
            BufferedReader br = new BufferedReader(reader);
            Stack<String> stack = new Stack<>();
            while (br.ready()) {
                System.out.println();
                stack.add(br.readLine());
            }
            br.close();
            reader.close();

            FileWriter writer = new FileWriter(writ);
            BufferedWriter bw = new BufferedWriter(writer);
            while (!stack.isEmpty()) {
                bw.write(stack.pop() + "\n");
            }
            bw.close();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
