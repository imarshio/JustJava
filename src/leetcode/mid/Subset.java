package mid;

import java.util.ArrayDeque;

import java.util.Deque;

/**
 * @arithmetics ：回溯算法（深度优先搜索DFS）
 * @author ： masuo
 * @time ：2021年3月7日 下午10:56:02 类说明 ：获取字符串的所有子集
 */
public class Subset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subset subset = new Subset();
		subset.subset("123");
	}

	private void subset(String s) {
		// 获取所有子集，使用回溯算法dfs，
		//1.获取字符串长度
		int len = s.length();
		//2.将字符串转化为字符数组
		char[] arrayList = s.toCharArray();
		//3.声明栈，用于存储子集
		Deque<String> stack = new ArrayDeque<String>();
		//4.执行深度优先搜索
		dfs(arrayList, len, 0, stack);
	}

	private void dfs(char[] arrayList, int len, int depth, Deque<String> stack) {
		//递归结束条件，当深度与数组长度相同时
		if(depth==len) {
			System.out.println(stack);
			return;
		}
		
		for(int i = depth;i<len;i++) { 
			//得到所有子集
			stack.addLast(new String(arrayList,depth,i+1-depth));
		}
		dfs(arrayList, len, depth+1, stack);
		
	}

}
