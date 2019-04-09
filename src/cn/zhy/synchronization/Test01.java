package cn.zhy.synchronization;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test01
 * @Description 多线程可以同时调用没上锁的方法演示。
 * synchronize锁的是内存的对象，谁占有锁谁执行  堆内存的区域
 * @Author zhy
 * @Date 2019/4/3
 */
public class MySynchronized {

    synchronized void m1() {
        System.out.println("我是同步方法..........开始");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是同步方法..........结束");

    }

    static void m2() {
        System.out.println("我是普通方法..........开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是普通方法..........结束");
    }


    public static void main(String[] args) {
        MySynchronized t = new MySynchronized();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.m1();
//            }
//        }).start();

        new Thread(t::m1).start();


        new Thread(() -> t.m2()).start();


    }
}
