package CodeTest;

import java.util.Scanner;

/**
 * @author masuo
 * @date: 2022/05/03/ 下午2:48
 * @description 捷丰数据 02 构建蛇形矩阵
 */
public class JFTest02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] strs = s.split("/*");
        // 因为输入的是自然数，且小于整形最大值
        int rowNum = Integer.parseInt(strs[0]);
        int colNum = Integer.parseInt(strs[2]);
        int[][] snackMatrix = buildSnackMatrix(rowNum, colNum);
        int[][] spiralMatrix = buildSpiralMatrix(rowNum, colNum);

        for (int[] matrix : snackMatrix) {
            for (int i : matrix) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int[] matrix : spiralMatrix) {
            for (int i : matrix) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 构建螺旋矩阵
     * @param rowNum 行数
     * @param colNum 列数
     * @return spiralMatrix 二维数组
     */
    private static int[][] buildSpiralMatrix(int rowNum, int colNum) {
        int[][] spiralMatrix = new int[rowNum][colNum];
        // 从1开始累加
        int index = 1;

        // 左上到右下
        int top = 0;
        int bottom = rowNum - 1;
        int left = 0;
        int right = colNum - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                spiralMatrix[top][i] = index++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                spiralMatrix[i][right] = index++;
            }
            right--;
            if(top <= bottom && left <= right) {
                for (int i = right; i >= left; i--) {
                    spiralMatrix[bottom][i] = index++;
                }
                bottom--;
                for (int i = bottom; i >= top; i--) {
                    spiralMatrix[i][left] = index++;
                }
                left++;
            }
        }
        for (int[] ints : spiralMatrix) {
            StringBuilder sb = new StringBuilder(colNum * 2);
            for (int anInt : ints) {
                sb.append(anInt).append(",");
            }
            System.out.println(sb.substring(0, sb.length() - 1));
        }
        return spiralMatrix;
    }

    /**
     * 构建蛇形矩阵
     * @param rowNum 行数
     * @param colNum 列数
     * @return snackMatrix 二维矩阵
     */
    private static int[][] buildSnackMatrix(int rowNum, int colNum) {
        int[][] snackMatrix = new int[rowNum][colNum];

        int index = 1;
        for (int i = 0; i < colNum; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < rowNum; j++) {
                    snackMatrix[j][i] = index++;
                }
            } else {
                for (int j = rowNum - 1; j >= 0; j--) {
                    snackMatrix[j][i] = index++;
                }
            }
        }

        for (int[] ints : snackMatrix) {
            StringBuilder sb = new StringBuilder(colNum * 2);
            for (int anInt : ints) {
                sb.append(anInt).append(",");
            }
            System.out.println(sb.substring(0, sb.length() - 1));
        }
        return snackMatrix;
    }


}
