package CodeTest;

import java.util.Scanner;

/**
 * @author: masuo
 * @data: 2021/8/9 10:05
 * @Description: 找大于0的最小正整数.
 *  用时：90分钟，前提是排序用的现成的写排序要用40+，
 */

public class FindMin {

    public static void main(String[] args) {
        String s1 = "0 1 2 3 5 6 9 7 8 -1 -11 -12 -13";
        String s2 = "1 2 0";
        String s3 = "3 4 -1 1";

        String sin = inputString();

        System.out.println("sin:"+sin);
        //justice(sin);


        String[] unsort = sin.split(" ");

        System.out.println("待排序数组为：");
        for (String s:unsort) {
            System.out.println(s);
        }
        sort(unsort);

    }


    private static String inputString() {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入String：");
        String s = scan.nextLine();
        return s;
    }

    private static String inputString(String mess) {
        Scanner scan = new Scanner(System.in);
        System.out.println(mess);
        String s = scan.nextLine();
        return s;
    }

    private static void justice(String sin) {
        System.out.println("sin:"+sin);
    }

    public static void sort(String[] sunsort) {
        int length = sunsort.length;
        int[] unsort = new int[length];
        //转换为int
        System.out.println("将String数组转为int数组");
        for (int i = 0;i<sunsort.length;i++) {
            if(Integer.parseInt(sunsort[i])>0){
                unsort[i] = Integer.parseInt(sunsort[i]);
            }
            System.out.println(unsort[i]);
        }
        System.out.println("将String数组转为int数组结束");

        //提前GC，保证不会占用太多空间
        System.gc();

        //排序，插入排序
        System.out.println("int数组排序");
        for(int i=1;i<length;i++) {
            int j=i;
            while(j>0) {
                if (unsort[j]<unsort[j-1]) {
                    int temp = unsort[j-1];
                    unsort[j-1]=unsort[j];
                    unsort[j]=temp;
                }
                j--;
            }
        }
        System.out.println("int数组排序结束");
        //排序结束，最后一位是
        for (int i:unsort) {
            System.out.println(i);
        }
        //找到
        System.out.println("找最小");
        for (int i =0;i<=length-1;i++) {
            if(i==length-1){
                System.out.println(unsort[i]+1);
                break;
            }else if(!(unsort[i]==0 || unsort[i]+1==unsort[i+1])){
                System.out.println(unsort[i]+1);
                break;
            }
        }

    }
}
