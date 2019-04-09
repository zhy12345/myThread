package cn.zhy.synchronization;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName MyCyclicBarrier
 * @Description CyclicBarrier 栅栏 线程计数器  同步屏障
 * @Author zhy
 * @Date 2019/4/8
 */
public class MyCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier  = new CyclicBarrier(3);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            fixedThreadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"开始等待");
                try {
                    int await = cyclicBarrier.await();
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"开始执行"+",wait:"+await);
                    System.out.println("阻塞的线程数量：" + cyclicBarrier.getNumberWaiting());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        fixedThreadPool.shutdown();
    }
}
