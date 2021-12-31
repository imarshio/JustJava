package leetcode.easy;

import java.util.Stack;

/**
* @arithmetics ：
* @author ： masuo
* @time ：2021年3月5日 上午10:10:07
* 类说明:仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作
*/
public class MyQueue {
	Stack<Integer> instack,outstack;
	public static void main(String[] args) {
		//  Auto-generated method stub
		MyQueue myQueue = new MyQueue();
		myQueue.push(1); // queue is: [1]
		myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//		myQueue.push(3);
//		myQueue.push(4);
//		System.out.println(myQueue.pop());
//		myQueue.push(5);
		System.out.println(myQueue.peek()); // return 1
		System.out.println(myQueue.pop()); // return 1, queue is [2]
//		System.out.println(myQueue.empty()); // return false
	}

	/** Initialize your data structure here. */
    public MyQueue() {
    	instack = new Stack<Integer>();
    	outstack = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	instack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	if(outstack.isEmpty()) {
    		in2out();
    	}
		return outstack.pop();
		
    }
    
    /** Get the front element. */
    public int peek() {
    	
    	if(outstack.isEmpty()) {
    		in2out();
    	}
		return outstack.peek(); 
    }
    
    private void in2out() {
		//  Auto-generated method stub
    	while(!instack.isEmpty()) {
    		outstack.push(instack.pop());
    	}
	}

	/** Returns whether the queue is empty. */
    public boolean empty() {
    	
    	return instack.isEmpty()&&outstack.isEmpty();
    }
}
