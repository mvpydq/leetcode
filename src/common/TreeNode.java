package common;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName TreeNode.java
 * @Description TODO
 * @createTime 2018年10月24日 20:25:00
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + "\nleft:" + left + "\nright:" + right + "\n";
    }

    public void dump() {
        System.out.println(toString());
    }
}
