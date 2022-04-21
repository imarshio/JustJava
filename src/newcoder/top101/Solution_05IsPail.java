package newcoder.top101;

import java.util.Stack;

/**
 * @author masuo
 * @data 1/4/2022 下午3:21
 * @Description 是否回文
 */

public class Solution_05IsPail {

    /**
     * 快慢指针 + 栈
     * @return boolean
     */
    public boolean isPail (ListNode head) {
        // write code here
        if(head == null || head.next == null){
            return true;
        }
        ListNode s = head;
        ListNode f = head;
        Stack<Integer> stack = new Stack<>();
        while (f != null && f.next != null){
            stack.add(s.val);
            s = s.next;
            f = f.next.next;
        }
        // f到头，慢指针走到一半 ，如1234，慢指针走到3.如12345，慢指针走到3
        if(f != null){
            // 因为奇数时，需要调过中间位
            s = s.next;
        }
        while (!stack.isEmpty() && stack.pop() == s.val){
            s = s.next;
        }

        return stack.isEmpty();
    }

    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int x) {
            val = x;
        }
    }

}
