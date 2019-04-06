package cn.zhy.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName MySingleThreadPool
 * @Description 只创建一个线程执行, 串行执行任务,按顺序执行线程在执行过程中失败而终止
 * 关闭，如果需要执行，将替换一个新的线程继续执行后续任务
 * @Author zhy
 * @Date 2019/4/6
 */
public class MySingleThreadPool {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "执行任务" + index);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        singleThreadExecutor.shutdown();
    }

}
