package leetcode.easy;

import java.util.HashMap;

/**
* @arithmetics ：
* @author ： masuo
* @time ：2021年3月13日 下午12:14:03
* @类说明 :
*/
public class SumOfUnique {

	public static void main(String[] args) {
		// Auto-generated method stub
		SumOfUnique souOfUnique= new SumOfUnique();
		int []nums = {1,2,3,2};
		souOfUnique.sumOfUnique(nums);
		
	}
	
	public int sumOfUnique(int[] nums) {
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		int sum = 0;
		//将键值key-value：设置为：数字，数字的次数
		for(int i=0;i<nums.length;i++) {
			if(hashMap.containsKey(nums[i])) {
				if(hashMap.get(nums[i])==1) {
					sum-=nums[i];
				}
				hashMap.put(nums[i], 0);
				continue;
			}
			hashMap.put(nums[i],1);
			sum+=nums[i];
		}
		System.out.println(sum);
		return sum;
    }

}
