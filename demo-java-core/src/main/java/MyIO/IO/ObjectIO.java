package MyIO.IO;

import org.junit.Test;

import javax.annotation.processing.FilerException;
import java.io.*;

/**
 * @author masuo
 * @date: 2021/12/25/ 下午12:55
 * @description 对象流 ObjectOutputStream / ObjectInputStream
 * 增强了缓冲区的功能
 * 增加了读写8种基本数据类型和字符串的功能
 * 增加了读写对象的功能 readObject / writeObject
 * serialVersionUID:序列化版本号ID，保证序列化的类和反序列化的类是同一个类
 * transient: 瞬时的，表示不可被持久化，不会被序列化，也就不会被写入磁盘
 * 静态属性同样不会被序列化
 */
public class ObjectIO {

    /**
     * 写入对象 OutputStream - ObjectOutputStream
     */
    @Test
    public void objectWrite() {
        String read = "../JavaCode/src/files/temp3.txt";
        File file = new File(read);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new FilerException("创建文件失败！");
                }
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(outputStream);

            // 写入一个字节的整数值，会阻塞直到字节被写入
            // 写入会变成乱码，你可以使用二进制方式读取
            os.write(49);
            byte[] buffBytes = new byte[]{'h', 'e', 'l', 'l', 'o'};
            // 写入字节或字节数组
            os.write(buffBytes);
            os.write(buffBytes, 1, 3);
            // 写入 boolean
            os.writeBoolean(true);
            // 写入字节或字符
            os.writeByte(48);
            os.writeBytes("ma shuo");
            // 写入字符或字符数组
            os.writeChar(50);
            os.writeChars("ma shuo");
            // 写入浮点类型
            os.writeDouble(1.0);
            os.writeFloat(1.0f);
            // 写入短长整数类型
            os.writeInt(20);
            os.writeLong(1212121112121L);
            os.writeShort(45454);
            // 写入UTF
            os.writeUTF("utf - 8");
            // 写入对象
            os.writeObject(getStudent(23, "mashuo", 1));

            System.out.println("写入成功！");
            // 这都是写入，即序列化
            os.flush();
            os.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Student getStudent(int age, String name, int sex) {
        return new Student(age, name, sex, "打羽毛，球");
    }

    /**
     * 读取对象 InputStream
     */
    @Test
    public void objectRead() {
        String read = "../JavaCode/src/files/temp3.txt";
        File file = new File(read);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new FilerException("创建文件失败！");
                }
            }

            // 读取文件 -- 读取文件是反序列化的过程，
            // 注意,读取的时候，你需要知道文件里面都存储了什么类型的数据，再去读取
            // 需要按照写入的顺序进行读取

            {
                // 当然，你也可以直接读取read(),读取后获取其类型，在处理
                InputStream is = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(is);

                int o;
                while ((o = ois.read()) != -1) {
                    System.out.println(o);
                }
                // 直接读取 ，不会读取对象类，需要额外读取
                Student student = (Student) ois.readObject();
                System.out.println(student.getAge());
                System.out.println(student.getName());
                // 当属性值被transient修饰后，在获取此属性的值时，将会获取其默认初始化的值，而不是你填写的属性值
                System.out.println(student.getSex());
                System.out.println(Student.hobby);
            }

            {
                // 当然，你也可以直接读取read(),读取后获取其类型，在处理
                InputStream is = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(is);

                int o;
                // while ((o = ois.read()) != -1) {
                //    if(o instanceof double)
                //}
                // 直接读取 ，不会读取对象类，需要额外读取
                // student student = (ObjectIO.student) ois.readObject();
                // System.out.println(student.getAge());
                // System.out.println(student.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 准备一个对象
    static class Student implements Serializable {
        private static final long serialVersionUID = -4259932672711249045L;
        int age;
        String name;
        transient int sex;

        public static String getHobby() {
            return hobby;
        }

        public static void setHobby(String hobby) {
            Student.hobby = hobby;
        }

        static String hobby;

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Student(int age, String name, int sex, String hobby) {
            this.age = age;
            this.name = name;
            this.sex = sex;
            Student.hobby = hobby;
        }
    }
}
