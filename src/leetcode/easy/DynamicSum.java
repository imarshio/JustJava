package easy;

public class DynamicSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		DynamicSum ds = new DynamicSum();
		int []nums = {1,2,3,4};
		nums = ds.runningSum(nums);
		for(int num:nums){
			System.out.println(num);
		}
	}
	
	public int[] runningSum(int[] nums) {
        
        for(int i=1;i<nums.length;i++){
            nums[i] = nums[i]+nums[i-1];
        }
		return nums;
                
    }
}
