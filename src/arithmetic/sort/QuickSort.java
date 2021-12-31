package arithmetic.sort;

import org.junit.Test;


/**
 * @arithmetics 快排
 * @author  masuo
 * @date  2021-5-14 10:10:00
 */
public class QuickSort {

	@Test
	public void sort() {
		int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0};
		int length = unsort.length;
		//这里
		quickSortPL(unsort,0,length-1);
		quickSortPR(unsort,0,length-1);

		for (int i : unsort) {
			System.out.println(i);
		}
	}

	public void quickSortPL(int []unsort,int left,int right) {
		if(left>=right){
			//相等时返回，不然会陷入无限循环 ***
			return;
		}
		//从小到大
		//基准点在左侧
		int indexL = left;//基准值下标
		int indexR = right;//
		int pivot = unsort[left];
		//当基准点在左侧时，我们需要从另一侧出发
		while(left<right){
			while ((unsort[right]>pivot)){
				//右侧数据都应该大于基准点
				right--;
			}
			while (unsort[left]<=pivot && left<right){
				//左侧数据都应该小于等于基准点，
				// 因为我们是从基准点出发，所以我们需要保证左边是小于等于基准点的
				// 并且需要保证坐下标小于右下标
				left++;
			}
			//当两侧运动都结束时，此时，左测指向的数据大于基准点，右侧指向的数据小于基准点，
			// 指向的数据交换
			if (left < right) {
				//交换的前提是两个指针符合规则
				int temp = unsort[left];
				unsort[left] = unsort[right];
				unsort[right] = temp;

			}
		}
		//一遍完整的循环之后，此时除基准点外的其他数据都以结束点为中心(通过上面的限制条件，左右下标指向同一下标)，分成两组数据
		//左侧数据都是小于基准点的
		//此时需要交换基准点与结束点

		//为了方便理解，这里声明的变量名称设置为“中间值”   ***
		int mid = left;
		unsort[indexL] = unsort[mid];
		unsort[mid] = pivot;

		quickSortPL(unsort, indexL, mid-1);
		quickSortPL(unsort, mid+1, indexR);
	}

	public void quickSortPR(int []unsort,int left,int right) {

		if(left>=right){
			return;
		}

		//从小到大
		//基准点在右侧,记录基准值下标
		int indexR = right;
		int indexL = left;
		int pivot = unsort[right];
		//基准值在右侧，所以我们需要从左侧出发
		while(left<right){
			while (unsort[left]<pivot){
				//左侧数值都小于基准值
				left++;
			}
			while (unsort[right]>=pivot && left<right){
				//右侧都大于基准值，前提是下标符合规则
				right--;
			}
			if(left!=right){
				//此时找到符合条件的双边，进行交换
				int temp = unsort[left];
				unsort[left] = unsort[right];
				unsort[right] = temp;
			}

		}

		//一边循环之后，交换基准值与中心值
		int mid = left;
		unsort[indexR] = unsort[mid];
		unsort[mid] = pivot;

		quickSortPR(unsort, indexL, mid-1);
		quickSortPR(unsort, mid+1, indexR);
	}

}
