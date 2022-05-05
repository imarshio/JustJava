package JavaCore.MyLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author masuo
 * @data 29/4/2022 上午9:00
 * @Description 读写锁
 */

public class MyReentrantReadWriteLock {

    public void test() {
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

        // 创建读锁，给读操作上锁
        rrwl.readLock().lock();

        // 释放读锁
        rrwl.readLock().unlock();

        // 创建写锁，给写操作上锁
        rrwl.writeLock().lock();

        // 释放写锁
        rrwl.writeLock().lock();
    }
}
