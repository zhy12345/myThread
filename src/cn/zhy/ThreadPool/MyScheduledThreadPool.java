package cn.zhy.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyScheduledThreadPool
 * @Description 延时加载线程池
 * @Author zhy
 * @Date 2019/4/6
 */
public class MyScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        for (int i = 0; i <10000 ; i++) {
            int index = i;
            scheduledThreadPool.schedule(()->{
                System.out.println(Thread.currentThread().getName() + "执行任务" + index);
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },10000,TimeUnit.MILLISECONDS);
        }

        scheduledThreadPool.shutdown();
    }
}
