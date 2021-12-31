package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @arithmetics ：HashSet
 * @author ： masuo
 * @time ：2021年3月13日 上午10:55:35
 * @类说明 :不使用任何内建的哈希表库设计一个哈希集合（HashSet）。 实现 MyHashSet 类： void add(key) 向哈希集合中插入值
 *      key 。 bool contains(key) 返回哈希集合中是否存在这个值 key 。 void remove(key) 将给定值
 *      key从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 */
public class MyHashSet {
	List<Integer> hashSet;

	public static void main(String[] args) {
		//  Auto-generated method stub
		MyHashSet mhs= new MyHashSet();
		mhs.add(1);
		mhs.add(2);
		System.out.println(mhs.contains(1));
		System.out.println(mhs.contains(3));
		mhs.add(2);
		System.out.println(mhs.contains(2));
		
		mhs.remove(2);
		System.out.println(mhs.contains(2));
	}

	/** Initialize your data structure here. */
	public MyHashSet() {
		hashSet = new ArrayList<Integer>();
	}

	public void add(int key) {
		if(!hashSet.contains(key)){
			hashSet.add(key);
		}		
	}

	public void remove(int key) {
		//因为arraylist移除时使用下标移除，所以我们需要找到相应值的下标来移除该值
		int depth = 0;
		for(int i:hashSet) {
			if (i==key) {
				hashSet.remove(depth);
				break;
			}
			depth++;
		}
		
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key) {
		boolean con = false;
		for(int i:hashSet) {
			if(i==key) {
				return true;
			}
		}
		return con;
	}
}
