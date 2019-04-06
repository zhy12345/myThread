package cn.zhy.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyCachedThreadPool
 * @Description 可缓存的线程池，默认时间是60s 当超过60s 创建新的线程继续执行任务
 * @Author zhy
 * @Date 2019/4/6
 */
public class MyCachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            int index = i;
            cachedThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "执行任务" + index);
            });
            TimeUnit.MILLISECONDS.sleep(70);
//            TimeUnit.SECONDS.sleep(70);
        }
        cachedThreadPool.shutdown();
    }

}
