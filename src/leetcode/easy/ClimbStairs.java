package leetcode.easy;

/**
 * @arithmetics ：DP()动态规划
 * @author ： masuo
 * @date  ：2021年3月11日 上午1:00:22
 * @description :假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 		每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairs {

	public static void main(String[] args) {
		//  Auto-generated method stub
		ClimbStairs cStairs= new ClimbStairs();
		//int num = cStairs.climbStairs(45);
		int num = cStairs.climbStairsTwo(5);
		System.out.println(num);
	}

	public int climbStairsTwo(int n) {

		//method two
		//q代表前两位的值，pre代表的是前一位的值
		int sum = 1,pre = 0,q = 0;
		for (int i = 0; i < n; i++) {
			//q代表的位置下移一位，因为
			q = pre;
			//pre代表的位置下移一位
			pre = sum;
			//此时sum = q + pre; 代表的就是: f(n-1) + f(n-2)
			sum = q + pre;
		}
		return sum;
	}

	public int climbStairs(int n) {
		//这种很慢，因为只有一次调用，所以我们可以考虑向上加

		//method one
		if(n==1) {
			return 1;
		}else if(n==2) {
			return 2;
		}else {
			return climbStairs(n-1)+climbStairs(n-2);
		}
	}
}
