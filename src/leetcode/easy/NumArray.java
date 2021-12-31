package leetcode.easy;
/**
* @arithmetic ：
* @author ： masuo
* @time ：2021年3月1日 上午11:23:02
* 类说明：给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点
*/

/**
 * Your NumArray object will be instantiated and called as such: NumArray obj =
 * new NumArray(nums); int param_1 = obj.sumRange(i,j);
 */
class NumArray {
	public int[] sum;
	static int[] nums = { -2, 0, 3, -5, 2, -1 };
	public static void main(String[] args) {
		//  Auto-generated method stub
		
		NumArray numArray = new NumArray(nums);
		int s = numArray.sumRangeOne(0, 3);
		System.out.println(s);
	}

	public NumArray(int[] nums) {
		int n = nums.length;
		// 需要判断i是否为0
		sum = new int[n];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				sum[i] = nums[i];
			} else
				sum[i] = sum[i - 1] + nums[i];
		}
//		 不需要判断i是否为0
//		sum = new int[n+1];
//		for (int i = 0; i < n; i++) {
//			sum[i+1] = sum[i] + nums[i];
//		}
	}

	public int sumRangeOne(int i, int j) {
		if (i == 0) {
			return sum[j];
		}
		return sum[j] - sum[i - 1];
	}

	public int sumRangeTwo(int i, int j) {
		return sum[j + 1] - sum[i];
	}

	public int sumRangeFor(int i, int j) {
		//使用for循环求和
		int s = 0;
		for(int n=i;n<=j;n++) {
			s+=nums[n];
		}
		return s;
	}

}
