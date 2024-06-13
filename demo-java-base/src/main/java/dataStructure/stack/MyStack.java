package dataStructure.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * @author masuo
 * @data 19/4/2022 下午1:32
 * @Description 栈测试
 */

public class MyStack {

    @Test
    public void test1() {
        Stack<Integer> stack = new Stack<>();

        // 向栈中添加元素
        stack.push(1);
        // Vector.
        stack.add(2);
        Integer i = 1;
        //
        stack.pop();

        stack.peek();
    }
}
