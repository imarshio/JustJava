package mid;

/**
 * @arithmetics ：
 * @author ： masuo
 * @time ：2021年3月3日 上午12:01:57 类说明 ：计算二维矩阵的子矩阵的元素总和
 */
public class NumMatrix {
	int[][] sum;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = { { 3, 0, 1, 4, 2 }, 
						   { 5, 6, 3, 2, 1 }, 
						   { 1, 2, 0, 1, 5 }, 
						   { 4, 1, 0, 1, 7 },
						   { 1, 0, 3, 0, 5 } };
		NumMatrix numMatrix = new NumMatrix(matrix);
		System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
		;

	}

	public NumMatrix(int[][] matrix) {
		// 初始化时求出二维前缀和,相当于拉长的一维前缀和
		int n = matrix.length;
		if (n > 0) {
			int m = matrix[0].length;
			sum = new int[n + 1][m + 1];
			sum[1][1] = matrix[0][0];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
				}
			}
		}

	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
	}

}
