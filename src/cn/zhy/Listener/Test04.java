package cn.zhy.Listener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test04
 * @Description 实现方式3 使用门闩 Latch 替代 wait notify
 * CountDownLatch  不涉及锁定，当count的值为0时当前线程继续
 * ー个容器,提供两个方法,add,size写两个线程,
 * 线程1添加10个元素到容器中,线程2实现监控元素的个数,当个数到5个时,线程2给出提示并结束
 * @Author zhy
 * @Date 2019/4/4
 */
public class Test04 {
    //    volatile List<Object> lists = new ArrayList<>();
     int count = 0;

     void adds() {
        count++;
    }

     int size() {
        return count;

    }

    public static void main(String[] args) {
        Test04 t = new Test04();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("daole");
        }).start();


        new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    t.adds();
                    if (t.size() == 5) {
                        countDownLatch.countDown();
                    }
                    System.out.println(t.size());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }
}
