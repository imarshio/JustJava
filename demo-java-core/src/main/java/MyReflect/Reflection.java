package MyReflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author masuo
 * @data 19/1/2022 下午4:13
 * @Description 反射
 * 反射即程序运行期间，JVM可以通过反射获取任意一个类得属性和方法，并访问他们
 * 反射可以干什么？
 * 动态的创建对象，
 * 举例，
 * Spring IoC，就是将对象交给Spring容器管理，在需要的时候再通过反射生成对象实例给调用者
 * Spring容器通过反射可以完成 对象创建，属性注入，对象管理
 * 接下来，我们会通过反射实现对象的创建，对象方法的调用，对象属性的获取
 */

public class Reflection {

    // 反射获取类的三种方式
    @Test
    public void test1() {
        // ClassName.class
        Class<Student> studentClass = Student.class;
        // 获取类的全限定名称
        System.out.println(studentClass.getName());
        // JavaCore.MyReflect.Student

        // 获取单纯的类名
        String simpleName = studentClass.getSimpleName();
        System.out.println(simpleName);
        // Student

        // 获取类的属性，包括public，protected，private，default（package），但是不能获取继承的数据
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
            // private java.lang.String JavaCore.MyReflect.Student.num
            // private int JavaCore.MyReflect.Student.age
            // public int JavaCore.MyReflect.Student.stuClass
            // protected boolean JavaCore.MyReflect.Student.sex
            // int JavaCore.MyReflect.Student.num1
        }

        try {
            // 根据属性名称获取指定属性的类型,因为不确定输入的属性名称是正确的，所以需要抛出异常
            Field num = studentClass.getDeclaredField("num");
            System.out.println(num);
            // private java.lang.String JavaCore.MyReflect.Student.num
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // 获取类的类加载器
        ClassLoader classLoader = studentClass.getClassLoader();
        System.out.println(classLoader);
        // sun.misc.Launcher$AppClassLoader@18b4aac2

        // 获取类的构造器们
        Constructor<?>[] declaredConstructors = studentClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
            // public JavaCore.MyReflect.Student()
            // public JavaCore.MyReflect.Student(java.lang.String,int,int)
        }

        try {
            Constructor<Student> declaredConstructor = studentClass.getDeclaredConstructor(String.class, int.class, int.class);
            System.out.println(declaredConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // studentClass.asSubclass()

        // 获取类的所有方法
        Method[] declaredMethods = studentClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
            // public java.lang.String JavaCore.MyReflect.Student.getNum()
            // public int JavaCore.MyReflect.Student.getAge()
            // public int JavaCore.MyReflect.Student.getStuClass()
            // public void JavaCore.MyReflect.Student.setAge(int)
            // public void JavaCore.MyReflect.Student.setNum(java.lang.String)
            // public void JavaCore.MyReflect.Student.setStuClass(int)
        }

        // 获取指定方法，两个参数
        // 第一个：方法的名称
        // 第二个：方法参数类型.class,无参可省略
        try {
            Method setAge = studentClass.getDeclaredMethod("setAge", int.class);
            System.out.println(setAge);

            // getMethod(),只能获取共有方法，尝试获取私有的会抛出异常NoSuchMethodException
            Method method = studentClass.getMethod("setAge", int.class);
            // Method method1 = studentClass.getMethod("Student");
            System.out.println(method);
            // System.out.println(method1);

            // invoke,调用方法，两个参数,返回
            // 第一个参数：表明这个方法是被谁调用，即对象实例
            // 第二个参数：用于方法调用的参数
            Student student = studentClass.newInstance();
            Object invoke = method.invoke(student, 18);
            System.out.println(invoke);

            System.out.println(student);
            // Student{num='null', age=18, stuClass=0, sex=false, num1=0}

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

// 准备一个类
class Student {
    private String num;
    private int age;
    public int stuClass;
    protected boolean sex;
    int num1;// default

    @Override
    public String toString() {
        return "Student{" +
                "num='" + num + '\'' +
                ", age=" + age +
                ", stuClass=" + stuClass +
                ", sex=" + sex +
                ", num1=" + num1 +
                '}';
    }

    public Student() {
    }

    public Student(String num, int age, int stuClass) {
        this.num = num;
        this.age = age;
        this.stuClass = stuClass;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStuClass() {
        return stuClass;
    }

    public void setStuClass(int stuClass) {
        this.stuClass = stuClass;
    }
}
