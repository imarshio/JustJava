package leetcode.tooffer.solution;

import java.util.Stack;


/**
 * @author masuo
 * @data 2021/11/24 15:50
 * @Description
 */

public class Reverse {
    //从尾到头打印链表
    public int[] reversePrint(ListNode head) {
        if(head==null){
            return new int[0];
        }
        Stack<Integer> si = new Stack<>();
        ListNode pre = new ListNode(head.val);
        while (head.next != null){
            //方法1：放到栈中
            si.push(head.val);

            //方法2：放到链表中

        }
        si.push(head.val);
        int [] tail = new int[si.size()];
        int length = 0;
        while (!si.empty()){
            tail[length] = si.pop();
            ++length;
        }

        return tail;
    }

    //反转链表
    public ListNode reverseListI(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode reverse = new ListNode(head.val);

        ListNode tempI = reverseListI(head.next);
        ListNode tempII = tempI;
        while (tempII.next != null){
            tempII = tempII.next;
        }
        tempII.next = reverse;
        return tempI;
    }

    public ListNode reverseListII(ListNode head) {

        if(head == null || head.next == null)
            return head;
        ListNode node = reverseListII(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}



