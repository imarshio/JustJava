package leetcode.mid;
/**
* @arithmetics ：
* @author ： masuo
* @time ：2021年3月3日 上午11:00:10
* 类说明
*/
public class CountBits {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountBits cBits = new CountBits(5);
		int []result = cBits.countBits(5);
		for(int r:result) {
			System.out.println(r);
		}
	}
	
	public CountBits(int num) {
		int []one = new int [num+1];
		int higtBit = 0;
		for(int i=1;i<=num;i++) {
			if((i&(i-1))==0) {
				higtBit = i;
			}
			one[i] = one[i-higtBit]+1;
		}
		for(int r:one) {
			System.out.println(r);
		}
	}
	public int[] countBits(int num) {
		int []one =new int[num+1];
        for(int i=0;i<=num;i++){
        	one[i]=getBit(i);
        }
        return one;
    }

	private int getBit(int i) {
		// TODO Auto-generated method stub
		int num = 0;
		while(i!=0) {
			i=i&(i-1);
			num++;
		}
		return num;
	}
}
