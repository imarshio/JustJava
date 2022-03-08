package sorting.sort;


import org.junit.Test;

/**
 * @author masuo
 * @arithmetics 冒泡排序
 * @date 2021-05-14 2:54:21  10min
 */
public class BubbleSort {

    @Test
    public void sort() {
        int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0};
        for (int i = unsort.length; i > 0; i--) {
            for (int j = 0; j < unsort.length - 1; j++) {
                if (unsort[j] > unsort[j + 1]) {
                    int temp = unsort[j];
                    unsort[j] = unsort[j + 1];
                    unsort[j + 1] = temp;
                }
            }

        }
        for (int i : unsort) {
            System.out.println(i);
        }

    }
}
