package leetcode.tooffer.solution;

/**
 * @author masuo
 * @data 2021/11/24 14:39
 * @Description 包含min函数的栈,定义栈的数据结构，
 *              请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 *              调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */

public class MinStack {

    //大小
    int size;

    Node top;
    /** initialize your data structure here. */
    public MinStack() {
        size = 0;
    }

    public void push(int value) {
        int min;
        if(size==0){
            min = value;
        }else {
            min = Math.min(top.min,value);
        }
        top = new Node(value,top);
        top.setMin(min);
        ++size;
    }

    public void pop() {
        --size;
        top = top.son;
    }

    public int top() {
        return top.value;
    }

    public int min() {
        return top.min;
    }

    static class Node{
        int value;
        int min;
        Node son;

        public Node(int value,Node son) {
            this.value = value;
            this.son = son;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }
}
