package sort;


// import org.junit.Test;

/**
 * @author ： masuo
 * @arithmetics ：
 * @date ：2021年5月14日 下午3:08:42
 * @description : 选择排序
 */
public class SelectSort {

    // @Test
    public void sort() {
        int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0};

        for (int i = 0; i < unsort.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < unsort.length; j++) {
                if (unsort[min] > unsort[j]) {
                    min = j;
                }
            }
            int temp = unsort[i];
            unsort[i] = unsort[min];
            unsort[min] = temp;
        }

        for (int i : unsort) {
            System.out.println(i);
        }
    }
}
