package CodeTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

/**
* @arithmetics ：
* @author ： masuo
* @date 2021年3月7日 下午11:51:13
* 类说明
*/
public class StackTest {

	public static void main(String[] args) {
		//
		System.out.println("***********stack**************");
		Deque<String> stack= new ArrayDeque<>();
		char[] c = {'a','b','c','a','b','c'};
		//new String(charArray,index,num):从charArray中选取从index开始的共num位字符
		System.out.println(new String(c,0,4));
		stack.push("aaa");
		//addFirst(string):向stack（栈）中最开始的位置添加字符串
		stack.addFirst("b");
		//addLast(string)：向stack（栈）中最后的位置添加字符串
		stack.addLast(new String(c,0,1));
		System.out.println(stack);
		stack.removeLast();
		System.out.println(stack);
		
		System.out.println("************ArrayList***************");
		List<Integer> hashSet = new ArrayList<Integer>();
		hashSet.add(0,1);
		hashSet.add(1,2);
		hashSet.add(3,2);
		for(int i:hashSet) {
			System.out.println(i);
		}
		System.out.println(hashSet.contains(0));
		
		
		System.out.println("************HashMap************");
		HashMap<Integer, String> hashMap =new HashMap<Integer, String>();
		hashMap.put(0, "ms");
		hashMap.put(1, "xcl");
		hashMap.put(2, "lv");
		hashMap.put(3, "zmh");
		hashMap.put(4, "ljx");
		hashMap.put(0, "xxx");
		System.out.println(hashMap.get(0));
		
		
	}

}
