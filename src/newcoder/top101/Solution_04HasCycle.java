package newcoder.top101;

/**
 * @author masuo
 * @data 1/4/2022 下午3:21
 * @Description 链表是否有环
 */

public class Solution_04HasCycle {

    /**
     * 两个方法，
     * 1、将value放入hashmap，由于hashmap的特性，不允许有重复的key，所以判断其长度和链表长度
     * 2、快慢指针，
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        if(head == null || head.next == null){
            //{}, {1}
            return false;
        }
        // {1,2} ,{}
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int x) {
            val = x;
        }
    }

}
