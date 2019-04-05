package cn.zhy.customerAndProduct;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test02
 * @Description 写一个固定容量同步容器, 拥有put和get方法, 以及 getout方法,
 * 能够支持2个生产者线程以及10个消费者程的阻塞调用使用
 * 创建2个专属线程条件  一个只存放消费者线程 customer
 * 一个只存放producer 线程，
 * 在生产满的时候唤醒消费者线程消费
 * 当集合空的时候唤醒生产者线程起来消费
 * @Author zhy
 * @Date 2019/4/5
 */
public class Test02 {

    private LinkedList<Object> lists = new LinkedList<>();

    final int max = 10;

    int count = 0;

    private Lock lock = new ReentrantLock(true);
    private Condition customer = lock.newCondition();
    private Condition producer = lock.newCondition();

    private Object get() {
        Object o = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                customer.await();
            }
            count--;
            o = lists.removeFirst();
            producer.signalAll();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return o;
    }

    private void put(Object o) {
        try {
            lock.lock();
            while (lists.size() == max) {
                producer.await();
            }
            count++;
            lists.add(o);
            customer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public static void main(String[] args) {
        Test02 t = new Test02();
//        customer
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println("我消费了" + t.get());
                }
            }, "customer" + "*" + i).start();
        }

//    producer
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    t.put(Thread.currentThread().getName() + "创建的 " + j);
                }
            }, "producer" + "*" + i).start();
        }
    }
}
