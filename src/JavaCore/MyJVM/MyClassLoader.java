package JavaCore.MyJVM;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;

/**
 * @author masuo
 * @create 2021/7/27 8:46
 * @Description 类加载机制
 */


public class MyClassLoader {

    public static void main(String[] args) {
        test();

        getBootstrapClassLoaderUrls();
    }

    private static void getBootstrapClassLoaderUrls() {

        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        System.out.println(bootstrapClassPath);

        //获取BootStrapClass Loader可加载的类的路径
        URL[] urLs = bootstrapClassPath.getURLs();
        for (URL url:urLs) {
            System.out.println(url);
        }
    }

    /**
     * java中的类加载机制
     * 1.什么是类加载？
     *      类的加载是指将一个类的.class文件中的二进制数据读取到内存中，将其放在
     *      运行时数据区的方法区内，然后再堆区创建一个java。long。Class对象，用
     *      来封装类在方法区内的数据结构。类的加载的最终产品是位于堆区中的Class对象，
     *      class对象封装了类在方法区内的数据结构，并且向Java程序员提供了访问方法区
     *      内的数据结构的接口。
     */
    private static void test() {

        //获取系统加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取其上级类加载器:扩展类加载器
        System.out.println(systemClassLoader.getParent());//sun.misc.Launcher$ExtClassLoader@1b6d3586

        //获取扩展类加载器的上级,这里的null不代表他没有上级类加载器，只是说她的上级加载器不是由Java编写
        //Bootstrap Class Loader
        System.out.println(systemClassLoader.getParent().getParent());//null

        //使用系统默认加载器进行加载
        ClassLoader classLoader = MyClassLoader.class.getClassLoader();//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);

        //获取String类的加载器，引导类加载器加载的，说明Java的黑犀牛内部类都是由引导类加载器加载的
        System.out.println(String.class.getClassLoader());//null
    }

}
