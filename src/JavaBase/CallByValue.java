package JavaBase;

import org.junit.Test;

/**
 * @author masuo
 * @data 2021/9/27 10:37
 * @Description 值传递
 */

public class CallByValue {

    @Test
    public void test(){
        foo(5);
    }

    public void foo(int a){
        int x = 6;
        hoo(a,x);
        System.out.println(x);
    }

    private void hoo(int a, int x) {
        a = a -1;
        x = x*a;
    }
}
