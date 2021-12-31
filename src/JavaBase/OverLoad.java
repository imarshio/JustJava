package JavaBase;

/**
 * @author masuo
 * @create 2021/7/15 22:37
 * @Description 重载
 */
public class OverLoad {
    void fun(){
        //无参数
    }

    void fun(int i){
        //有一个参数
    }

    void fun(int i,int j){
        //有两个参数
    }

    void fun(String s){
        //有一个参数，类型不同
    }

//    String fun(){
//        //返回值不同，但是方法名一样，不算重载，不可行
//        //'fun()' is already defined in 'JavaBase.OverLoad'
//        return "00";
//    }
}
