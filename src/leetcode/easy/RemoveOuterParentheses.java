package leetcode.easy;

/**
 * @arithmetics ：栈
 * @author ： masuo
 * @time ：2021年3月1日 下午3:42:13 
 */
/**
 * 类说明: 输入："(()())(())" 输出："()()()"
 * 
 * @author Masuo
 *
 */
public class RemoveOuterParentheses {

	public static void main(String[] args) {
		// Auto-generated method stub
		String str = "()()(())(()(()))";
		RemoveOuterParentheses roParentheses = new RemoveOuterParentheses();
		roParentheses.removeOuterParentheses(str);
		roParentheses.delLastChar();
	}

	public String removeOuterParentheses(String S) {
		StringBuilder sBuilder = new StringBuilder();
		int leval = 0;
		for (char c : S.toCharArray()) {
			
			if (c == ')')
				leval--;
			if (leval >= 1)
				sBuilder.append(c);
			if (c == '(')
				leval++;
		}
		System.out.println(sBuilder.toString());
		return sBuilder.toString();
	}
	
	public void delLastChar() {
		String string = "1,2,3,4,5,";
		string = string.substring(0, string.length()-1);
		System.out.println(string);
	}

}
