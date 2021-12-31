package mid;

/**
 * @arithmetics ：LIS(Longest Increasing Subsequence)最长上升子序列
 * @author ： masuo
 * @time ：2021年3月10日 下午11:46:04
 * @类说明 :给你一个整数数组 nums ，找到其中最长严格递增子序列的长度
 */
public class LengthOfLIS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LengthOfLIS lengthOfLIS = new LengthOfLIS();
		int []nums = {0,1,0,3,2,3};
		lengthOfLIS.lengthOfLIS(nums);
	}

	public int lengthOfLIS(int[] nums) {//0 1 0 3 2 3
										//1 2 1 3 3 1
		int len = nums.length;
		int []res = new int[len];
		res[0]=1;
		int max = 0;
		for(int i = 1; i<len ;i++) {
			res[i]=1;
			for(int j=0;j<i;j++) {
				if(nums[i]>nums[j]) {
					if(res[i]<=res[j]) {
						res[i] = res[j]+1;
					}
				}
			}
			System.out.println(res[i]);
			if(max<res[i]) {
				max=res[i];
			}
		}
		System.out.println(max);
		return max;
	}
}
