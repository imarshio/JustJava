package mid;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @arithmetics ：二叉树搜索
 * @author ： masuo
 * @time ：2021年3月12日 上午10:28:44
 * @类说明 :给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序遍历。编写一个在不重构树的条件下的可行算法。
 */
public class IsValidSerialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IsValidSerialization ivs = new IsValidSerialization();
		String preorder = "9,3,4,#,#,1,#,#,#,2,#,6,#,#";
		System.out.println(ivs.isValidSerialization(preorder));
	}

	public boolean isValidSerialization(String preorder) {
		String []s= preorder.split(",");
		//利用栈来存储当前节点剩余的槽位，每个非空节点初始有两个槽位
		Deque<Integer> deque =new ArrayDeque<Integer>();
		deque.push(1);
		for(String str:s) {
			if(deque.isEmpty()) {
				return false;
			}
			if(str.equals("#")) {
				deque = tempDeque(deque);
			}else {
				deque = tempDeque(deque);
				deque.push(2);
			}
		}
		return deque.isEmpty();
	}

	private Deque<Integer> tempDeque(Deque<Integer> deque) {
		// TODO Auto-generated method stub
		int temp = deque.pop();
		temp--;
		if(temp!=0) {
			deque.push(temp);
		}
		return deque;
	}

	public boolean isValidCount(String preorder) {
		//与方法一类似，但是优化栈，将栈中元素加起来，我们可以只维护一个计数器就能判断
		//计数器初始为1，代表根节点，每次遇到非空节点+2
		int depth = 1;
		String [] s=preorder.split(",");
		for(String str:s) {
			if(depth<0) {
				return false;
			}
			if(str.equals("#")) {
				depth--;
			}else {
				//这里要先执行depth-1，在执行depth+2，所以depth+1即可
				depth+=1;
			}
		}
		return depth==0;
	}
}
