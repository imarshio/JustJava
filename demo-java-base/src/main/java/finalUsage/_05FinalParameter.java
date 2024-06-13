package JavaBase.finalUsage;

/**
 * @author masuo
 * @data 6/5/2022 下午4:21
 * @Description final 修饰参数
 * -- 我们知道，final修饰的变量是编译期常量
 */

public class _05FinalParameter {

    // 编译期就已经完成了赋值
    private final int i = 0;

    // 类加载时完成赋值
    private final int j;

    public _05FinalParameter(int j) {
        this.j = j;
    }
}
