package sortal.test;

import org.junit.Test;
import sortal.Sorting;
import sortal.impl.BubbleSortImpl;
import sortal.impl.QuickSortImpl;
import sortal.impl.SelectSortImpl;

/**
 * @author masuo
 * @data 2/3/2022 上午8:57
 * @Description TODO
 */

public class SortTest {

    @Test
    public void bubble() {
        Sorting sort = new BubbleSortImpl();
        int[] sortDescent = sort.sortDescent(new int[]{1, 2, 4, 5, 8, 9, 7, 4, 85, 0});
        for (int i : sortDescent) {
            System.out.println(i);
        }
    }

    @Test
    public void select() {
        Sorting sort = new SelectSortImpl();
        int[] sortDescent = sort.sortDescent(new int[]{1, 2, 4, 5, 8, 9, 7, 4, 85, 0});
        for (int i : sortDescent) {
            System.out.println(i);
        }
    }

    @Test
    public void quick() {
        Sorting sort = new QuickSortImpl();
        int[] sortDescent = sort.sortDescent(new int[]{1, 2, 4, 5, 8, 9, 7, 4, 85, 0});
        for (int i : sortDescent) {
            System.out.println(i);
        }
    }
}
