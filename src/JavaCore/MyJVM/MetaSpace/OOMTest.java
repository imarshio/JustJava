package JavaCore.MyJVM.MetaSpace;

/**
 * @author: masuo
 * @data: 2021/8/7 15:12
 * @Description: 设置内存溢出实例
 *      思想：想要方法区溢出，就必须要将方法区填满，有两个方法，
 *      1.缩小方法区的容量，将其上限设置的小点
 *      2.
 *      想要将方法区填满就需要知道方法区中都能存放什么信息
 *      我们大概知道方法区中存放的是类的信息，所我们需要不断地创建类来堆满方法区
 *      通过循环不断生成类
 */

public class OOMTest {
}
