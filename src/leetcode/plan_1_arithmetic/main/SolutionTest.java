package leetcode.plan_1_arithmetic.main;

import leetcode.plan_1_arithmetic.solution.*;
import org.junit.Test;

/**
 * @author masuo
 * @data 2021/8/18 11:11
 * @Description
 */

public class SolutionTest {

    public static void main(String[] args) {

        SolutionTest st = new SolutionTest();
        st.day1();
        st.day2();
        st.day3();
        st.day6();
        st.day7();
    }

    @Test
    public void day7() {
        Solution_7 s7 = new Solution_7();
        //s7.floodFill(new int[][]{{1,1,1}, {1,1,0}, {1,0,1},},1,1,2);
        //s7.floodFill(new int[][]{ {0,0,0}, {0,0,0},},1,1,2);
        //s7.floodFill(new int[][]{ {0,0,0}, {0,1,0},},1,1,2);
        //s7.floodFill(new int[][]{ {0,0,0}, {0,1,1},},1,1,1);
        s7.maxAreaOfIsland(new int[][]{ {0,0,1,0,0,0,0,1,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                                        {0,0,0,0,0,0,0,1,1,0,0,0,0}});
    }

    @Test
    public void day6() {
        Solution_6 solution_6 = new Solution_6();

        //out:for (int i = 0; i < 10; i++) {
        //    int j = 0;
        //    while(j<2){
        //        if(i==2){
        //            i++;
        //            continue out;
        //        }
        //        System.out.println(i*j);
        //        j++;
        //    }
        //
        //
        //}
        //System.out.println(solution_6.lengthOfLongestSubstring("abcabcbb"));
        //System.out.println(solution_6.lengthOfLongestSubstring3("abcabcbb"));
        System.out.println(solution_6.checkInclusion1("adc","dcda"));

    }

    @Test
    public void day3() {
        Solution_3 solution = new Solution_3();
        solution.moveZeroes(new int[]{0,1,0,3,12});
    }

    @Test
    public void day2() {
        Solution_2 solution = new Solution_2();
        //int[] nums = solution.sortedSquares(new int[]{-4, -1, 0, 3, 10});
        //for (int i:nums) {
        //    System.out.println(i);
        //}
        //solution.rotateOne(new int[]{-1,-100,3,99},2);
        solution.rotateTwo(new int[]{-1,-100,3,99},2);

    }

    @Test
    public void day1() {

        Solution_1 solution = new Solution_1();
        //System.out.println(solution.search(new int[]{0}, 9));
        //System.out.println(solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
        System.out.println(solution.searchInsert(new int[]{-1, 0, 3, 5, 9, 12}, 22));

    }
}
