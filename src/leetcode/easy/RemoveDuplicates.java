package leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * @arithmetics ：
 * @author ： masuo
 * @time ：2021年3月9日 上午10:42:56 
 * @类说明 :删除相邻且相同的字符
 */
public class RemoveDuplicates {

	public static void main(String[] args) {
		//  Auto-generated method stub
		RemoveDuplicates removeDuplicates = new RemoveDuplicates();
		String s = getLongStr(20);
		System.out.println(s);
		removeDuplicates.removeDuplicates1("abbaca");
		removeDuplicates.removeDuplicates2("abbaca");
		removeDuplicates.removeDuplicates3("azxxzy");
	}

	private void removeDuplicates3(String s) {
		// 使用双数组
		int len = s.length();
		//该数组存储S
		char[]origin = s.toCharArray();
		//另一数组存储结果
		char[]result = new char[len];
		
		//onum:originnum,rnum:resutnum
		
		int onum=0,rnum = -1;
		for(char c:origin) {
			if(onum<=rnum&&result[rnum]==c) {
				rnum--;
			}else {
				rnum++;
				result[rnum]=c;
			}
		}
		StringBuffer stringBuffer = new StringBuffer();
		while(onum<=rnum) {
			stringBuffer.append(result[onum]);
			onum++;
		}
		System.out.println(stringBuffer.toString());
	}

	private void removeDuplicates2(String S) {
		// 优化第一种方法   ×
		//后来想了一下，这种方法并不是比第一种方法好，虽然步骤看上去是少了，但是如果将循环按照一步步的来走的看的话，实际步数会比第一种方法多很多，
		//因为每循环一次就会增加一步，所以增加的步数为n，而第一种方法的步数是缩水后的栈的步数。
		int len = S.length();
		String string = "";
		Deque<String> stackDeque = new ArrayDeque<String>(len);
		for(int i =0;i<len;i++) {
			//判断
			if(stackDeque.peek()!=null&&stackDeque.peek().equals(String.valueOf(S.charAt(i)))) {
				stackDeque.pop();
				string = string.substring(0, stackDeque.size());
				continue;
			}
			stackDeque.push(String.valueOf(S.charAt(i)));
			string+=S.charAt(i);
		}
		System.out.println(string);
	}

	private static String getLongStr(int lenth) {
		//获取随机长度为i的字符串
		String string = "";
		Random random =new Random();
		String str = "abc";
		for(int i =0;i<lenth;i++) {
			string+=str.charAt(random.nextInt(3));
		}
		return string;
	}

	public String removeDuplicates1(String S) {
		int len = S.length();
		String string = "";
		Deque<String> stackDeque = new ArrayDeque<String>(len);
		for(int i =0;i<len;i++) {
			//判断
			if(stackDeque.peek()!=null&&stackDeque.peek().equals(String.valueOf(S.charAt(i)))) {
				stackDeque.pop();
				continue;
			}
			stackDeque.push(String.valueOf(S.charAt(i)));
		}
		
		while (!stackDeque.isEmpty()) {
			string+=stackDeque.getLast();
			stackDeque.removeLast();
		}
		return string;
	}

}
