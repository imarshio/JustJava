package sortal;

import java.util.ArrayList;

/**
 * @author masuo
 * @data 2/3/2022 上午5:19
 * @Description TODO
 */
public interface Sorting {

    /**
     * 从大到小，Descent下降
     *
     * @param unsorted 未排序数组
     * @return 排序数组
     */
    int[] sortDescent(int[] unsorted);

    int[] sortAscent(int[] unsorted);

    ArrayList<Integer> sortDescent(ArrayList<Integer> unsorted);

    ArrayList<Integer> sortAscent(ArrayList<Integer> unsorted);
}
