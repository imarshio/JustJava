package leetcode.tooffer.solution;

import org.junit.Test;

import java.util.Stack;

/**
 * @author masuo
 * @data 2021/11/24 10:06
 * @Description 栈与队列
 */

public class StackAndQueue {

    public static void main(String[] args) {

    }

    @Test
    public void stacks(){
        Stack<Integer> si = new Stack<>();
        si.push(0);
        si.push(2);
        si.push(4);
        si.push(5);
        si.push(6);
        si.push(1);

        si.pop();
        si.pop();
        si.pop();

        si.peek();

    }
}
