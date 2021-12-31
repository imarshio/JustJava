package JavaCore.MyJVM.MyStack;

/**
 * @author: masuo
 * @data: 2021/7/29 16:34
 * @Description: 栈桢执行顺序测试
 */

public class StackFrameTest {

    public static void main(String[] args) {

        StackFrameTest sft = new StackFrameTest();//进入执行main，线程栈中出现main
        sft.method1();//method1执行结束，线程栈中method1消失
    }//main执行结束，线程栈中main消失，同时栈桢生命周期结束

    private void method1() {

        System.out.println("method1开始执行~");//进入执行method1，线程栈中出现method1
        method2();
        System.out.println("method1结束执行~");//method2执行结束，线程栈中method2消失
    }

    private void method2() {
        System.out.println("method2开始执行~");//进入执行method2，线程栈中出现method2
        method3();
        System.out.println("method2结束执行~");//method3执行结束，线程栈中method3消失
    }

    private void method3() {
        System.out.println("method3开始执行~");//进入执行method3，线程栈中出现method3
        System.out.println("method3结束执行~");
    }
}
