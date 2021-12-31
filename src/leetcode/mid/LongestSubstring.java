package mid;

import java.util.*;


public class LongestSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestSubstring ls = new LongestSubstring();
		System.out.println(ls.longestSubstring("ababbc", 2));
	}

	public int longestSubstring(String s, int k) {
		if (s.length() < k) return 0;
        HashMap<Character, Integer> counter = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
        	System.out.println(counter.getOrDefault(s.charAt(i), 0) + 1);
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        return 0;
    }
}
