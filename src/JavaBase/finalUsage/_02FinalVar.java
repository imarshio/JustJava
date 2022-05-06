package JavaBase.finalUsage;

/**
 * @author masuo
 * @data 6/5/2022 下午5:03
 * @Description final 修饰变量
 *  -- 显示赋值的变量在编译器完成赋值
 */

public class _02FinalVar {

    // 编译期就已经完成了赋值
    private final int i = 0;

    // final 修饰的变量必须赋初始值，Variable 'j' might not have been initialized
    // private final int j;
}
