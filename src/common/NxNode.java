package common;

import java.util.List;

/**
 * 429
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName NxNode.java
 * @Description TODO
 * @createTime 2018年11月05日 16:31:00
 */
public class NxNode {
    public int val;
    public List<NxNode> children;

    public NxNode() {}

    public NxNode(int _val, List<NxNode> _children) {
        val = _val;
        children = _children;
    }
}
