package common;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName common.ListNode.java
 * @Description TODO
 * @createTime 2018年10月23日 16:22:00
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public void dump() {
        System.out.print(val);
        ListNode current = this;
        while (true) {
            if (current.next == null) {
                break;
            }

            System.out.print("->" + current.next.val);
            current = current.next;
        }
        System.out.println();
    }
}
