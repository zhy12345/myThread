package cn.zhy.ReentrantLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test01
 * @Description fair 锁
 * @Author zhy
 * @Date 2019/4/4
 */
public class Test01 extends Thread {
    static Lock lock = new ReentrantLock(true);
    int i = 0;

    public void run() {

        for (; i < 100; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "***********" + i);
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Test01 t = new Test01();
//        Test01 t3= new Test01();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
//        Thread t3 = new Thread(new Thread());
//        t.run();
//        t3.run();
        t1.start();
        t2.start();
    }

}
