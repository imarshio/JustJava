package MyIO.IO;

import org.junit.Test;

import javax.annotation.processing.FilerException;
import java.io.File;
import java.util.Date;

/**
 * @author masuo
 * @date: 2021/12/25/ 下午8:34
 * @description 文件类，关于文件的操作
 */
public class FileOP {

    @Test
    public void fileOP() {

        // String fileName = "D:\\code\\MasuoCode\\JavaCode\\src\\files\\temp5.txt";
        String fileName = "src/files/temp5.txt";
        // String fileName = "../JavaCode/src/files/temp5.txt";
        System.out.println(System.getProperty("user.dir"));
        File file = new File(fileName);

        try {
            // 创建文件
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new FilerException("文件创建失败！");
                } else {
                    System.out.println("文件创建成功！");
                }
            } else {
                System.out.println("文件已存在！");
            }
            // 删除文件
            // 直接删除
            //{
            //    if(file.delete()){
            //        System.out.println("文件删除成功！");
            //    }
            //}
            // jvm退出时删除
            {
                file.deleteOnExit();
                Thread.sleep(5000);
                System.out.println("JVM 删除文件成功！");
            }
            // 获取文件信息
            {
                System.out.println("获取文件的绝对路径：" + file.getAbsolutePath());
                System.out.println("获取文件的路径：" + file.getPath());
                System.out.println("获取文件的名称：" + file.getName());
                System.out.println("获取文件的父目录：" + file.getParentFile());
                System.out.println("获取文件的总大小：" + file.getTotalSpace());
                System.out.println("获取文件的空闲大小：" + file.getFreeSpace());
                System.out.println("获取文件已使用大小：" + file.getUsableSpace());
                System.out.println("获取文件最新修改时间：" + new Date(file.lastModified()));
                System.out.println("获取文件最新修改时间：" + new Date(file.lastModified()).toLocaleString());
            }
            // 判断
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件目录操作
     */
    @Test
    public void fileDirOP() {

        String fileName = "src/files/aaa/bbb/cc";
        System.out.println(System.getProperty("user.dir"));
        File file = new File(fileName);
        try {
            // 创建文件夹
            if (!file.exists()) {
                // mkdir 创建一个文件夹
                if (file.mkdir()) {
                    System.out.println("单级文件夹创建成功！");
                } else if (file.mkdirs()) {
                    System.out.println("多级文件夹创建成功！");
                } else {
                    System.out.println("文件夹创建失败！");
                }
            } else {
                System.out.println("文件已存在！");
            }
            {
                // 删除文件夹，删除单级目录或文件，
                // 注意 ，删除的如果是文件夹，则必须是空文件夹
                System.out.println("删除文件夹结果：" + file.delete());

                // 使用JVM删除
                file.deleteOnExit();
                System.out.println("JVM删除文件夹成功");
            }

            // 获取文件夹信息
            // 同上

            // 判断
            // 遍历文件夹
            String file2Name = "src/files";
            File file1 = new File(file2Name);

            // String[] files = file1.list();
            // if (files == null) {
            //    System.out.println("空的！");
            //} else {
            //    for (String s : files) {
            //        System.out.println(s);
            //    }
            //}

            System.out.println("filter");
            // 文件过滤器
            File[] files1 = file1.listFiles(pathname -> pathname.getName().endsWith(".txt"));
            File[] files2 = file1.listFiles(pathname -> pathname.getName().endsWith(".txt"));
            assert files1 != null;
            if (files1.length == 0) {
                System.out.println("空的！");
            } else {
                for (File file2 : files1) {
                    System.out.println(file2.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
