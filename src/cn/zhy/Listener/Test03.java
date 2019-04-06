package cn.zhy.Listener;

/**
 * @ClassName Test03
 * @Description 实现方式2 wait And notify
 * wait 会释放锁  notify 不会释放锁  且wait 必须写在synchronized 块中
 * ー个容器,提供两个方法,add,size写两个线程,
 * 线程1添加10个元素到容器中,线程2实现监控元素的个数,当个数到5个时,线程2给出提示并结束
 * @Author zhy
 * @Date 2019/4/4
 */
public class Test03 {
    //    volatile List<Object> lists = new ArrayList<>();
    int count = 0;

    void adds() {
        count++;
    }

    int size() {
        return count;

    }

    public static void main(String[] args) {
        Test03 t = new Test03();

        new Thread(() -> {
            synchronized (t) {
                try {
//                    监听线程启动，并且此时进入了wait状态，并且释放了锁资源
                    t.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("daole");
//                打印完提示信息，并唤醒添加元素的线程
                t.notify();
                try {
//                    释放锁，因为监听线程的目的已经实现，为了节约资源让其等待1s后关闭
                    t.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();


        new Thread(() -> {
            synchronized (t) {
                for (int i = 1; i <= 10; i++) {
                    t.adds();
//                    判断集合中的元素个数，当到达5时 唤醒监听线程并且进入wait状态并且释放锁。
                    if (t.size() == 5) {
                        t.notify();
                        try {
                            t.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(t.size());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
