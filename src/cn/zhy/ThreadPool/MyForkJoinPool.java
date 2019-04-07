package cn.zhy.ThreadPool;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName MyForkJoin
 * @Description Fork 分叉  ， join 合并  ,  将数组分成多份  交给多个线程 处理 求和  最后再总的求和
 * @Author zhy
 * @Date 2019/4/7
 */
public class MyForkJoinPool {
    static int num[] = new int[100];
    static final Integer MAX_NUM = 5;

    static {
        for (int i = 0; i < 100; i++) {
            num[i] = i + 1;
        }
//        System.out.println(Arrays.stream(num));
    }

    static class AddTask extends RecursiveTask<Integer> {

        int start, end;

        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer compute() {
            Integer sum = 0;
            if (end - start <= MAX_NUM) {
                for (int i = start; i < end; i++) {
                    sum += num[i];
                }
            } else {
                int middle = start + (end - start) / 2;
                AddTask leftTask = new AddTask(start, middle);
                AddTask rightTask = new AddTask(middle, end);
                leftTask.fork();
                rightTask.fork();
                int leftSum = leftTask.join();
                int rightSum = rightTask.join();
                sum = leftSum + rightSum;
                System.out.println("start:" + start + ",end:" + end + ",Sum:" + sum);
            }
            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
//      System.out.println(Arrays.toString(num));
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AddTask task = new AddTask(0, num.length);
        forkJoinPool.execute(task);
        Integer sumThis = task.join();
        System.out.println(sumThis);
        System.in.read();
    }
}
