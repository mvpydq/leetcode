package medium;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImpl implements NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    public NestedIntegerImpl(int val) {
        this.val = val;
    }

    public NestedIntegerImpl(int[] vals) {
        list = new ArrayList<NestedInteger>();
        for (int num: vals) {
            list.add(new NestedIntegerImpl(num));
        }
    }

    @Override
    public boolean isInteger() {
        return list == null;
    }

    @Override
    public Integer getInteger() {
        return val;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }
}
