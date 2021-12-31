package CodeTest;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * @author masuo
 * @create $(YEAR)-$(MONTH)-$(DAY) $(TIME)
 * @Description
 */
public class Test1 {
    public static void main(String[] args) {

        //String s = ",";
        //System.out.println(s.substring(0, 0));

        StringBuilder sb = new StringBuilder();
        sb.append("");
        System.out.println(sb.toString().equals(""));
        System.out.println(sb.toString() == null);

        Object o = "111";
        String s = "222";
        System.out.println(o+s);
        System.out.println((o+s).toString());
        System.out.println((s+o).toString());
        System.out.println((s+o).getClass());
        //Test1.main(new String[]{"111"});
        //List<Integer> l1 = new ArrayList<>();
        //l1.add(0);
        //l1.add(1);
        //l1.add(2);
        //l1.add(3);
        //l1.add(3);
        //Integer [] l2={2,3,4};
        //for (Integer i:l1) {
        //    for (Integer j:l2
        //         ) {
        //        if (i.equals(j)){
        //            System.out.println(i);
        //            break;
        //        }
        //    }
        //
        //}
        //
        //Dictionary ht = new Hashtable();
        //
        ////String s = String.format("%s,%s","1111","2222");
        ////System.out.println(s);
        //
        //test();
        //test2(s);
    }

    /**
      * @Description:
      * @author: masuo
      * @date: 2021/7/29 15:11
      * @param:
      */
    private static void test2(String s) {
        int i = 10;
        int j = 20;
        test();
    }

    /**
      * @Description:
      * @author: masuo
      * @date: 2021/7/29 15:12
      * @param:
      */
    private static void test() {
        int i = 30;
        int j = 40;
    }

}
