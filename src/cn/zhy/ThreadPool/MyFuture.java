package cn.zhy.ThreadPool;

import java.util.concurrent.*;

/**
 * @ClassName MyFuture
 * @Description Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询
 * 是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
 * @Author zhy
 * @Date 2019/4/6
 */
public class MyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(5000);
            return 1000;
        });

        new Thread(task).start();
//        阻塞
//        System.out.println("222222222222222222222");
        System.out.println(task.get());
//        System.out.println(task.get(2000,TimeUnit.MILLISECONDS));

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        Future<Integer> future = fixedThreadPool.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

//        System.out.println(future.get());
        System.out.println(future.isDone());
        System.out.println(future.get());
        fixedThreadPool.shutdown();
    }
}
