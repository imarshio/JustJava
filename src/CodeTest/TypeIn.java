package CodeTest;

import java.util.Scanner;

/**
 * @author: masuo
 * @data: 2021/8/11 15:15
 * @Description: input 代替Scanner，改成类似List<E>
 */

public final class TypeIn {

    //The message that you need to output
    //private static String message;

    //Set the basic tools
    private static final Scanner scan = new Scanner(System.in);

    private TypeIn(){
        //私有化构造函数，不能建立实例对象
    }

    //Input what you want with no message

    public static String inputString() {
        System.out.print("请输入你要输入的：");
        return scan.nextLine();
    }

    public static int input() {
        System.out.print("请输入你要输入的：");
        return scan.nextInt();
    }

    public static String inputString(String message) {
        //TypeIn.message = message;
        System.out.print(message+":");
        return scan.nextLine();
    }
}
