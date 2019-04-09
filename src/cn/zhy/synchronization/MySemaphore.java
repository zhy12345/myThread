package cn.zhy.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName MySemaphore
 * @Description Semaphore , 是 synchronized 的加强版，作用是控制线程的并发数量
 * 方法 acquire( int permits ) 参数作用，及动态添加 permits 许可数量　　没有通路线程没法执行
 *
 * @Author zhy
 * @Date 2019/4/8
 */
public class MySemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5,true);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 20 ; i++) {
            int index = i ;
            cachedThreadPool.execute(()->{
                if(index <= 1){
                    try {
                        semaphore.acquire(5);
                        System.out.println(Thread.currentThread().getName() + "，取得通路1");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{

//                    semaphore.release(2);
                    System.out.println("可用通路"+semaphore.availablePermits());
//                    System.out.println(Thread.currentThread().getName() + "，释放");
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
