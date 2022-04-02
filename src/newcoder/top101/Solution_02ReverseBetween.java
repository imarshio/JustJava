package newcoder.top101;


import org.junit.Test;

import java.util.List;

/**
 * @author masuo
 * @data 1/4/2022 上午10:18
 * @Description 反转链表
 */

public class Solution_02ReverseBetween {
    /*
     * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
     *
     * 数据范围： 0≤n≤1000
     * 要求：空间复杂度 O(1) ，时间复杂度 O(n) 。
     */
    private void reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //Cur_next 指向cur节点的下一个节点
            ListNode Cur_next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = Cur_next;
        }
    }


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
        ListNode pre = null;
        ListNode left = head;
        while (--m > 0) {
            pre = left;
            left = pre.next;
        }
        ListNode right = head;
        while (--n > 0) {
            right = right.next;
        }
        ListNode next = right.next;

        // 断开
        right.next = null;

        //翻转 left -> right,同时拼接最后面的部分
        ListNode cur = left.next;
        //此时left为末尾，下一个就是next
        left.next = next;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = left;
            left = cur;
            cur = temp;
        }

        // 拼接
        if(pre == null){
            return left;
        }
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
    public void text() {
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

}

