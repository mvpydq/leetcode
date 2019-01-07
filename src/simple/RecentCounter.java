package simple;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName RecentCounter.java
 * @Description TODO
 * @createTime 2018年11月22日 13:44:00
 */
public class RecentCounter {
    private List<Integer> pingList = new ArrayList<Integer>();

    public RecentCounter() {
    }

    public int ping(int t) {
        pingList.add(t);
        int left = t - 3000;
        int min = pingList.get(0);
        if (min >= left) {
            return pingList.size();
        } else {
            while (true) {
                if (pingList.get(0) >= left) {
                    break;
                } else {
                    pingList.remove(0);
                }
            }
        }

        return pingList.size();
    }

    public static void main(String[] args) {
        RecentCounter rc = new RecentCounter();
        System.out.println(rc.ping(825));
        System.out.println(rc.ping(2295));
        System.out.println(rc.ping(4131));
        System.out.println(rc.ping(5455));
        System.out.println(rc.ping(5884));
        System.out.println(rc.ping(5975));
    }
}
