package dataStructure.tree;

import com.ms.math.Random;
import org.junit.Test;

import java.io.*;
import java.util.Stack;

/**
 * @author masuo
 * @data 2021/9/29 8:16
 * @Description 测试类
 */

public class TreeTest {

    @Test
    public void test1() {
        DynamicBinaryTree<Integer> dbt = new DynamicBinaryTree<>();
        dbt.add(10);
        dbt.add(5);
        dbt.add(15);
        dbt.add(12);
        dbt.add(17);
        dbt.add(11);
        dbt.add(13);
        dbt.add(16);
        dbt.add(18);
        System.out.println(dbt.get(5).item);

        try{
            dbt.del(12);
        }catch (Exception e){
            e.printStackTrace();
        }

        dbt.del(11);
        System.out.println(dbt.getDepth());
    }

    @Test
    public void test2() {
        BalancedBinaryTree<Integer> bbt = new BalancedBinaryTree<>();
        bbt.add(10);
        bbt.add(5);
        bbt.add(15);
        bbt.add(12);
        bbt.add(17);
        bbt.add(11);
        bbt.add(13);
        bbt.add(16);
        bbt.add(18);
        System.out.println(bbt.get(5).item);
        bbt.del(5);
        bbt.del(15);
        bbt.del(12);
        bbt.del(13);
        System.out.println(bbt.getHeight(bbt.root));
    }

    @Test
    public void test3() {
        DynamicBinaryTree<Integer> dbt = new DynamicBinaryTree<>();
        dbt.add(10);
        dbt.add(5);
        dbt.add(15);
        dbt.add(4);
        dbt.add(8);
        dbt.add(11);
        dbt.add(19);
        dbt.add(6);
        dbt.add(9);
        dbt.add(16);
        dbt.add(12);
        dbt.add(20);
        dbt.add(17);
        dbt.del(10);
        System.out.println(dbt.getDepth());
    }

    @Test
    public void test4() {
        //测试DynamicBinaryTree 的增加功能
        DynamicBinaryTree<Integer> dbt = new DynamicBinaryTree<>();
        long start = System.currentTimeMillis();
        while (dbt.size < 20000) {
            int i = Random.randomInt(100000);
            System.out.println(i);
            dbt.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(start - end);
    }

    @Test
    public void test5() {
        //排除增加报错
        DynamicBinaryTree<Integer> dbt = new DynamicBinaryTree<>();
        dbt.add(4137);
        dbt.add(6005);
        dbt.add(8368);
        dbt.add(8756);
        dbt.add(1720);
        dbt.add(120);
        dbt.add(927);
        dbt.add(7670);
        dbt.add(185);
        dbt.add(918);
        dbt.add(1105);
        dbt.add(1039);
        dbt.add(795);
        dbt.add(7252);
        dbt.add(7072);
        dbt.add(6127);
        dbt.add(8414);
        dbt.add(1007);
        dbt.add(910);
        dbt.add(9321);
        dbt.add(8998);
        dbt.add(9261);
        dbt.add(9782);
        dbt.add(8700);
        dbt.add(8717);
        dbt.add(7254);
        dbt.add(3928);
        dbt.add(1891);
        dbt.add(6701);
        dbt.add(1681);
        dbt.add(1062);
        dbt.add(3333);
        dbt.add(7948);
        dbt.add(5637);
        dbt.add(1385);
        dbt.add(3291);
        dbt.add(300);
        dbt.add(3632);
        dbt.add(9983);
        dbt.add(9486);
        dbt.add(9531);
        dbt.add(7565);
        dbt.add(3673);
        dbt.add(1045);
        dbt.add(9597);
        dbt.add(2262);
        dbt.add(7510);
        dbt.add(3612);
        dbt.add(881);
        dbt.add(2243);
        dbt.add(2107);
        dbt.add(3956);
        dbt.add(7851);
        dbt.add(9775);
        dbt.add(6683);
        dbt.add(2149);
        System.out.println(dbt.getDepth());
    }

    @Test
    public void test6() {
        int i = 0;
        while (i < 1000) {
            test4();
            ++i;
        }
    }

    @Test
    public void test7() {
        //测试DynamicBinaryTree 的删除功能
        DynamicBinaryTree<Integer> dbt = new DynamicBinaryTree<>();
        Stack<Integer> stack = new Stack<>();

        //创建文件
        String fileName = "D:/code/MasuoCode/JavaCode/src/dataStructure/date/temp0.txt";
        File file = new File(fileName);
        //System.out.println(System.getProperty("user.dir"));
        try {
            if (!file.exists()) {
                boolean flag = file.createNewFile();
            }
            //写入文件
            FileWriter writer = new FileWriter(file); //以当前内容替换文件中的内容
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            while (dbt.size < 10) {
                int i = Random.randomInt(100);
                stack.add(i);
                bufferedWriter.write(i + "\n");
                dbt.add(i);
            }

            bufferedWriter.close();
            writer.close();

            while (!stack.empty()) {
                System.out.println(stack.peek());
                dbt.del(stack.pop());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test8() {
        //测试DynamicBinaryTree 的删除功能
        DynamicBinaryTree<Integer> dbt = new DynamicBinaryTree<>();

        //创建文件
        String file1 = "D:/code/MasuoCode/JavaCode/src/dataStructure/date/temp.txt";
        String file2 = "D:/code/MasuoCode/JavaCode/src/dataStructure/date/temp1.txt";
        File file = new File(file1);

        try {
            if (!file.exists()) {
                boolean flag = file.createNewFile();
            }

            //读取文件
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            while (br.ready()) {
                dbt.add(Integer.valueOf(br.readLine()));
                dbt.preList();
            }
            System.out.println("最大节点:" + dbt.getMaxLeaf(dbt.root).item);

            br.close();
            reader.close();

            dbt.preList();
            dbt.midList();
            dbt.tailList();

            reader = new FileReader(file2);
            br = new BufferedReader(reader);
            while (br.ready()) {
                Integer integer = Integer.valueOf(br.readLine());
                System.out.println(integer);
                dbt.preList();
                dbt.del(integer);
            }
            br.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test9() {
        RBTree<Integer> rbTree = new RBTree<>();
        //rbTree.root;
    }
}