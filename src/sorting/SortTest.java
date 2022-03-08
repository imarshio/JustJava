package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sorting.sort.BubbleSort;
import sorting.sort.InsertSort;
import sorting.sort.QuickSort;
import sorting.sort.SelectSort;
import sorting.sort.ShellSort;
import org.junit.Test;

public class SortTest {


	//no
	@Test
	public void quickSort() {
		//
		System.out.println(Math.ceil(7/2.0));
		System.out.println("quick sort begin");
		QuickSort qSort= new QuickSort();
		qSort.sort();
	}

	//no
	@Test
	public void shellSort() {
		//
		System.out.println("shell sort");
		ShellSort shellSort= new ShellSort();
		shellSort.sort();
	}

	//ok
	@Test
	public void insertSort() {
		//
		System.out.println("insert sort");
		InsertSort insort= new InsertSort();
		insort.sort();
		System.out.println("insert sort");
		insort.insertSort();
	}


	//ok
	@Test
	public void selectSort() {
		//
		System.out.println("selects ort");
		SelectSort selectSort= new SelectSort();
		selectSort.sort();
	}


	//ok
	@Test
	public void bubbleSort() {
		//
		System.out.println("bubble");
		BubbleSort bSort = new BubbleSort();
		bSort.sort();
	}


	@Test
	public void listTest() {
		// 数组删除不能正向删除或者foreach删除，倒序删除或使用iterator
		List<String> list = new ArrayList<>(5);
		list.add("122");
		list.add("233");
		list.add("344");
		list.add("455");
		list.add("566");

		for (String string : list) {
			System.out.println(string);
		}

		list.remove(0);
		list.remove(3);
		System.out.println("remove");
		for (String string : list) {
			System.out.println(string);
		}
	}


	public int absolute(int i, int j) {
		//
		int r = (i - j) > 0 ? (i - j) : (j - i);
		System.out.println(r);
		return r;
	}

	@Test
	public void scannerTest() {
		Scanner scanner= new Scanner(System.in);
		System.out.print("");
		String string= scanner.next();
		System.out.println(string);
		scanner.close();
	} 
}
