package mid;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @arithmetics ：
 * @author ： masuo
 * @time ：2021年3月7日 下午9:41:35 类说明:全排列，给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 */
public class Permute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Permute permute = new Permute();
		int []nums = {1,2,3};
		permute.permute(nums);
	}

	public List<List<Integer>> permute(int[] nums) {
		// 深度优先搜索 dfs

		// 1.先得到数组的长度
		int len = nums.length;

		// 2.生命返回数组,动态数组
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();

		// 3.建立递归数组，完成深度优先遍历
		boolean[] used = new boolean[len];
		Stack<Integer> path = new Stack<>();
		dfs(nums, len, resultList, 0, used, path);
		System.out.println(resultList);
		return resultList;
	}

	/**
	 * 三个变量：
	 *  depth，深度 
	 *  used[i]:表示下标为i的数据是否被用过
	 *  path:结果集，栈
	 */
	private void dfs(int[] nums, int len, List<List<Integer>> resultList, int depth, boolean[] used,
			Stack<Integer> path) {
		// 1.递归终止的条件为当depth等于len的时候，则深度达到最深，返回状态回溯
		if (depth == len) {
			resultList.add(new ArrayList<Integer>(path));
		}

		// 2.当depth不为len时，对数组循环
		for (int i = 0; i < len; i++) {
			// 判断该下标上的数是否被用过
			if (used[i]) {
				// 用过则判断下一个
				continue;
			}
			// 没被用过
			path.push(nums[i]);
			used[i] = true;
			dfs(nums, len, resultList, depth+1, used, path);
			
			//递归一次完成之后进行回溯，将前面操作逆转
			used[i] = false;
			path.pop();
		}

	}

}
