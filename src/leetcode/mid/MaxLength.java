package leetcode.mid;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author masuo
 * @data 15/4/2022 下午2:13
 * @Description 最长无重复子数组
 */

public class MaxLength {

    public int maxLength (int[] arr) {
        // write code here
        int len = arr.length;
        if(len == 0 || len == 1){
            return len;
        }
        Deque<Integer> deque = new ArrayDeque<>();

        int length = 0,maxLen = 0;
        for (int i : arr) {

            if (!deque.contains(i)){
                length++;
            }else {
                while (deque.poll() != i) {
                    length--;
                }
            }
            deque.offer(i);
            maxLen = Math.max(length,maxLen);
        }
        return maxLen;
    }
}
