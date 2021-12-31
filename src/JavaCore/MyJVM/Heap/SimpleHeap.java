package JavaCore.MyJVM.Heap;

/**
 * @author: masuo
 * @data: 2021/8/2 14:45
 * @Description: -Xms10m -Xmx10m -XX:+PrintGCDetails
 */

public class SimpleHeap {

    private int id;

    public SimpleHeap(int id){
        this.id = id;

    }

    void show(){
        System.out.println(id);
    }


    public static void main(String[] args) {

        SimpleHeap s1 = new SimpleHeap(10);
        SimpleHeap s2 = new SimpleHeap(20);

        s1.show();
        s2.show();
    }
}
//2+0.5+0.5+7=10