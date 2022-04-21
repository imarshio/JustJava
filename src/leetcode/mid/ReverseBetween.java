package leetcode.mid;

import newcoder.top101.Solution_02ReverseBetween;
import org.junit.Test;

/**
 * @author masuo
 * @data 15/4/2022 上午10:26
 * @Description 翻转指定区间的链表
 */

public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            // =
            return head;
        }
        int n = right - left;
        // left < right
        ListNode pre = null;
        ListNode leftNode = head;
        while (--left > 0) {
            pre = leftNode;
            leftNode = pre.next;
        }


        ListNode rightNode = leftNode;
        while (n-- > 0) {
            rightNode = rightNode.next;
        }

        ListNode next = rightNode.next;
        rightNode.next = null;

        ListNode cur = leftNode.next;
        leftNode.next = next;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = leftNode;
            leftNode = cur;
            cur = temp;
        }
        if (pre == null) {
            return leftNode;
        }
        pre.next = leftNode;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    public void test() {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4);
        h.next.next.next.next = new ListNode(5);

        ListNode node = reverseBetween(h, 1, 2);
    }
}
