package leetcode.easy;

/**
 * @arithmetics ：DP
 * @author ： masuo
 * @time ：2021年3月11日 下午3:11:37
 * @类说明 :一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * @解法：有动态规划解法，记忆法，递归等方法。难度较小。
 */
public class MassageList {

	public static void main(String[] args) {
		//  Auto-generated method stub
		MassageList massageList =new MassageList();
		int []nums = {0,0};
		System.out.println(massageList.massage(nums));
	}

	public int massage(int[] nums) {
		//DP解法
		int len = nums.length;
		 if(len==0){
	            return 0;
	        }
		int []max = new int[len];
		max[0]=nums[0];
		
		if(len==1) {
			return nums[0];
		}else if (len==2) {
			max[1]=nums[1];
			return Math.max(max[len-1], max[len-2]);
		}else {
			max[2]=nums[0]+nums[2];
			for(int i=3;i<len;i++) {
				max[i]=Math.max(max[i-2], max[i-3])+nums[i];
			}
			return Math.max(max[len-1], max[len-2]);
		}
	}

}
