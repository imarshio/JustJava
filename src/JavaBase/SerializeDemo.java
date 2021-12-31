package JavaBase;

import java.io.*;

/**
 * @author masuo
 * @data 2021/9/18 16:29
 * @Description 序列化
 */

public class SerializeDemo {

    public static void main(String[] args) {
        CanSer sc = new CanSer();
        sc.age =10 ;
        sc.name = "ms";
        sc.ser = 100;

        File files = new File("D:\\ser11.txt");
        //try {
        //    boolean newFile = files.createNewFile();
        //    System.out.println(newFile);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //序列化
        //try {
        //
        //    OutputStream file = new FileOutputStream("D:\\sers.ser");
        //    ObjectOutputStream oos = new ObjectOutputStream(file);
        //    oos.writeObject(sc);
        //    oos.close();
        //    file.close();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //反序列化
        CanSer cs = new CanSer();
        try {
            FileInputStream fis = new FileInputStream("D:\\sers.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            cs = (CanSer) ois.readObject();
            System.out.println(cs.ser);
            System.out.println(cs.age);
            System.out.println(cs.name);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class CanSer implements Serializable {
    //声明一个可以被序列化的类，需要继承Serialize接口
    public int age;
    public String name ;
    public transient int ser;

    public CanSer() {
    }

    public CanSer(int age, String name, int ser) {
        this.age = age;
        this.name = name;
        this.ser = ser;
    }
}