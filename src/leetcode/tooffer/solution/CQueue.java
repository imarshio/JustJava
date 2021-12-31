package leetcode.tooffer.solution;

import java.util.Stack;

/**
 * @author masuo
 * @data 2021/11/24 10:07
 * @Description 用两个栈实现队列
 *              队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 *              分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 *              (若队列中没有元素，deleteHead操作返回 -1 )
 */

public class CQueue {

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */

    Stack<Integer> head;
    Stack<Integer> tail;
    public CQueue() {
        head = new Stack<>();
        tail = new Stack<>();
    }

    public void appendTail(int value) {
        //方法1
        if(tail.empty() && head.empty()){
            tail.push(value);
        }else if(tail.empty() && !head.empty()){
            //先将head中的值放到tail中
            while (!head.empty()){
                tail.push(head.pop());
            }
            tail.push(value);
        }else if (!tail.empty()){
            tail.push(value);
        }
        //方法2：方法1 的简化
        while (!head.empty()){
            tail.push(head.pop());
        }
        tail.push(value);
    }

    public int deleteHead() {
        if(head.empty() && tail.empty()){
            return -1;
        }else if(!tail.empty()){
            //将tail中的值放到head中
            while (!tail.empty()){
                head.push(tail.pop());
            }
        }
        return head.pop();
    }

}
