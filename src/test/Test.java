package test;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2018年11月27日 14:32:00
 */
public class Test implements Runnable {
    private String id;

    public Test(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        //System.out.println("wait lock " + id);
        synchronized (Test.class) {
            try {
                System.out.println("sync lock " + id);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println("finish " + id);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Test t = new Test("id" + i  );
            Thread thread = new Thread(t);
            thread.start();
        }
    }
}
