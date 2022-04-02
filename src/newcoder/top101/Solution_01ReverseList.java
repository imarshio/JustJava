package newcoder.top101;


/**
 * @author masuo
 * @data 1/4/2022 上午10:18
 * @Description 反转链表
 */

public class Solution_01ReverseList {
    /*
     * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
     *
     * 数据范围： 0≤n≤1000
     * 要求：空间复杂度 O(1) ，时间复杂度 O(n) 。
     */
    public ListNode ReverseList(ListNode head) {
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

    public ListNode ReverseList1(ListNode head) {
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
}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int x) {
        val = x;
    }
}
