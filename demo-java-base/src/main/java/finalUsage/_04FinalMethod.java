package JavaBase.finalUsage;

import org.junit.Test;

/**
 * @author masuo
 * @data 6/5/2022 下午4:14
 * @Description TODO
 */

public class _04FinalMethod extends _01FinalUsage {

    // cannot override
    // @Override
    // public final void aMethod() {
    //     System.out.println("final 修饰方法");
    // }

    // final 修饰方法
    @Test
    public void finalMethod() {
        // final 修饰的方法不能被修改
        aMethod();
    }
}
