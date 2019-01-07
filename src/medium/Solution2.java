package medium;

import common.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Solution2.java
 * @Description TODO
 * @createTime 2019年01月02日 13:35:00
 */
public class Solution2 {
    /**
     * S=(1/2)*(x1y2*1+x2y3*1+x3y1*1-x1y3*1-x2y1*1-x3y2*1)
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points) {
        double area = 0;
        for (int[] a: points) {
            for (int[] b: points) {
                for (int[] c: points) {
                    area = Math.max(area, 0.5 * Math.abs(a[0] * b[1] + b[0] * c[1] + c[0] * a[1] - a[0] * c[1] - b[0] * a[1] - c[0] * b[1]));
                }
            }
        }

        return area;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();

        treeSumHelper(root, countMap);
        if (countMap.size() == 0) {
            return new int[1];
        }

        int maxCount = Collections.max(countMap.values());
        List<Integer> resList = new ArrayList<Integer>();
        for (Entry<Integer, Integer> entry: countMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                resList.add(entry.getKey());
            }
        }

        int[] resArr = new int[resList.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = resList.get(i);
        }

        return resArr;
    }

    public int treeSumHelper(TreeNode node, HashMap<Integer, Integer> countMap) {
        if (node == null) {
            return 0;
        }

        int sum = node.val;
        if (node.left != null) {
            int sumLeft = treeSumHelper(node.left, countMap);
            sum += sumLeft;
        }
        if (node.right != null) {
            int sumRight = treeSumHelper(node.right, countMap);
            sum += sumRight;
        }

        countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
        return sum;
    }

    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }

        int count = 0;
        int tmp = 0;
        for (int i = 2; i < A.length; i++) {
            if ((A[i] - A[i - 1]) == (A[i - 1] - A[i - 2])) {
                count += ++tmp;
            } else {
                tmp = 0;
            }
        }

        return count;
    }

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> record = new Stack<Integer>();
        for (int i = 0; i < T.length; i++) {
            while (record.size() > 0 && T[record.peek()] < T[i]) {
                int top = record.peek();
                record.pop();
                res[top] = i - top;
            }
            record.push(i);
        }

        return res;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode curr = head.next;
        ListNode pre = head;
        while (curr != null) {
            if (curr.val < pre.val) {
                ListNode next = curr.next;
                ListNode insert = dummyNode.next;
                ListNode temp = dummyNode;
                while (curr.val > insert.val && insert != pre) {
                    temp = insert;
                    insert = insert.next;
                }

                temp.next = curr;
                curr.next = insert;
                pre.next = next;
                curr = next;
            } else {
                pre = curr;
                curr = curr.next;
            }
        }

        return dummyNode.next;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        if (root == null) {
            return res;
        }

        rightHelper(root, res, 1);

        return res;
    }

    public void rightHelper(TreeNode node, List<Integer> list, int depth) {
        if (node == null) {
            return;
        }

        if (depth > list.size()) {
            list.add(node.val);
        }

        rightHelper(node.right, list, depth + 1);
        rightHelper(node.left, list, depth + 1);
    }

    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int len = words.length;
        int[] value = new int[len];
        for (int i = 0; i < len; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                value[i] |= 1 << (tmp.charAt(j) - 'a');
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct)) {
                    maxProduct = words[i].length() * words[j].length();
                }
            }
        }
        return maxProduct;
    }

    public int regionsBySlashes(String[] grid) {
        int [][] g = new int[grid.length*3][grid.length*3];
        int regions = 0;
        for (int i = 0; i < grid.length; i++){      // transform the string to 2D grid array
            for (int j = 0; j < grid.length; j++) {
                if (grid[i].charAt(j) == '/') {
                    g[i * 3][j * 3 + 2] = g[i * 3 + 1][j * 3 + 1] = g[i * 3 + 2][j * 3] = 1;
                }
                if (grid[i].charAt(j) == '\\') {
                    g[i * 3][j * 3] = g[i * 3 + 1][j * 3 + 1] = g[i * 3 + 2][j * 3 + 2] = 1;
                }
            }
        }
        for (int i = 0; i < g.length; i++){      // use the help function to count the regions
            for (int j = 0; j < g.length; j++){
                if (g[i][j] == 0){
                    DeepFirstSearch(g, i, j);
                    regions++;
                }
            }
        }
        return regions;
    }

    public void DeepFirstSearch(int [][]g, int i, int j) {
        if (i >= 0 && j >= 0 && i < g.length && j < g.length && g[i][j] == 0) {
            g[i][j] = 1;
            DeepFirstSearch(g, i - 1, j);
            DeepFirstSearch(g, i + 1, j);
            DeepFirstSearch(g, i, j - 1);
            DeepFirstSearch(g, i, j + 1);
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        List<java.util.Map.Entry<Integer, Integer>> entries = new ArrayList<java.util.Map.Entry<Integer, Integer>>(countMap.entrySet());
        Collections.sort(entries, new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            res.add(entries.get(i).getKey());
        }

        return res;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode t = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = t;

        return head;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(2);
        node.right = new TreeNode(-3);

        int a[] = {73,74,75,71,69,72,76,73};
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};

        ListNode lnode = new ListNode(1);
        lnode.next = new ListNode(2);
        lnode.next.next = new ListNode(3);
        lnode.next.next.next = new ListNode(4);
        lnode.next.next.next.next = new ListNode(5);

        System.out.println(s.oddEvenList(lnode));

    }
}
