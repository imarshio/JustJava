package CodeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: masuo
 * @data: 2021/7/29 15:12
 * @Description:
 */

public class Test3 {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        //TypeIn<String> ti = new Tet<>();
        Scanner scan = new Scanner(System.in);
        scan.nextInt();
        Math.abs(10);
        //String s = scan.nextLine();

        String s = TypeIn.inputString();
        String s1 = TypeIn.inputString("a1aa1a");

        Typeins.inputs();

        int a = 10;
        int b = 10;

        //a,b = swap(a,b);
    }

    private static int swap(int a, int b) {
        return a;
    }


}


class Typeins{

    private static Scanner scan;
    public static Object inputs(){
        System.out.print("请输入你要输入的：");
        scan = new Scanner(System.in);
        return scan.nextLine();
    }

}