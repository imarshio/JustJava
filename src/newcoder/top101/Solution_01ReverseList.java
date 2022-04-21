package newcoder.top101;


import org.junit.Test;

import java.util.Stack;

/**
 * @author masuo
 * @data 1/4/2022 上午10:18
 * @Description 反转链表
 */

public class Solution_01ReverseList {

    // 节点
    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int x) {
            val = x;
        }
    }

    /*
     * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
     *
     * 数据范围： 0≤n≤1000
     * 要求：空间复杂度 O(1) ，时间复杂度 O(n) 。
     */
    public ListNode reverseList0(ListNode head) {
        // 双指针 ，一个指针指向头节点，一个指针指向头节点的下一节点
        // null
        if(head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode next = head.next;
        //断开
        pre.next = null;
        while (next != null){
            ListNode temp = next.next;
            // 断开连接，并反向  1 --> 2 --> 3 --> 4  ==>   null <-- 1 <-- 2
            next.next = pre;
            pre = next;
            next = temp;
        }
        return pre;
    }

    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null){
            // {},{1}
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        while (next != null){
            ListNode temp = next.next;
            // 翻转
            next.next = head;
            head = next;
            next = temp;
        }

        return head;
    }

    // 递归翻转
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public ListNode reverseList3(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        // 递归，直到最后一个时回调，此时newHead是尾节点
        ListNode newHead = reverseList3(head.next);
        //回调之后，head是前置节点,这一步是将前置节点变为后置节点，即由 1 -> 2 ==> 1 -> 2 -> 1，
        // 这一步能完成翻转主要是因为，旧链表与新链表共享尾节点
        head.next.next = head;
        // 之后断开前置节点，让新链表的尾节点一直都是head的后置节点
        head.next = null;
        return newHead;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head = reverseList0(head);
        head = reverseList1(head);
        head = reverseList2(head);
    }

}