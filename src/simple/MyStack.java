package simple;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName MyStack.java
 * @Description TODO
 * @createTime 2018年10月31日 14:33:00
 */
public class MyStack {
    LinkedList<Integer> queue;
    public MyStack() {
        queue = new LinkedList<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.push(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.pop();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
