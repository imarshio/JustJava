package newcoder.top101;


import org.junit.Test;

import java.util.List;

/**
 * @author masuo
 * @data 1/4/2022 上午10:18
 * @Description 反转链表
 */

public class Solution_02ReverseBetween {

    /**
     * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)，空间复杂度 O(1)。
     * 例如：
     * 给出的链表为 1 → 2 → 3 → 4 → 5 → NULL, m=2,n=4,
     * 返回 1 → 4 → 3 → 2 → 5 → NULL.
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        //找到头和前置节点
        ListNode pre = null;
        ListNode left = head;
        while (--m > 0) {
            pre = left;
            left = pre.next;
        }
        //找到尾和后置节点
        ListNode right = head;
        while (--n > 0) {
            right = right.next;
        }
        ListNode next = right.next;

        // 断开与后面相连的部分，也是孤立前面的链表，这样就形成了反转前面的链表，
        right.next = null;

        // 翻转 left -> right,同时拼接最后面的部分
        ListNode cur = left.next;
        // 断开，而因为此时left为末尾，下一个就是next
        left.next = next;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = left;
            left = cur;
            cur = temp;
        }

        // 拼接
        if(pre == null){
            // pre 为null，则left为头，直接返回
            return left;
        }
        // pre不为null，则代表有前置节点，则拼接起来
        pre.next = left;
        return head;
    }

    public ListNode re(ListNode head, int m, int n) {
        ListNode start = head;
        ListNode pre = null;
        //找到起点
        while (--m > 0) {
            pre = start;
            start = start.next;
        }
        //找到终点
        ListNode end = head;
        while (--n > 0) {
            end = end.next;
        }

        //将中间部分反转
        ListNode last = end.next;
        end.next = null;
        while (start != null) {
            ListNode next = start.next;
            start.next = last;
            last = start;
            start = next;
        }
        //将起点前的节点与终点相连
        if (pre == null)
            return end;
        pre.next = end;
        return head;
    }

    @Test
    public void test() {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        // h.next.next = new ListNode(3);
        // h.next.next.next = new ListNode(4);
        // h.next.next.next.next = new ListNode(5);

        ListNode node = reverseBetween(h, 1, 2);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int x) {
            val = x;
        }
    }

}

