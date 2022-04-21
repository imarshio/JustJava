package newcoder.top101;

import org.junit.Test;

/**
 * @author masuo
 * @data 14/4/2022 上午11:22
 * @Description NC50 链表中的节点每k个一组翻转
 */

public class Solution_050ReverseKGroup {

    /**
     * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
     * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
     * 你不能更改节点中的值，只能更改节点本身。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here

        int size = getSize(head);
        // 记录当前旋转的是第几组
        int start = 0, end = k - 1;
        while (end < size){
            // 旋转
            head = reverseBetween(head,start,end);
            // 下一组
            start = start + k;
            end = end + k;
        }
        return head;
    }

    private ListNode reverseBetween(ListNode node, int start, int end) {
        // K
        int k = end - start;

        // 找到头和前置节点
        ListNode pre = null;
        ListNode head = node;
        while(start-- > 0){
            pre = head;
            head = pre.next;
        }

        // 找到尾
        ListNode tail = head;
        while (k-- > 0){
            tail = tail.next;
        }
        // 后置节点
        ListNode next = tail.next;

        // 断开
        tail.next = null;

        // 翻转 head -> tail
        ListNode cur = head.next;
        head.next = next;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = head;
            head = cur;
            cur = temp;
        }
        // pre -> head
        if(pre == null){
            return head;
        }
        pre.next = tail;
        return node;
    }

    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    static class ListNode {
        int val;

        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void test() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node = reverseKGroup(node, 2);
        System.out.println(node);
    }
}