package cn.zhy.customerAndProduct;

import java.util.LinkedList;

/**
 * @ClassName Test01
 * @Description 写一个固定容量同步容器, 拥有put和get方法, 以及 getout方法,
 * 能够支持2个生产者线程以及10个消费者程的阻塞调用使用
 * 实现方式1 waitfnnotify/ notify来实现
 * @Author zhy
 * @Date 2019/4/5
 */
public class Test01 {
    final private LinkedList<Object> list = new LinkedList<>();
    final int PRODUCT_MAX = 10;
    static int count = 0;

    synchronized Object get() {
        while (list.size() == 0) {
            try {
                this.wait(1000);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object o = list.removeFirst();
        count--;
        this.notifyAll();
        return o;
    }

    synchronized void put(Object o) {
        while (list.size() == PRODUCT_MAX) {
            try {
                this.wait(1000);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(o);
        count++;
        this.notifyAll();
    }

    public static void main(String[] args) {
        Test01 t = new Test01();
//        创建消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println("我消费了" + t.get());
                }
            }, "cus" + "*" + i).start();
        }

//        创建生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    t.put(Thread.currentThread().getName() + "创建的 " + j);
                }
            }, "pro" + "*" + i).start();
        }

    }
}
