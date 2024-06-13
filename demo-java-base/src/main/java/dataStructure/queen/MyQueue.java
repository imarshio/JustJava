package dataStructure.queen;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author masuo
 * @data 19/4/2022 下午1:27
 * @Description 队列测试
 */

public class MyQueue {

    @Test
    public void baseQueueUsing() {
        // 队列的基础使用
        Queue<Integer> queue = new ArrayDeque<>();
        // 向队列中添加
        queue.add(1);
        queue.add(2);
        queue.add(3);
        // 向队列中安全添加元素
        queue.offer(4);

        // 从队列中取出并删除队列中第一个元素
        Integer i1 = queue.poll();
        // 从队列中取出但不删除队列中第一个元素
        Integer i2 = queue.peek();

    }

    public void typeOfQueue() {
        Queue<Integer> queue1 = new ArrayDeque<>();
    }
}
