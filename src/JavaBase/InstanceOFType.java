package JavaBase;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author masuo
 * @create 2021/7/13 10:58
 * @Description InstanceOF Type 关键字
 */
public class InstanceOFType {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        System.out.println(ls.getClass());//class java.util.ArrayList
        System.out.println(ls instanceof Collection);

        //基础类型
        char c = '1';
        // System.out.println(c instanceof String);//Inconvertible types; cannot cast 'char' to 'java.lang.String'

        //null
        System.out.println(null instanceof Object);//false
        // System.out.println(Integer.valueOf(2017-02-01));

        //class类的实例对象
        String s = "123";
        System.out.println(s instanceof String);

        //接口实现类，如ArrayList是AbstractList的子类，是List<E>, RandomAccess, Cloneable, java.io.Serializable的接口实现类
        List<String> ls2 = new ArrayList<>();
        System.out.println(ls2 instanceof List);

        //直接或间接子类
        List<String> ls3 = new ArrayList<>();
        System.out.println(ls3 instanceof AbstractCollection);

    }
}
