package MyIO.IO;

import java.io.File;
import java.io.IOException;

/**
 * @author masuo
 * @data 2021/12/9 17:35
 * @Description 新建文件
 */

public class NewFile {

    public static void main(String[] args) throws IOException {
        File file = new File("D:/code/MasuoCode/JavaCode/src/JavaBase/file/temp.txt");

        // 能创建文件，但是不能创建目录
        if (!file.exists()) {
            System.out.println("不存咋");
            boolean flag = file.createNewFile();
        }


    }
}
