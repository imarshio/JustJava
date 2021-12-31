package CodeTest;

import java.util.HashSet;
import java.util.List;

/**
 * @author masuo
 * @data 2021/8/13 11:07
 * @Description
 */

public class RandomTest {

    /**
     * default left value is 0 {@code double}
     */
    private static final double DEFAULT_LEFT_DOUBLE = 0.0;

    /**
     * default right value is 1.0 {@code double}
     */
    private static final double DEFAULT_RIGHT_DOUBLE = 1.0;

    /**
     * default left value is 0 {@code int}
     */
    private static final int DEFAULT_LEFT_INT = 0;

    /**
     * default right value is 1 {@code int}
     */
    private static final int DEFAULT_RIGHT_INT = 1;


    //don't let anyone to instance your class
    private RandomTest() {
    }


    /**
     * Return a Random {@code double} value with a positive sign,
     * greater than or equal to {@code 0.0} and
     * less than {@code 1.0} double
     * <p>
     * {@code 0.0} <= {@code return values} < {@code 1.0}
     *
     * @return a pseudorandom {@code double}
     */
    public static double origin() {
        return Math.random();
    }

    /**
     * Return a random {@code double} value whit a positive sign,
     * greater than or equals to {@code 0.0} and
     * less than {@code right} double
     * <p>
     * {@code 0.0} <= {@code return values} < {@code right}
     *
     * @param right to limit the return value
     * @return a pseudorandom {@code double} less than right and
     * greater than or equal to{@code 0.0}
     */
    public static double random(double right) {
        return Math.random() * right;
    }

    /**
     * Return a random {@code double} value whit a positive sign,
     * greater than or equals to {@code left} and
     * less than {@code right} double
     * <p>
     * {@code left} <= {@code return values} < {@code right}
     *
     * @param left  to limit the return value's min number
     * @param right to limit the return value's max number
     * @return a pseudorandom {@code double} less than right and
     * greater than or equal to{@code left}
     */
    public static double random(double left, double right) {
        //left<right
        if (left > right) {
            double temp = left;
            left = right;
            right = temp;
        }
        return Math.random() * (right - left) + left;
    }

    /**
     * Return a random {@code int} value whit a positive sign,
     * greater than or equals to {@code 0} and
     * less than {@code right} int
     * <p>
     * {@code 0} <= {@code return values} < {@code right}
     *
     * @param right to limit the return value's max number
     * @return a pseudorandom {@code int} less than right and
     * greater than or equal to{@code 0}
     */
    public static int randomInt(int right) {
        return (int) (Math.random() * right);
    }

    /**
     * Return a random {@code int} value ,
     * greater than or equals to {@code left} and
     * less than {@code right} int
     * <p>
     * {@code left} <= {@code return values} < {@code right}
     *
     * @param left  to limit the return value's min number
     * @param right to limit the return value's max number
     * @return a pseudorandom {@code int} less than right and
     * greater than or equal to{@code left}
     */
    public static int randomInt(int left, int right) {

        //left<right
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }

        // when left = Integer.MIN_VALUE ,{@code right-left}
        // will over the range of the Integer

        if (left == Integer.MIN_VALUE) {
            return (int) (Math.random() * left + right);
        }
        return (int) (Math.random() * (right - left)) + left;
    }

    /**
     * Return a group of double random that less than {@code 1.0}
     * and greater than or equal to {@code 0.0}
     *
     * @param capacity the capacity of the randomList
     * @return {@code double[]}
     */
    public static double[] randoms(int capacity) throws Exception {
        return randoms(DEFAULT_LEFT_DOUBLE, DEFAULT_RIGHT_DOUBLE, capacity);
    }

    /**
     * Return a group of double random that less than {@code right}
     * and greater than or equal to {@code 0.0}
     *
     * @param right    to limit the return value's max number
     * @param capacity the capacity of the randomList
     * @return {@code double[]}
     */
    public static double[] randoms(double right, int capacity) throws Exception {
        return randoms(DEFAULT_LEFT_DOUBLE, right, capacity);
    }

    /**
     * Return a group of double random that less than {@code right}
     * and greater than or equal to {@code left}
     *
     * @param left     to limit the return value's min number
     * @param right    to limit the return value's max number
     * @param capacity the capacity of the randomList
     * @return {@code double[]}
     */
    public static double[] randoms(double left, double right, int capacity) throws Exception {
        //left<right
        if (left > right) {
            double temp = left;
            left = right;
            right = temp;
        }
        //capacity>0
        if (capacity <= 0) {
            throw new Exception("random capacity error：capacity="+capacity);
        }
        double[] randoms = new double[capacity];
        for (int i = 0; i < capacity; i++) {
            randoms[i] = random(left, right);
        }
        return randoms;
    }

    /**
     * Return a group of int random that less than {@code right}
     * and greater than or equal to {@code 0}
     *
     * @param right    to limit the return value's max number
     * @param capacity the capacity of the randomList
     * @return {@code int[]}
     */
    public static int[] randomInts(int right, int capacity) throws Exception {
        return randomInts(DEFAULT_LEFT_INT, right, capacity);
    }

    /**
     * Return a group of int random that less than {@code right}
     * and greater than or equal to {@code left}
     *
     * @param left     to limit the return value's min number
     * @param right    to limit the return value's max number
     * @param capacity the capacity of the randomList
     * @return {@code int[]}
     */
    public static int[] randomInts(int left, int right, int capacity) throws Exception {
        //left<right
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }
        if (capacity <= 0) {
            throw new Exception("随机数组容量异常,capacity=："+capacity);
        }
        int[] randoms = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            randoms[i] = randomInt(left, right);
        }
        return randoms;
    }

    /**
     * Return a group of int random that less than {@code border}
     * and greater than or equal to {@code Integer.MIN_VALUE}
     *
     * @param border   the one side of the randomList
     * @param capacity the capacity of the randomList
     * @return {@code int[]}
     */
    public static int[] smallerThan(int border, int capacity) throws Exception {
        if (capacity <= 0) {
            throw new Exception("随机数组容量异常,capacity=："+capacity);
        }
        int[] randoms = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            randoms[i] = randomInt(Integer.MIN_VALUE, border);
        }
        return randoms;
    }

    /**
     * Return a group of int random that less than {@code Integer.MAX_VALUE}
     * and greater than or equal to {@code border}
     *
     * @param border   the one side of the randomList
     * @param capacity the capacity of the randomList
     * @return {@code int[]}
     */
    public static int[] biggerThan(int border, int capacity) throws Exception {
        if (capacity <= 0) {
            throw new Exception("随机数组容量异常,capacity=："+capacity);
        }
        int[] randoms = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            randoms[i] = randomInt(border, Integer.MAX_VALUE);
        }
        return randoms;
    }

}