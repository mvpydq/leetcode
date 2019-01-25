package medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<NestedInteger>();
    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger curr = stack.peek();
            if (curr.isInteger()) {
                return true;
            }

            stack.pop();
            for (int i = curr.getList().size() - 1; i >= 0; i--) {
                stack.push(curr.getList().get(i));
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[0];
        NestedIntegerImpl nestedInteger = new NestedIntegerImpl(nums);
        System.out.println(nestedInteger.isInteger());
        List<NestedInteger> list = new ArrayList<NestedInteger>();
        //list.add(nestedInteger);
        NestedIterator nestedIterator = new NestedIterator(list);
        System.out.println(nestedIterator.hasNext());
        System.out.println(nestedIterator.next());
    }
}
