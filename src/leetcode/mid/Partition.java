package mid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Partition {

	public static void main(String[] args) {
		Partition partition = new Partition();
		partition.partition("aab");
	}

	public List<List<String>> partition(String s) {
		int len = s.length();
		// 1.声明结果数组，动态数组
		List<List<String>> res = new ArrayList<List<String>>();
		// 2.声明used[i]
		boolean[] used = new boolean[len];
		// 3.声明深度depth
		int depth = 0;
		// 4.声明stack栈，用于存储每次生成的字符数组
		Deque<String> stack = new ArrayDeque<String>();
		char[] c = s.toCharArray();
		// 5.调用dfs函数
		dfs(c, len, res, depth, stack, used);
		dfs(c, depth, len, stack, res);
		System.out.println(res);
		return res;
	}

	private void dfs(char[] c, int len, List<List<String>> res, int depth, Deque<String> stack, boolean[] used) {
		// 递归结束条件
		if (depth == len) {
			// 这里一定要new一个新的动态数组，不然直接存进来的stack会是个空数组
			res.add(new ArrayList<String>(stack));
			// 递归结束，深度达到最深，将栈中字符数组存入res中
			return;
		}

		for (int i = depth; i < len; i++) {
			// 每次获取一个
			// 判断要加入的数组是否为回文数组
			if (!isPartition(c, depth, i)) {
				continue;
			}
			stack.addLast(new String(c, depth, i + 1 - depth));
			dfs(c, len, res, i + 1, stack, used);
			stack.removeLast();

		}
	}

	private boolean isPartition(char[] c, int left, int right) {

		while (left < right) {
			if (c[left] != c[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	private void dfs(char[] charArray, int index, int len, Deque<String> path, List<List<String>> res) {
		if (index == len) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = index; i < len; i++) {
			// 因为截取字符串是消耗性能的，因此，采用传子串下标的方式判断一个子串是否是回文子串
			path.addLast(new String(charArray, index, i + 1 - index));
			dfs(charArray, i + 1, len, path, res);
			path.removeLast();
		}
	}
}