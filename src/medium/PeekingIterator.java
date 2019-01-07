package medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName PeekingIterator.java
 * @Description TODO
 * @createTime 2018年11月21日 14:10:00
 */
class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private int current = -1;
    private boolean hasNext = false;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        hasNext = this.iterator.hasNext();
        if (hasNext) {
            current = this.iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return current;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int next = current;
        if (iterator.hasNext()) {
            current = iterator.next();
        } else {
            hasNext = false;
        }

        return next;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }
}
