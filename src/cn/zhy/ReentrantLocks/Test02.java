package cn.zhy.ReentrantLocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test02
 * @Description tryLock
 * @Author zhy
 * @Date 2019/4/4
 */
public class Test02 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(()->{
            for(int i = 0;i<20;i++){
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"***"+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }).start();
        new Thread(()->{
            try {
                boolean flag = lock.tryLock(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我要疯了 *** 我不等了");
        }).start();
    }

}
