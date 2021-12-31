package JavaBase;

/**
 * @author masuo
 * @create 2021/7/14 11:34
 * @Description 重写
 */
public class OverWrite {


    public static void main(String[] args) {

        OverWrite ow = new OverWrite();

        try {
            ow.overWrite();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }


    private void overWrite() {
        //发生在父类与子类之间
        class Father extends Object{
            private int i;

            protected void fun(){
                System.out.println("这是子类没有的父类方法。");
            }

            protected void funAccessControl(){
                //默认包级可见
                System.out.println("父类访问控制权限为protected。");
            }

            protected Father funReturnType(){
                //默认包级可见
                System.out.println("父类返回类型为Father");
                return new Father();
            }

            protected void funException()throws Throwable{
                //默认包级可见
                System.out.println("fun");
            }
        }

        class Son extends Father{
            /** 满足里氏替换原则
             * 1.访问控制权限大于等于父类
             * 2.返回类型是父类返回类型或其子类
             * 3.抛出异常为父类异常或其子类
             */

//            private void funAccessControl(){
//                //'funAccessControl()' in 'Son' clashes with 'funAccessControl()' in 'Father'; attempting to assign weaker access privileges ('private'); was 'protected'
//                //本人翻译水平有限，有道翻译为：'Son'中的'funAccessControl()'与'Father'中的'funAccessControl()'冲突;试图分配较弱的访问权限('private');“保护”
//                System.out.println("子类访问控制权限为private。未通过。");
//            }

            public void funAccessControl(){
                //
                System.out.println("子类访问控制权限为public。通过。");
            }

//            public Father funReturnType(){
//                //Incompatible types. Found: 'java.lang.Object', required: 'Father'
//                //不兼容的类型，
//                return new Object();
//            }

            public Father funReturnType(){
                //
                System.out.println("子类 funReturnType的返回类型是Son，Father的子类。");
                return new Son();
            }

            public void funException() throws Exception{
                //
            }
        }

        Father son = new Son();
        son.fun();
        son.funAccessControl();

        try {
            son.funException();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        son.funReturnType();
        System.out.println(son.toString());

    }

}
