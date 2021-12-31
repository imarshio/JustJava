package leetcode.dynamicPrograming.solutions;

/**
 * @author masuo
 * @data 2021/11/22 11:14
 * @Description 斐波那契数列
 */

public class Fibonacci {


    //斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和
    //F(0) = 0，F(1) = 1
    //F(n) = F(n - 1) + F(n - 2)，其中 n > 1
    public int fib(int n) {
        //滑动窗口法，不耗费内存，亦可使用备忘法，但是堆内存消耗较大
        int f = 0;
        int s = 1;
        if(n==0){
            return f;
        }
        if(n==1){
            return s;
        }
        for(int i = 1; i < n ; ++i){
            int x = f + s;
            f = s;
            s = x;
        }
        return s;
    }

    //泰波那契序列Tn定义如下：
    //
    //T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
    //
    //给你整数n，请返回第 n 个泰波那契数Tn 的值。

    public int tribonacci(int n) {
        //滑动窗口法，不耗费内存，亦可使用备忘法，但是堆内存消耗较大
        int f = 0;
        int s = 1;
        int t = 1;
        if(n<2){
            return n;
        }
        if(n==2){
            return t;
        }
        for(int i = 2; i < n; ++i){
            int x = f + s + t;
            f = s;
            s = t;
            t = x;
        }
        return t;
    }
}
