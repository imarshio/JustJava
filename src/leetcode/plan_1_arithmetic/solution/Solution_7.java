package leetcode.plan_1_arithmetic.solution;

/**
 * @author masuo
 * @data 2021/8/24 7:54
 * @Description DFS/BFS：深度优先搜索/广度优先搜索
 */

public class Solution_7 {

    /**
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     * <p>
     * 给你一个坐标(sr, sc)表示图像渲染开始的像素值（行 ，列）和一个新的颜色值newColor，让你重新上色这幅图像。
     * <p>
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
     * 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，
     * ……
     * 重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
     * <p>
     * 最后返回经过上色渲染后的图像。
     * <p>
     * 输入:
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * 输出: [[2,2,2],[2,2,0],[2,0,1]]
     * 解析:
     * 在图像的正中间，(坐标(sr,sc)=(1,1)),
     * 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2，
     * 因为它不是在上下左右四个方向上与初始点相连的像素点。
     *
     * @param image    二维整数数组
     * @param sr       图像的像素：行
     * @param sc       图像的像素：列
     * @param newColor 新的颜色值
     * @return image
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //该点一共有上下左右四个方位，我们需要分别判断该点的四个方位是否与该点相连
        int ori = image[sr][sc];
        if (ori == newColor) {
            return image;
        }
        image[sr][sc] = newColor;
        fillUDLR(image, sr, sc, ori, newColor);
        return image;
    }

    /**
     * UDLR(Up, Down, Left,Right)
     * DFS--深度优先搜索:Deep First Search
     * 因为在循环的时候会直接到最底层，所以叫深度优先搜索
     * BFS--广度优先搜索:Breadth First Search
     * 因为在循环的时候会首先遍历最上层
     *
     */
    private void fillUDLR(int[][] image, int sr, int sc, int old, int newColor) {
        int hight = image.length;//高度
        int length = image[0].length;//长度
        if (sr >= 0 && sr < hight) {
            //上,上边界时不可进入
            if (sr != hight - 1) {
                if (image[sr + 1][sc] == old) {
                    image[sr + 1][sc] = newColor;
                    fillUDLR(image, sr + 1, sc, old, newColor);
                }
            }
            //下，下边界时不可进入
            if (sr != 0) {
                if (image[sr - 1][sc] == old) {
                    image[sr - 1][sc] = newColor;
                    fillUDLR(image, sr - 1, sc, old, newColor);
                }
            }
        }
        if (sc >= 0 && sc < length) {
            //左,左边界不可进入
            if (sc != 0) {
                if (image[sr][sc - 1] == old) {
                    image[sr][sc - 1] = newColor;
                    fillUDLR(image, sr, sc - 1, old, newColor);
                }
            }
            //右，右边界不可进入
            if (sc != length - 1) {
                if (image[sr][sc + 1] == old) {
                    image[sr][sc + 1] = newColor;
                    fillUDLR(image, sr, sc + 1, old, newColor);
                }
            }
        }
    }



    /**
     * 给定一个包含了一些 0 和 1 的非空二维数组grid 。
     * <p>
     * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
     * 你可以假设grid 的四个边缘都被 0（代表水）包围着。
     * <p>
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     *
     * @param grid 非空二维数组
     * @return 最大的岛屿面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        //为了让一个土地不被多次访问，设置额外数组存储土地是否被访问
        int []grids = new int[grid.length*grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grids[(i+1)*(j+1)]!=1){
                    //没被访问过
                    grids[(i+1)*(j+1)-1]=1;
                    if(grid[i][j]!=0){
                        //陆地，对陆地的上下左右四个方位探查
                        max = Math.max(max,findUDLR(grid,i,j,grids));
                    }
                }

            }
        }
        return max;
    }

    private int findUDLR(int[][] grid, int i, int j, int[] grids) {
        //能进入说明此地块是陆地，在此陆地的上下左右找陆地
        int lands = 1;
        int hight = grid.length;//高度 i
        int length = grid[0].length;//长度 j
        //上，边界情况
        if(i>0){
            //往上走：i-1
            if(grids[i*(j+1)+1]!=1){
                //没被访问过
                //设置成已被访问
                grids[i*j+1]=1;
                if(grid[i-1][j]!=0){
                    //陆地
                    lands++;
                    findUDLR(grid, i-1, j, grids);
                }
            }

        }
        //下

        //左

        //右

        return lands;
    }

}
