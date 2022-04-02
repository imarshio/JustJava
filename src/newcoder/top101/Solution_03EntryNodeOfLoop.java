package newcoder.top101;

/**
 * @author masuo
 * @data 1/4/2022 下午3:21
 * @Description 链表是否有环,若其中包含环，请找出该链表的环的入口结点，否则，返回null
 */

public class Solution_03EntryNodeOfLoop {

    /**
     * 两个方法，
     * 1、将value放入hashmap，由于hashmap的特性，不允许有重复的key，所以判断其长度和链表长度
     * 2、快慢指针，快慢指针相遇时，让快指针回到起点一步一步走，再相遇就是入口
     * @param head
     * @return
     */
    public ListNode hasCycle(ListNode head) {

        if(head == null || head.next == null){
            //{}, {1}
            return null;
        }
        // {1,2} ,{}
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                fast = head;
                while (fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                //相遇
                return fast;
            }
        }
        return null;
    }
}
