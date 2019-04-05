package cn.zhy.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test01
 * @Description 实现方式1 volatile关键字
 * ー个容器,提供两个方法,add,size写两个线程,
 * 线程1添加10个元素到容器中,线程2实现监控元素的个数,当个数到5个时,线程2给出提示并结束
 * @Author zhy
 * @Date 2019/4/4
 */
public class Test01 {
    volatile List<Object> lists = new ArrayList<>();
    void adds(Object object) {
        lists.add(object);
    }

    int size() {
        return lists.size();

    }

    public static void main(String[] args) {
        Test01 t = new Test01();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                t.adds(new Object());
                System.out.println(t.size());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (t.size() == 5) {
                    System.out.println("daole");
                    break;
                }
            }
        }).start();

    }
}
