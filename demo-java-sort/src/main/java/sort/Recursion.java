package sort;

// import org.junit.Test;

/**
 * @author masuo
 * @date 2021/9/6 14:19
 * @description 求阶乘
 */

public class Recursion {


    // @Test
    public void test() {
        System.out.println(recursion(5));
    }

    /**
     * 递归：recursion
     *
     * @param n 参数
     * @return int
     */
    public int recursion(int n) {
        if (n == 1) {
            return n;
        }
        return n * recursion(n - 1);
    }

}
