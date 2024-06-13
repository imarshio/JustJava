package JavaBase.file;

import org.junit.Test;

import java.io.*;

/**
 * @author masuo
 * @data 2021/12/9 17:41
 * @Description 写入文件 【流】的思想，写入即【输入流】
 * 基础是InputStream
 */

public class WriteFile {

    // InputStream
    @Test
    public void test0() {

        try {
            String fileName = "../JavaCode/src/files/temp0.txt";
            File file = new File(fileName);
            if (!file.exists()) {
                // noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }
            InputStream input = new FileInputStream(file);
            while (true) {
                int n = input.read();
                if (n == -1) {
                    break;
                }
                System.out.print(n);
                System.out.println("  " + (char) (n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // File、FileWriter,基础文件写入流，不带缓冲，较慢
    @Test
    public void test1() {
        String fileName1 = "D:/code/MasuoCode/JavaCode/src/JavaBase/file/temp1.txt";
        String fileName2 = "D:/code/MasuoCode/JavaCode/src/JavaBase/file/temp2.txt";
        File file1 = new File(fileName1);
        File file2 = new File(fileName2);

        try {
            if (!file1.exists()) {
                boolean flag = file1.createNewFile();
            }
            if (!file2.exists()) {
                boolean flag = file2.createNewFile();
            }
            // 覆盖写入
            FileWriter fw1 = new FileWriter(fileName1);
            // 拼接写入，写入文件末尾处，
            FileWriter fw2 = new FileWriter(fileName2, true);

            // \ 这个斜杠是转义符，在字符串中，如果你想写入一个 \ ,那么你需要在字符串中这样写 \\
            fw1.write("this is the first line\n");
            fw1.write("this is the sec line\n");

            fw2.write("this \nis the first line\n");
            fw2.write("this is the sec line\n");
            fw2.write("写入\\  \n");

            fw1.flush();
            fw1.close();
            fw2.flush();
            fw2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // BufferWriter ，进阶文件写入，自带缓冲区，写入较快
    // 缓冲区，叫缓存，为内存的一部分
    @Test
    public void test2() {
        String fileName = "../JavaCode/src/files/temp0.txt";
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write("this is the fir line \n");
            bw.newLine();
            bw.write("this is the sec line\n");
            bw.close();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
