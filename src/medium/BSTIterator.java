package medium;

import common.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BSTIterator {
    Stack<TreeNode> stack =  null ;
    TreeNode current = null ;

    public BSTIterator(TreeNode root) {
        current = root;
        stack = new Stack<> ();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || current != null;
    }

    /** @return the next smallest number */
    public int next() {
        while (current != null) {
            stack.push(current);
            current = current.left ;
        }
        TreeNode t = stack.pop() ;
        current = t.right ;
        return t.val ;
    }
}
