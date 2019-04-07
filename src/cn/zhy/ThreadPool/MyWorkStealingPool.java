package cn.zhy.ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName MyWorkStealingPool
 * @Description 当一条线程执行完成后  会窃取其他任务继续执行.
 * @Author zhy
 * @Date 2019/4/7
 */
public class MyWorkStealingPool {

    public static void main(String[] args) throws IOException {
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
//        System.getProperties();
//        获取cpu是几核的
        int cpuCount = Runtime.getRuntime().availableProcessors();
//        System.out.println(cpuCount);
        for (int i = 0; i < 13; i++) {
            final int index = i;
            workStealingPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "**********" + index);
                try {
                    if (index <= 3) {
                        Thread.sleep(5000);

                    } else {
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        workStealingPool.shutdown();
//        由于是精灵线程（守护线程、后台线程）主线程不阻塞的话，看不到输出。
        System.in.read();

    }
}
