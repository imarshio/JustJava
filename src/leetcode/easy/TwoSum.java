package leetcode.easy;

import java.util.HashMap;

/**
 * @arithmetics ：
 * @author ： masuo
 * @time ：2021年3月13日 下午2:30:29
 * @类说明 :
 */
public class TwoSum {

	public static void main(String[] args) {
		// Auto-generated method stub
		TwoSum wSum = new TwoSum();
		int []nums = {2,7,11,15};
		for(int i:wSum.twoSum2(nums, 9)) {
			System.out.println(i);
		}
		
	}

	public int[] twoSum(int[] nums, int target) {
		
		//使用hashmap
		//时间复杂度为O（2n）==O（n）
		int len = nums.length;
		//对数据预处理，将数据放入到Hash Map中
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<len;i++) {
			map.put(nums[i], i);
		} 
		
		for(int i=0;i<len;i++) {
			int num = target-nums[i];
			if(map.containsKey(num)) {
				int index = map.get(num);
				if(index!=i) {
					return new int[] {i,index};
				}
			}
		} 
		return new int[]{1,0};
	}
	
public int[] twoSum2(int[] nums, int target) {
		
		//使用hashmap,优化算法
		//时间复杂度为O（n）
		int len = nums.length;
		//不对数据预处理，将数据放入到Hash Map中
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<len;i++) {
			int num = target-nums[i];
			if(map.containsKey(num)) {
				int index = map.get(num);
				return new int[] {index,i};
			}
			map.put(nums[i], i);
		} 
		return new int[0];
	}

}
