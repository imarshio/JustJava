package leetcode.easy;

import java.util.Stack;

/**
 * @author masuo
 * @data 15/4/2022 下午4:36
 * @Description 有效括号序列
 */

public class IsValid {

    public boolean isValid (String s) {
        // write code here
        if(s == null || s.equals("")){
            return true;
        }
        Stack<Character> strings = new Stack<>();
        int i = 0;
        while (i < s.length()){
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                strings.push(c);
            }else {
                if(!strings.isEmpty() && strings.peek() == c){
                    strings.pop();
                }else return false;
            }
            i++;
        }
        return strings.isEmpty();
    }
}
