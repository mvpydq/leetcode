package simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName MinStack.java
 * @Description TODO
 * @createTime 2018年10月27日 14:56:00
 */
class MinStack {
    List<Integer> data = new ArrayList<Integer>();
    int min = 0;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if (data.isEmpty() || x < min) {
            min = x;
        }
        data.add(0, x);
    }

    public void pop() {
        data.remove(0);
        min = Integer.MAX_VALUE;
        for (int i = 0, size = data.size(); i < size; i++) {
            if (data.get(i) < min) {
                min = data.get(i);
            }
        }
    }

    public int top() {
        return data.get(0);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}
