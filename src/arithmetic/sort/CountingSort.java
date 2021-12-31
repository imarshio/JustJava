package arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @author masuo
 * @data 2021/9/15 13:39
 * @Description 计数排序，第一印象，很浪费空间
 */

public class CountingSort {


    @Test
    public void sort() {
        int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0};
        //基数排序使用的是取最大值和最小值，在相等下标的数组内值+1，这样存储

        int max = Arrays.stream(unsort).max().getAsInt();
        int min = Arrays.stream(unsort).min().getAsInt();

        int[] sorted = new int[max - min + 1];
        for (int i : unsort) {
            ++sorted[i];
        }

        int index = 0;
        for (int i = 0; i < sorted.length; i++) {
            while (sorted[i]!=0){
                unsort[index] = i;
                --sorted[i];
                ++index;
            }
        }

        for (int i : unsort) {
            System.out.println(i);
        }

    }
}
