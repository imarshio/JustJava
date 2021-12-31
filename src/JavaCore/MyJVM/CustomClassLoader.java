package JavaCore.MyJVM;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author masuo
 * @create 2021/7/28 17:52
 * @Description
 */
public class CustomClassLoader extends ClassLoader{

    private Object CustomClassLoader;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        return (Class<?>) CustomClassLoader;
    }
}


class CustomClassLoader2 extends URLClassLoader{


    public CustomClassLoader2(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }   
}