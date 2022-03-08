package sorting.sort;

import org.junit.Test;

/**
 * @arithmetics 插入排序
 * @author masuo
 * @date  2021-5-14 11:36:17
 */
public class InsertSort {

	@Test
	public void sort() {
		int []unsorted = {1,2,4,5,8,9,7,4,85,0};

		int length = unsorted.length;
		
		for(int i=1;i<length;i++) {
			int j=i;
			while(j>0) {
				if (unsorted[j]<unsorted[j-1]) {
					int temp = unsorted[j-1];
					unsorted[j-1]=unsorted[j];
					unsorted[j]=temp;
				}
				j--;
			}
		}
		
		for (int i : unsorted) {
			System.out.println(i);
		}
	}

	/**
	* @arithmetics 插入排序
	* @author masuo
	* @date 2021-5-23 9.52~5-23 9.59 am
	*
	*/
	@Test
	public void insertSort() {
		int []unsorted = {1,2,4,5,8,9,7,4,85,0};
		int length = unsorted.length;
		for(int i=1;i<length;i++) {
			int j=i;
			while(j>0) {
				if(unsorted[j]>unsorted[j-1]) {
					int temp = unsorted[j];
					unsorted[j]=unsorted[j-1];
					unsorted[j-1]=temp;
				}
				j--;
			}
		}
		for(int num :unsorted) {
			System.out.println(num);
		}
	}
}
