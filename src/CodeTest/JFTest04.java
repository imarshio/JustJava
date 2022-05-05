package CodeTest;

import java.util.Arrays;

/**
 * @author masuo
 * @date: 2022/05/03/ 下午2:48
 * @description 捷丰数据 02
 */
public class JFTest04 {

    // 打印矩阵
    public static void main(String[] args) {

        //int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        int[] order = printMatrixInCircle(matrix);

        Arrays.stream(order).forEach(i -> System.out.print(i + " "));
    }

    private static int[] printMatrixInCircle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        // 一层一层的循环输出

        int length = matrix[0].length;
        int height = matrix.length;

        // 返回值
        int[] order = new int[length * height];

        // 确定左上角（top , left） 和 右下角 （bottom , right）
        int top = 0;
        int bottom = height - 1;
        int left = 0;
        int right = length - 1;

        // 记录返回值的位置
        int index = 0;
        // 首先最外层
        while (top <= bottom && left <= right){
            for (int i = left; i <= right; i++) {
                order[index++] = matrix[top][i];
            }
            for (int i = top + 1; i <= bottom; i++) {
                order[index++] = matrix[i][right];
            }
            top++;
            right--;
            if(left <= right && top <= bottom) {
                for (int i = right; i >= left; i--) {
                    order[index++] = matrix[bottom][i];
                }
                for (int i = bottom - 1; i >= top; i--) {
                    order[index++] = matrix[i][left];
                }
            }
            // 输出外层之后，收缩
            bottom--;
            left++;

        }

        return order;
    }
}
