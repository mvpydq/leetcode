package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName MapSum.java
 * @Description TODO
 * @createTime 2018年12月20日 16:01:00
 */
public class MapSum {
    Map<String, Integer> map;

    public MapSum() {
        map = new HashMap<String, Integer>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        if (prefix == null) {
            return 0;
        }

        int sum = 0;
        for (Entry<String, Integer> entry: map.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                sum += entry.getValue();
            }
        }

        return sum;
    }
}
