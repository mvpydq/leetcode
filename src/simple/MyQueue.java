package simple;

import java.util.Stack;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName MyQueue.java
 * @Description TODO
 * @createTime 2018年10月31日 14:49:00
 */
public class MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.isEmpty()) {
            for (int i = 0, size = stack1.size(); i < size; i++) {
                stack2.add(stack1.pop());
            }
        }

        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stack2.isEmpty()) {
            for (int i = 0, size = stack1.size(); i < size; i++) {
                stack2.add(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack2.isEmpty() && stack1.isEmpty();
    }
}
