package leetcode.easy;

public class IsMonotonic {

	public static void main(String[] args) {
		//
		IsMonotonic im = new IsMonotonic();
		int[] A = { 1, 1, 0 };
		System.out.println(im.isMonotonicOneLoop(A));
	
	}

	public boolean isMonotonicOneLoop(int[] A) {
		boolean inc=true,dec=true;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] < A[i + 1]) {
				dec =false;
			}
			if (A[i] > A[i + 1]) {
				inc = false;
			}
		}

		return inc||dec;
	}
	
	public boolean isMonotonicTwoLoop(int[] A) {
		boolean inc=true,dec=true;
		

		return inc||dec;
	}

}
