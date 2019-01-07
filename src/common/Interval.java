package common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Interval.java
 * @Description TODO
 * @createTime 2019年01月07日 16:19:00
 */
public class Interval {
    public int start;
    public int end;
    public Interval() { start = 0; end = 0; }
    public Interval(int s, int e) { start = s; end = e; }

    public static List<Interval> getList(int[][] nums) {
        List<Interval> res = new ArrayList<Interval>();

        for (int i = 0; i < nums.length; i++) {
            res.add(new Interval(nums[i][0], nums[i][1]));
        }

        return res;
    }

    @Override
    public String toString() {
        return "Interval{" +
            "start=" + start +
            ", end=" + end +
            '}';
    }
}
