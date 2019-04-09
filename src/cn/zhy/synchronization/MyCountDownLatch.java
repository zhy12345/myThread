package cn.zhy.synchronization;


import jdk.internal.dynalink.beans.StaticClass;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName MyCatchDownLatch
 * @Description 倒计时门闩
 * @Author zhy
 * @Date 2019/4/8
 */
public class MyCountDownLatch {

    static CountDownLatch countDownLatch = new CountDownLatch(3);

    public Runnable getMyRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "**** bagin");
                try {
                    countDownLatch.await();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "**** end");


            }
        };
        return runnable;
    }


    private static void myOpreate() {
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + "**** My Notify");
    }

    public static void main(String[] args) {
        MyCountDownLatch t = new MyCountDownLatch();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            if (i >= 1 && i <= 3) {
                myOpreate();
            }else{
                fixedThreadPool.execute(t.getMyRunnable());
            }

        }

        fixedThreadPool.execute(t.getMyRunnable());

        fixedThreadPool.shutdown();
    }
}
