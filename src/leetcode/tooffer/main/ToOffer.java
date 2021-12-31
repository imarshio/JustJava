package leetcode.tooffer.main;

import leetcode.tooffer.solution.CQueue;
import leetcode.tooffer.solution.ListNode;
import leetcode.tooffer.solution.MinStack;
import leetcode.tooffer.solution.Reverse;
import org.junit.Test;

import java.util.ListIterator;

/**
 * @author masuo
 * @data 2021/11/24 10:04
 * @Description 剑指offerI
 */

public class ToOffer {
    public static void main(String[] args) {
        ToOffer to = new ToOffer();
        to.cqueue();
        to.minStack();
        to.reverse();
    }

    @Test
    public void reverse() {
        Reverse reverse = new Reverse();
        ListNode ln1 = new ListNode(5);
        ListNode ln2 = new ListNode(4);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(2);
        ListNode ln5 = new ListNode(1);
        ln4.next = ln5;
        ln3.next = ln4;
        ln2.next = ln3;
        ln1.next = ln2;



        reverse.reverseListII(ln1);
    }

    @Test
    public void minStack() {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        //ms.push(8);
        //ms.push(0);
        ms.min();
        ms.top();
        ms.pop();
        ms.pop();
        ms.top();
    }

    @Test
    public void cqueue() {
        CQueue cq = new CQueue();
        cq.appendTail(10);
        cq.appendTail(11);
        System.out.println(cq.deleteHead());
        cq.appendTail(2);
        cq.appendTail(5);


        cq.deleteHead();
        cq.deleteHead();
    }


}
