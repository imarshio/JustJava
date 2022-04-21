package newcoder.top101;

import java.util.Stack;

/**
 * @author masuo
 * @data 19/4/2022 下午4:52
 * @Description 链表相加
 */

public class Solution_040AddList {

    public ListNode addInList(ListNode head1, ListNode head2) {
        // 栈
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1.val);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.push(head2.val);
            head2 = head2.next;
        }

        // 进位
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            // 求和
            int sum = carry;
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            stack3.add(sum % 10);
            carry = sum / 10;
        }
        if(carry > 0){
            stack3.add(carry);
        }
        ListNode newHead = new ListNode(0);
        ListNode tmp = newHead;
        while (!stack3.isEmpty()) {
            tmp.next = new ListNode(stack3.pop());
            tmp = tmp.next;
        }
        return newHead.next;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int x) {
            val = x;
        }
    }

}
