package CodeTest;

import java.util.Scanner;

/**
 * @author masuo
 * @date: 2022/05/03/ 下午2:48
 * @description 捷丰数据 02
 */
public class JFTest02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] strs = s.split("/*");
        // 因为输入的是自然数，且小于整形最大值
        int rowNum = Integer.parseInt(strs[0]);
        int colNum = Integer.parseInt(strs[2]);
        printSnack(rowNum, colNum);
    }

    private static void printSnack(int rowNum, int colNum) {
        int[][] matrix = new int[rowNum][colNum];

        int index = 1;
        for (int i = 0; i < colNum; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < rowNum; j++) {
                    matrix[j][i] = index++;
                }
            } else {
                for (int j = rowNum - 1; j >= 0; j--) {
                    matrix[j][i] = index++;
                }
            }
        }

        for (int[] ints : matrix) {
            StringBuilder sb = new StringBuilder(colNum * 2);
            for (int anInt : ints) {
                sb.append(anInt).append(",");
            }
            System.out.println(sb.substring(0, sb.length() - 1));
        }
    }
}
