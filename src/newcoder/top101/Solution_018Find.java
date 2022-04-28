package newcoder.top101;

/**
 * @author masuo
 * @data 22/4/2022 下午2:13
 * @Description 二维数组中的查找
 */

public class Solution_018Find {

    // 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
    // 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    public boolean Find(int target, int[][] array) {
        int height = array.length;
        if (height == 0) {
            return false;
        }
        int length = array[0].length;
        if (length == 0) {
            return false;
        }

        if (array[0][0] > target || array[height - 1][length - 1] < target) {
            return false;
        }

        // 想用到二分法，就需要找到一个有序的数组，在二维数组中，一列/一排都已经有序了，但是其内部还是杂乱无章
        // 所以我们需要找到一个点符合有序的规则，观察得知边界是符合条件的，
        // 边界就是：左上 -> 左下 -> 右下  或 左上 -> 右上 -> 右下
        // 取左下为中点
        int i = height - 1, j = 0;
        while (i >= 0 && j < length) {
            if(array[i][j] == target){
                return true;
            }else if(array[i][j] < target) {
                // 向右移动
                j++;
            }else if(array[i][j] > target) {
                // 向上移动
                i--;
            }
        }
        return false;
    }
}
