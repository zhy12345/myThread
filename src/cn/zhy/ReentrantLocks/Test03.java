package cn.zhy.ReentrantLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test03
 * @Description 可中断锁 lockInterruptibly
 * @Author zhy
 * @Date 2019/4/4
 */
public class Test03 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "***" + i);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("我来响应中断");
    }
}
