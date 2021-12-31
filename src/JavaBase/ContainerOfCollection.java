package JavaBase;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author masuo
 * @create 2021/7/17 22:01
 * @Description 容器之collection
 */
public class ContainerOfCollection{



    @SuppressWarnings("")
    public static void main(String[] args) {
        collection();

        define();

        ArrayListTest();

        lengthOfArray();

        LinkedListTest();

        SetTest();

        MapTest();

        displacement();
    }

    private static void displacement() {

        //位移运算
        int i = 32;//2的5次方
        getBinary(i);
        //<<
        int j = i<<1;
        System.out.println("右移一位运算："+j);
        getBinary(j);
        //>>,开口向左，左移运算相当于除以2
        int x = i>>1;
        System.out.println("左移运算："+x);
        getBinary(x);

        Math.abs(10);
    }

    private static void getBinary(int i) {
        System.out.println("i的二进制表示为"+Integer.toBinaryString(i));
    }

    private static void MapTest() {

    }

    private static void SetTest() {
        //HashSet
        Set<String> s = new HashSet<>();

        //这里使用线程测试同步性以及迭代无序性

        new Thread("thread1"){
            @Override
            public void run() {
                System.out.println("thread1");
                s.add("thread10");
                s.add("thread11");
                s.add("thread12");
                s.add("thread13");
            }
        }.start();

        s.add("asd");
        s.add("cas");
        s.add("abc");

        new Thread("thread2"){
            @Override
            public void run() {
                s.add("thread20");
                s.add("thread21");
                s.add("thread22");
                s.add("thread23");
                System.out.println("thread2");
            }
        }.start();

        new Thread("thread3"){
            @Override
            public void run() {
                System.out.println(s);
            }
        }.start();
    }

    private static void LinkedListTest() {
        System.out.println("linked list test");

        //声明一下，看看如何初始化的
        List<String> ls = new LinkedList<>();

        //加数据，因为有前后中，所以数据大于等于3就行，
        ls.add("1");
        ls.add("2");
        ls.add("3");
        ls.add("4");
        ls.add("5");

        System.out.println(ls);
    }

    private static void lengthOfArray() {

        int []is = new int[10];
        is[0]=1;
        is[1]=1;
        is[2]=1;
        is[3]=1;
        System.out.println(is.length);
        List<Integer> li = new ArrayList<>();
        li.add(1);
        li.add(1);
        li.add(1);
        li.add(1);
        System.out.println(li.size());
    }

    private static void ArrayListTest() {
        List<String> al1 = new ArrayList<>(2);
        List<String> al2 = new ArrayList<>();
        al2.add("1");//第一次添加数据，数组长度length变成10，size变成1
        al2.add("2");//length不变，size+1 2
        al2.add("3");//length不变，size+1 3
        al2.add("4");//length不变，size+1 4
        al2.add("5");//length不变，size+1 5
        al2.add("6");//length不变，size+1 6
        al2.add("7");//length不变，size+1 7
        al2.add("8");//length不变，size+1 8
        al2.add("9");//length不变，size+1 9
        al2.add("10");//length不变，size+1 10 ，此时，数组长度和size已经相等，下一次添加数据时便会触发扩容机制
        al2.add("11");//length = length + length >> 1
        al2.add("12");
        al2.add("13");
        al2.add("14");
        al2.add("15");
        al2.add("16");
        System.out.println(al2.get(1).getClass());
        System.out.println(al2.size());//11
        System.out.println(al2);
        Object[] objects = al2.toArray();
        System.out.println(objects[10]);

    }

    private static void collection() {
        class CollectionClass implements Collection{

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public Object[] toArray(Object[] a) {
                return new Object[0];
            }
        }
    }

    private static void define() {
        List<String> lv = new Vector<>();

        List<String> la = new ArrayList<>();
        List la1 = new ArrayList<>();

        List<String> ll = new LinkedList<>();

        List<String> ls = new Stack<>();

//        Collections c = new Collections();
//        Collections.sort();

        Set<String> sh = new HashSet<>();

        Set<String> st = new TreeSet<>();

        Set<String> sl = new LinkedHashSet<>();

        Map<Integer,String> m1 = new HashMap<>(10);

        m1.put(null,"0");
        m1.put(0,"1");
        m1.put(1,"2");
        m1.put(2,"3");
        m1.put(3,"4");
        m1.put(4,"5");
        m1.put(5,"5");
        m1.put(6,"5");
        m1.put(7,"5");
        m1.put(8,"5");
        m1.put(9,"5");
        m1.put(10,"5");
        m1.put(11,"5");
        m1.put(12,"5");//13

        Map<Integer,String> m2 = new TreeMap<>();

        Map<Integer,String> m3 = new LinkedHashMap<>(10,0.7f,false);
        m3.put(0,"0");
        m3.put(1,"5");
        m3.put(4,"3");
        m3.put(5,"6");
        m3.put(2,"8");
        m3.put(3,"1");

        System.out.println("m3:"+m3);

        Map<Integer,String> m4 = new Hashtable<>();

        Map<Integer,String> m5 = new ConcurrentHashMap<>();

    }
}
