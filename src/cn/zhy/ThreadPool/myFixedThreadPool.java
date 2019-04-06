package cn.zhy.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName myFixedThreadPool
 * @Description 固定长度线程池，若超出等待其他线程执行完毕在进行继续执行
 * @Author zhy
 * @Date 2019/4/6
 */
public class myFixedThreadPool {
    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6 ; i++) {
            final int index  = i ;
            fixedThreadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"执行任务" + index);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        fixedThreadPool.shutdown();
    }
}
