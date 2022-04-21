package leetcode.hard;


import leetcode.mid.ReverseBetween;
import org.junit.Test;

/**
 * @author masuo
 * @data 15/4/2022 上午10:55
 * @Description K 个一组翻转链表
 */

public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        int size = getSize(head);
        int start = 0, end = k - 1;
        while (end < size) {
            head = reverseBetween(head, start, end);
            start += k;
            end += k;
        }
        return head;
    }

    public ListNode reverseBetween(ListNode node, int start, int end) {
        int n = end - start;
        ListNode pre = null;
        ListNode head = node;
        while (start-- > 0) {
            pre = head;
            head = pre.next;
        }
        ListNode tail = head;
        while (n-- > 0) {
            tail = tail.next;
        }
        ListNode next = tail.next;
        tail.next = null;
        ListNode cur = head.next;
        head.next = next;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = head;
            head = cur;
            cur = temp;
        }

        if (pre == null) {
            return head;
        }
        pre.next = head;
        return node;
    }

    public int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
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

        ListNode node = reverseKGroup(h, 2);
    }
}
