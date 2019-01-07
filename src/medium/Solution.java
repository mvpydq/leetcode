package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

import common.ListNode;
import common.TreeNode;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2018年11月12日 11:26:00
 */
public class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[] rowMax = new int[grid.length];
        int[] colMax = new int[grid[0].length];

        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            int tmpR = 0;
            for (int j = 0; j < colMax.length; j++) {
                if (grid[i][j] > tmpR) {
                    tmpR = grid[i][j];
                }
            }
            rowMax[i] = tmpR;
        }

        for (int i = 0; i < colMax.length; i++) {
            int tmpC = 0;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] > tmpC) {
                    tmpC = grid[j][i];
                }
            }
            colMax[i] = tmpC;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < colMax.length; j++) {
                int max = Math.min(rowMax[i], colMax[j]);
                sum += max - grid[i][j];
            }
        }

        return sum;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int row = 0, col = -1;
        int minRow = 1, minCol = 0;
        int maxRow = n - 1, maxCol = n - 1;
        int num = 1;
        // 1右+col 2下+row 3左-col 4上-row
        int direction = 1;
        int max = (int) Math.pow(n, 2);
        boolean change = false;
        while (true) {
            switch (direction) {
                case 1:
                    if (col >= maxCol) {
                        direction = 2;
                        maxCol--;
                        change = true;
                    } else {
                        col++;
                    }
                    break;
                case 2:
                    if (row >= maxRow) {
                        direction = 3;
                        maxRow--;
                        change = true;
                    } else {
                        row++;
                    }
                    break;
                case 3:
                    if (col <= minCol) {
                        direction = 4;
                        minCol++;
                        change = true;
                    } else {
                        col--;
                    }
                    break;
                case 4:
                    if (row <= minRow) {
                        direction = 1;
                        minRow++;
                        change = true;
                    } else {
                        row--;
                    }
                    break;
            }

            if (change) {
                change = false;
                continue;
            }

            res[row][col] = num++;
            if (num > max) {
                break;
            }
        }

        return res;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return treeHelper(nums, 0, nums.length - 1);
    }

    public TreeNode treeHelper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int maxIdx = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        TreeNode node = new TreeNode(nums[maxIdx]);
        node.left = treeHelper(nums, start, maxIdx - 1);
        node.right = treeHelper(nums, maxIdx + 1, end);

        return node;
    }

    public int[] countBits(int num) {
        int pow = 1;
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            if (i == 0) {
                res[i] = 0;
            } else {
                res[i] = res[i - pow] + 1;
                if (i == (pow * 2 - 1)) {
                    pow *= 2;
                }
            }
        }

        return res;
    }

    /**
     * https://leetcode.com/problems/subsets/discuss/122645/3ms-easiest-solution-no-backtracking-no-bit-manipulation-no-dfs-no-bullshit
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<>());

        for (int n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<Integer>(res.get(i));
                subset.add(n);
                res.add(subset);
            }
        }

        return res;
    }

    public int minAddToMakeValid(String S) {
        char[] chars = S.toCharArray();
        int left = 0;
        int right = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        return left + right;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfsSearch(graph, 0, res, path);

        return res;
    }

    public void dfsSearch(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int nextNode: graph[node]) {
            path.add(nextNode);
            dfsSearch(graph, nextNode, res, path);
            path.remove(path.size() - 1);
        }
    }

    public void backTrade(String sublist, List<String> res, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(sublist);
        } else {
            if (left > 0) {
                backTrade(sublist + "(", res, left - 1, right);
            }
            if (left < right) {
                backTrade(sublist + ")", res, left, right - 1);
            }
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        backTrade("", res, n, n);

        return res;
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = pruneTree(root.left);
        TreeNode right = pruneTree(root.right);
        if (root.val == 1) {
            root.left = left;
            root.right = right;
        } else {
            if (left == null && right == null) {
                return null;
            } else {
                root.left = left;
                root.right = right;
            }
        }

        return root;
    }

    public int matrixScore(int[][] A) {
        int sum = 0;
        int colLen = A[0].length;
        for (int i = 0; i < A.length; i++) {
            int[] row = A[i];
            if (row[0] == 0) {
                for (int j = 0; j < colLen; j++) {
                    row[j] = row[j] == 0 ? 1 : 0;
                }
            }
        }

        for (int i = 0; i < colLen; i++) {
            int zeroCnt = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 0) {
                    zeroCnt++;
                }
            }

            if (zeroCnt > A.length - zeroCnt) {
                for (int j = 0; j < A.length; j++) {
                    A[j][i] = A[j][i] == 0 ? 1 : 0;
                }
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < colLen; j++) {
                sum += Math.pow(2, colLen - j - 1) * A[i][j];
            }
        }

        return sum;
    }

    /**
     * https://blog.csdn.net/jacky_chenjp/article/details/66477538
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }

        int[] res = new int[len];

        int lastEle = nums[len - 1];
        int k = len - 2;
        for (;k >= 0; k--) {
            if (lastEle > nums[k]) {
                break;
            } else {
                lastEle = nums[k];
                continue;
            }
        }

        if (k < 0) {
            for (int i = 0; i < (len + 1) / 2; i++) {
                swap(nums, i, len - i - 1);
            }
        } else {
            int index = 0;
            for (int i = len - 1; i > k; i--) {
                if (nums[i] > nums[k]) {
                    swap(nums, i, k);
                    index = i;
                    break;
                }
            }

            if (index == 0) {
                swap(nums, k, len - 1);
            }

            for (int i = k+1; i < (k + len + 2) / 2; i++) {
                swap(nums, i, k + len - i);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        int len = nums.length;
        if (nums == null || len == 0) {
            return res;
        }

        exchange(nums, 0, len, res);

        return res;
    }

    public void exchange(int[] nums, int i, int len, List<List<Integer>> res) {
        if (i == len - 1) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add(nums[j]);
            }
            res.add(list);
        } else {
            for (int j = i; j < len; j++) {
                swap(nums, i, j);
                exchange(nums, i + 1, len, res);
                swap(nums, i, j);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inOrderHelper(root, res);

        return res;
    }

    public void inOrderHelper(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        inOrderHelper(node.left, list);
        list.add(node.val);
        inOrderHelper(node.right, list);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        postOrderHelper(root, res);

        return res;
    }

    public void postOrderHelper(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        postOrderHelper(node.left, list);
        postOrderHelper(node.right, list);
        list.add(node.val);
    }

    public List<Integer> postorderTraversalNoRecu(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return res;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }

        }

        return res;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (k == 0) {
            return res;
        }

        Stack<Integer> tmp = new Stack<Integer>();
        combineHelper(n, k, 1, res, tmp);

        return res;
    }

    public void combineHelper(int n, int k, int start, List<List<Integer>> res, Stack<Integer> tmp) {
        if (k == 0) {
            res.add(new ArrayList<>(tmp));
        } else {
            for (int i = start; i <= n; i++) {
                tmp.push(i);
                combineHelper(n, k - 1, i + 1, res, tmp);
                tmp.pop();
            }
        }
    }

    /**
     * https://blog.csdn.net/jiangxiewei/article/details/82227451
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c: nums) {
            b = b ^ c & ~a;
            a = a ^ c & ~b;
        }

        return b;
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<String>();

        Map<Character, Character> pMap = new HashMap<Character, Character>();
        char[] pArr = pattern.toCharArray();
        int pLen = pattern.length();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() != pLen) {
                continue;
            }

            pMap.clear();
            boolean match = true;
            for (int j = 0; j < pLen; j++) {
                if (!pMap.containsKey(pArr[j])) {
                    if (pMap.containsValue(words[i].charAt(j))) {
                        match = false;
                        break;
                    }
                    pMap.put(pArr[j], words[i].charAt(j));
                } else {
                    if (pMap.get(pArr[j]) != words[i].charAt(j)) {
                        match = false;
                        break;
                    }
                }
            }

            if (match) {
                res.add(words[i]);
            }
        }

        return res;
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        findBottomLeftValueHelper(root, 1, map);
        int dep = 0;
        for (java.util.Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getKey() > dep) {
                dep = entry.getKey();
            }
        }

        return map.get(dep);
    }

    public void findBottomLeftValueHelper(TreeNode root, int depth, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            if (!map.containsKey(depth)) {
                map.put(depth, root.val);
            }
            return;
        }

        findBottomLeftValueHelper(root.left, depth + 1, map);
        findBottomLeftValueHelper(root.right, depth + 1, map);
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i != j) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        List<Integer> curr = new ArrayList<Integer>();
        combinationHelper(curr, res, candidates, target, 0);

        return res;
    }

    public void combinationHelper(List<Integer> curr, List<List<Integer>> res, int[] nums, int target, int start) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<Integer>(curr));
        } else {
            for (int i = start; i < nums.length; i++) {
                curr.add(nums[i]);
                combinationHelper(curr, res, nums, target - nums[i], i);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        return diffWaysToComputeHelper(input, map);
    }

    public List<Integer> diffWaysToComputeHelper(String input, Map<String, List<Integer>> map) {
        if (map.containsKey(input)) {
            return map.get(input);
        }

        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0, len = input.length(); i < len; i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                for (Integer l: diffWaysToComputeHelper(input.substring(0, i), map)) {
                    for (Integer r: diffWaysToComputeHelper(input.substring(i + 1, len), map)) {

                        if (ch == '+') {
                            res.add(l + r);
                        } else if (ch == '-') {
                            res.add(l - r);
                        } else {
                            res.add(l * r);
                        }
                    }
                }
            }
        }

        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);

        return res;
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> tmpList = new ArrayList<Integer>();
        kthSmallestHelper(root, tmpList, k);

        return tmpList.get(k - 1);
    }

    public void kthSmallestHelper(TreeNode root, List<Integer> tmpList, int k) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            tmpList.add(root.val);
            return;
        }

        if (tmpList.size() >= k) {
            return;
        }

        kthSmallestHelper(root.left, tmpList, k);
        tmpList.add(root.val);
        kthSmallestHelper(root.right, tmpList, k);
    }

    public String complexNumberMultiply(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int idxAddA = a.indexOf('+');
        int idxAddB = b.indexOf('+');
        int num1 = Integer.valueOf(a.substring(0, idxAddA));
        int num2 = Integer.valueOf(b.substring(0, idxAddB));
        int i1 = Integer.valueOf(a.substring(idxAddA + 1, a.length() - 1));
        int i2 = Integer.valueOf(b.substring(idxAddB + 1, b.length() - 1));

        sb.append(num1 * num2 - i1 * i2);
        sb.append("+");
        sb.append(num1 * i2 + num2 * i1);
        sb.append("i");

        return sb.toString();
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<Integer>();

        if (S == null || S.isEmpty()) {
            return res;
        }

        int[] map = new int[26];
        char[] chars = S.toCharArray();
        TreeMap<Integer, Integer> pairMap = new TreeMap<Integer, Integer>();
        for (int i = 0; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if (map[idx] > 0) {
                System.out.println(chars[i] + "\t" + map[idx] + "\t" + i);
                pairMap.put(map[idx], i + 1);
            } else {
                map[idx] = i + 1;
                pairMap.put(map[idx], i + 1);
            }
        }
        System.out.println(pairMap);
        int start = 0, end = 0;
        for (java.util.Map.Entry<Integer, Integer> entry: pairMap.entrySet()) {
            int tmpStart = entry.getKey();
            int tmpEnd = entry.getValue();
            if (end == 0) {
                start = tmpStart;
                end = tmpEnd;
            } else if (tmpEnd > end && tmpStart <= end) {
                end = tmpEnd;
            } else if (tmpStart > end) {
                res.add(end - start + 1);
                System.out.println(S.substring(start - 1, end));
                start = tmpStart;
                end = tmpEnd;
            }
        }
        res.add(end - start + 1);

        return res;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();

        combinationSum3Helper(curr, res, n, k, 1);

        return res;
    }

    public void combinationSum3Helper(List<Integer> curr, List<List<Integer>> res, int n, int k, int start) {
        if (n < 0 || k < 0) {
            return;
        } else if (n == 0 && k == 0) {
            res.add(new ArrayList<Integer>(curr));
        } else {
            for (int i = start; i <= 9; i++) {
                curr.add(i);
                combinationSum3Helper(curr, res, n - i, k - 1, i + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }

    /**
     * https://blog.csdn.net/qq_38595487/article/details/81163737
     * @param nums
     * @return
     */
    public int[] singleNumber3(int[] nums) {
        int[] re = new int[2];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum ^= nums[i];
        }

        int flag = sum & (~(sum - 1));

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & flag) == 0) {
                re[0] ^= nums[i];
            } else {
                re[1] ^= nums[i];
            }
        }

        return re;
    }

    /**
     * https://www.cnblogs.com/ariel-dreamland/p/9159482.html
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0, len = (int)Math.pow(2, n); i < len; i++) {
            res.add((i >> 1) ^ i);
        }

        return res;
    }

    public String customSortString(String S, String T) {
        int[] map = new int[26];
        List<StringBuilder> sbList = new ArrayList<StringBuilder>();
        for (int i = 0, len = S.length(); i < len; i++) {
            map[S.charAt(i) - 'a'] = i + 1;
            sbList.add(new StringBuilder());
        }

        StringBuilder remain = new StringBuilder();
        for (int i = 0, len = T.length(); i < len; i++) {
            char ch = T.charAt(i);
            if (map[ch - 'a'] > 0) {
                int idx = map[ch - 'a'] - 1;
                sbList.get(idx).append(ch);
            } else {
                remain.append(ch);
            }
        }

        for (StringBuilder sb: sbList) {
            remain.append(sb.toString());
        }

        return remain.toString();
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }

        if (root.val > L) {
            sum += rangeSumBST(root.left, L, R);
        }
        if (root.val < R) {
            sum += rangeSumBST(root.right, L, R);
        }

        return sum;
    }

    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            int[] tmp = board[i];
            for (int j = 0; j < tmp.length; j++) {
                board[i][j] = gameHelper(i, j, board);
            }
        }

        for (int i = 0; i < board.length; i++) {
            int[] tmp = board[i];
            for (int j = 0; j < tmp.length; j++) {
                if (board[i][j] > 10) {
                    if (board[i][j] < 12 || board[i][j] > 13) {
                        board[i][j] = 0;
                    } else {
                        board[i][j] = 1;
                    }
                } else {
                    if (board[i][j] == -3) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    public int gameHelper(int i, int j, int[][] board) {
        int d = Math.max(i - 1, 0);
        int u = Math.min(i + 1, board.length - 1);
        int l = Math.max(j - 1, 0);
        int r = Math.min(j + 1, board[0].length - 1);
        int alive = 0;
        for (int m = d; m <= u; m++) {
            for (int k = l; k <= r; k++) {
                if (m == i && k == j) {
                    continue;
                }

                if (board[m][k] > 0) {
                    alive++;
                }
            }
        }

        if (board[i][j] > 0) {
            return alive + 10;
        } else {
            return alive * -1;
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.isEmpty()) {
            return res;
        }

        List<String> current = new ArrayList<String>();
        partition(res, current, s, 0);

        return res;
    }

    public void partition(List<List<String>> res, List<String> current, String s, int idx) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(current));
        } else {
            for (int i = idx; i < s.length(); i++) {
                String tmp = s.substring(idx, i + 1);
                if (isSym(tmp)) {
                    current.add(tmp);
                    partition(res, current, s, i + 1);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    public boolean isSym(String s) {
        if (s == null || s.length() == 1) {
            return true;
        }

        int sLen = s.length();
        for (int i = 0, len = s.length() / 2; i < len; i++) {
            if (s.charAt(i) != s.charAt(sLen - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        int n = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            int idx = nums[i];
            if (idx > n) {
                idx = idx % n;
            }

            nums[idx - 1] += n;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > n * 2) {
                res.add(i + 1);
            }
        }

        return res;
    }

    public int singleNonDuplicate(int[] nums) {
        return singleHelper(nums, 0, nums.length - 1);
    }

    public int singleHelper(int[] nums, int left, int right) {
        if (right - left <= 0) {
            return nums[left];
        }
        if (right - left == 1) {
            return nums[left] ^ nums[right];
        }
        int mid = (left + right) / 2;
        return singleHelper(nums, left, mid) ^ singleHelper(nums, mid + 1, right);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }

        return grid[m - 1][n - 1];
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);

        return root;
    }

    Map<Integer,List<TreeNode>> cache= new HashMap<Integer, List<TreeNode>>();
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<TreeNode>();

        if (N % 2 == 0) {
            return res;
        }
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        N = N - 1;
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - i);
            for (TreeNode nl: left) {
                for (TreeNode nr: right) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = nl;
                    cur.right = nr;
                    res.add(cur);
                }
            }
        }
        cache.put(N, res);

        return res;
    }

    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == '.') {
                    continue;
                } else {
                    if (i - 1 >= 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j - 1 >= 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 曼哈顿距离 leetcode 789
     * @param ghosts
     * @param target
     * @return
     */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int minDis = Math.abs(ghosts[0][0] - target[0]) + Math.abs(ghosts[0][1] - target[1]);
        for (int i = 1; i < ghosts.length; i++) {
            int temp = Math.abs(ghosts[i][0] - target[0]) + Math.abs(ghosts[i][1] - target[1]);
            if (temp < minDis) {
                minDis = temp;
            }
        }

        int dis = Math.abs(target[0]) + Math.abs(target[1]);
        return dis < minDis;
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                dp[j][j + i] = Math.max(piles[j] - dp[j + 1][j + i], piles[j + i] - dp[j][j + i - 1]);
            }
        }

        return dp[0][n - 1] > 0;
    }

    public int findDuplicate1(int[] nums) {
        int x = 0;
        for (int a : nums) {
            x = x ^ a;
        }

        return x;
    }

    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int slow = nums[0];
        int fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode current = root;
        if (current.val < val) {
            if (current.right == null) {
                current.right = new TreeNode(val);
            } else {
                insertIntoBST(current.right, val);
            }
        } else {
            if (current.left == null) {
                current.left = new TreeNode(val);
            } else {
                insertIntoBST(current.left, val);
            }
        }

        return root;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode last = new ListNode(0);
        last.next = head;
        ListNode current = head;
        ListNode next = head.next;
        head = next;
        while (current != null && current.next != null) {
            last.next = next;
            current.next = next.next;
            next.next = current;
            last = current;
            current = current.next;
            if (current != null) {
                next = current.next;
            }
        }

        return head;
    }

    public TreeNode buildTreeIP(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }

        TreeNode root = buildHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

        return root;
    }

    public TreeNode buildHelper(int[] inOrder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {
        if (inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postOrder[postEnd]);
        int rootPos = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == postOrder[postEnd]) {
                rootPos = i;
                break;
            }
        }

        root.left = buildHelper(inOrder, inStart, rootPos - 1, postOrder, postStart, postStart + (rootPos - inStart - 1));
        root.right = buildHelper(inOrder,rootPos + 1, inEnd, postOrder, postStart + rootPos - inStart, postEnd - 1);

        return root;
    }

    public TreeNode buildTreePI(int[] preorder, int[] inorder) {
        return buildHelperPI(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public TreeNode buildHelperPI(int[] pre, int[] in, int preStart, int inStart, int inEnd) {
        if (preStart > pre.length - 1 || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (root.val == in[i]) {
                index = i;
                break;
            }
        }

        root.left = buildHelperPI(pre, in, preStart + 1, inStart, index - 1);
        root.right = buildHelperPI(pre, in, preStart + index - inStart + 1, index + 1, inEnd);

        return root;
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        largestHelper(root, 0, res);
        return res;
    }

    public void largestHelper(TreeNode node, int depth, List<Integer> res) {
        if (node == null) {
            return;
        }

        if (res.size() <= depth) {
            res.add(node.val);
        } else {
            if (res.get(depth) < node.val) {
                res.set(depth, node.val);
            }
        }

        largestHelper(node.left, depth + 1, res);
        largestHelper(node.right, depth + 1, res);
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            String sp9 = null;
            String sp4 = null;
            String sp5 = "D";
            String sp1 = "M";

            int dev = 1;
            if (num >= 1000) {
                dev = 1000;
            } else if (num >= 100) {
                dev = 100;
                sp9 = "CM";
                sp4 = "CD";
                sp5 = "D";
                sp1 = "C";
            } else if (num >= 10) {
                dev = 10;
                sp9 = "XC";
                sp4 = "XL";
                sp5 = "L";
                sp1 = "X";
            } else {
                sp9 = "IX";
                sp4 = "IV";
                sp5 = "V";
                sp1 = "I";
            }

            int count = num / dev;
            num -= count * dev;
            num = num % dev;
            if (sp9 != null) {
                if (count == 9) {
                    sb.append(sp9);
                    continue;
                } else if (count == 4) {
                    sb.append(sp4);
                    continue;
                }
            }

            if (count >= 5) {
                sb.append(sp5);
                count -= 5;
            }

            for (int i = 0; i < count; i++) {
                sb.append(sp1);
            }
        }

        return sb.toString();
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String s: allowed) {
            String key = s.substring(0, 2);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(s.substring(2));
        }

        return helper(bottom, map);
    }

    public boolean helper(String bottom, Map<String, List<String>> map) {
        if (bottom.length() == 1) {
            return true;
        }

        for (int i = 0; i < bottom.length() - 1; i++) {
            if (!map.containsKey(bottom.substring(i, i + 2))) {
                return false;
            }
        }

        List<String> list = new ArrayList<String>();
        getList(bottom, 0, new StringBuilder(), list, map);
        for (String s: list) {
            if (helper(s, map)) {
                return true;
            }
        }

        return false;
    }

    private void getList(String bottom, int idx, StringBuilder sb, List<String> list, Map<String, List<String>> map) {
        if (idx == bottom.length() - 1) {
            list.add(sb.toString());
            return;
        }

        for (String s: map.get(bottom.substring(idx, idx + 2))) {
            sb.append(s);
            getList(bottom, idx + 1, sb, list, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (stack != null && !stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                list.add(node.val);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return list;
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        flatten(right);
        flatten(left);
        root.right = left;
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }

        curr.right = right;
    }

    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        return merge(l, r);
    }

    private ListNode merge(ListNode l, ListNode r) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l != null && r != null) {
            if (l.val < r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }

        return dummyHead.next;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[nums.length];
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = nums[0];
        right[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i];
            right[len - i - 1] = right[len - i] * nums[len - i - 1];
        }

        res[0] = right[1];
        for (int i = 1; i < len - 1; i++) {
            res[i] = left[i - 1] * right[i + 1];
        }
        res[len - 1] = left[len - 2];
        return res;
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return b[0] - a[0];
                } else {
                    return a[1] - b[1];
                }
            }
        });

        List<int[]> res = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            int[] tmp = people[i];
            res.add(tmp[1], tmp);
        }

        return res.toArray(new int[people.length][]);
    }

    public int sumNumbersBad(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<Long> numList = new ArrayList<Long>();
        treeSumHelper(root, new StringBuilder(), numList);

        int sum = 0;
        for (Long num: numList) {
            sum += num;
        }

        return sum;
    }

    public void treeSumHelper(TreeNode node, StringBuilder numBuilder, List<Long> numList) {
        if (node == null) {
            return;
        }

        numBuilder.append(node.val);
        if (node.left == null && node.right == null) {
            numList.add(Long.valueOf(numBuilder.toString()));
            numBuilder.deleteCharAt(numBuilder.length() - 1);
        } else {
            treeSumHelper(node.left, new StringBuilder(numBuilder), numList);
            treeSumHelper(node.right, new StringBuilder(numBuilder), numList);
        }
    }

    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode n, int s) {
        if (n == null) {
            return 0;
        }

        int val = s * 10 + n.val;
        if (n.right == null && n.left == null) {
            return val;
        }

        return sum(n.left, val) + sum(n.right, val);
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k - 1];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            List<Integer> nextRow = triangle.get(i + 1);
            for (int j = 0, rowLen = row.size(); j < rowLen; j++) {
                int tmp = Math.min(nextRow.get(j), nextRow.get(j + 1)) + row.get(j);
                row.set(j, tmp);
            }
        }

        return triangle.get(0).get(0);
    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] * dp[i - j - 1] + dp[i];
            }
        }

        return dp[n];
    }

    public String frequencySort(String s) {
        TreeMap<Character, Integer> charCount = new TreeMap<Character, Integer>();

        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        List<java.util.Map.Entry<Character, Integer>> list = new ArrayList<java.util.Map.Entry<Character, Integer>>(charCount.entrySet());
        java.util.Collections.sort(list, new java.util.Comparator<Entry<Character, Integer>>() {
            @Override
            public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        StringBuilder res = new StringBuilder();
        for (java.util.Map.Entry<Character, Integer> entry: list) {
            int count = entry.getValue();
            char c = entry.getKey();
            for (int i = 0; i < count; i++) {
                res.append(c);
            }
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        int[] nums = {1,3,3,2,5};

        int[][] matrix = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        //String[] arr = {"XXX", "XXY", "XYX", "XYY", "YXZ"};
        String[] arr = {"ABD","BCE","DEF","FFF"};
        System.out.println(s.frequencySort("Aabb"));
        System.out.println(s.frequencySort("tree"));
        //System.out.println(105 % 1);
    }
}
