package JavaCore.MyLock;

import java.util.concurrent.locks.Lock;

/**
 * @author masuo
 * @data 2021/9/27 15:47
 * @Description 锁
 *  几种常见的锁
 *  公平锁 - 非公平锁
 *  悲观锁 - 乐观锁
 *  独享锁 - 共享锁
 *  互斥锁 - 读写锁
 *  分段锁：一种锁的设计，细化锁的操作，例，当数组中有一个元素需要加锁，那么只给这个元素加锁即可
 *  偏向锁、轻量锁、重量锁：指Synchronized锁的状态，在对象头中的标识
 *  自旋锁：尝试获取锁的线程不会立即阻塞，而是尝试循环去获取
 *  可重入锁（递归锁）：在同一线程的外层方法获取锁的时候，进入内层方法时，会自动获取锁。
 */

public class MyLock {

    //Java中有两种加锁的方式，
    /*
     * 1.synchronized  关键字  1.5之前的唯一一种加锁方式
     * 2.Lock  接口  public interface Lock{} since 1.5
    * */


}
