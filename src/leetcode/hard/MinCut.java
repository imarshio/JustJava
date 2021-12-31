package hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @arithmetics ：dfs(深度优先搜索)
 * @author ： masuo
 * @time ：2021年3月8日 下午1:47:51 类说明
 */
public class MinCut {

	public static void main(String[] args) {
		//  Auto-generated method stub
		MinCut minCut = new MinCut();
		System.out.println(minCut.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp"));
	}

	public int minCut(String s) {
		int len = s.length();
		int depth = 0;
		char[]c = s.toCharArray();
		List<Integer>res = new ArrayList<Integer>(len);
		Deque<String> path = new ArrayDeque<String>();
		if(isPartition(c, 0, len-1)) {return 0;}
		dfs(c,len,depth,res,path);
		return minRes(res,len);
	}

	private int minRes(List<Integer> res, int len) {
		int min = len;
		for(int i:res) {
			if(i<min) {min = i;}
		}
		return min-1;
	}

	private void dfs(char[] c, int len, int depth, List<Integer> res, Deque<String> path) {
		//判断递归结束条件
		if(depth==len) {
			List<String> list = new ArrayList<String>(path);
			res.add(list.size());
			return;
		}
		
		for(int i=depth;i<len;i++) {
			if(!isPartition(c,depth,i)) {continue;}
			path.addLast(new String(c,depth,i+1-depth));
			dfs(c, len, i+1, res, path);
			path.removeLast();
		}
		
	}

	private boolean isPartition(char[] c, int left, int right) {
		while(left<right) {
			if(c[left]!=c[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}



}
