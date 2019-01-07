package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName CrossPring.java
 * @Description TODO
 * @createTime 2019年01月04日 14:59:00
 */
public class CrossPrint {
    private volatile AtomicInteger num = new AtomicInteger(0);

    private ReentrantLock lock = new ReentrantLock();

    public class MyThread implements Runnable {
        private int mod;

        public MyThread(int mod) {
            this.mod = mod;
        }

        @Override
        public void run() {
            try {
                while (num.get() < 100) {
                    lock.lock();
                    if (num.get() % 2 == mod) {
                        System.out.println("thread " + mod + " print " + num.getAndIncrement());
                    }
                    lock.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CrossPrint c = new CrossPrint();
        MyThread t1 = c.new MyThread(0);
        MyThread t2 = c.new MyThread(1);
        new Thread(t1).start();
        new Thread(t2).start();
    }
}
