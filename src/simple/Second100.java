package simple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import common.NxNode;
import common.TreeNode;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Second100.java
 * @Description TODO
 * @createTime 2018年11月07日 19:37:00
 */
public class Second100 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        int gIndex = 0;
        for (int i = 0; i < s.length && gIndex < g.length; i++) {
            if (s[i] >= g[gIndex]) {
                count++;
                gIndex++;
            }
        }

        return count;
    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return 0;
    }

    public boolean repeatedSubstringPattern(String s) {
        int l = s.length();
        for(int i = l/2; i >= 1; i--) {
            if(l%i==0) {
                int m = l/i;
                String subS = s.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<m;j++) {
                    sb.append(subS);
                }
                if(sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPatternRegex(String str) {
        if(str == null || str.length() < 2) {
            return false;
        }

        boolean result = false;
        for(int i = 1; i <= str.length()/2; i++) {
            if(str.length()%i != 0) {
                continue;
            }
            String regex = "("+str.substring(0,i)+")" + "+";
            result = result | str.matches(regex);
        }
        return result;
    }

    public int numJewelsInStones(String J, String S) {
        if (J == null || J.isEmpty() || S == null || S.isEmpty()) {
            return 0;
        }

        Set<Character> jSet = new HashSet<Character>();
        for (int i = 0, length = J.length(); i < length; i++) {
            jSet.add(J.charAt(i));
        }

        int res = 0;
        for (int i = 0, length = S.length(); i < length; i++) {
            if (jSet.contains(S.charAt(i))) {
                res++;
            }
        }

        return res;
    }

    public int[] sortArrayByParityII(int[] a) {
        int oNum = a.length / 2;
        int jNum = a.length - oNum;
        int[] oArr = new int[oNum];
        int[] jArr = new int[jNum];
        int o = 0;
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                oArr[o++] = a[i];
            } else {
                jArr[j++] = a[i];
            }
        }

        int[] newArr = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) {
                newArr[i] = oArr[--o];
            } else {
                newArr[i] = jArr[--j];
            }
        }
        return newArr;
    }

    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int count = 0;
        while (tmp > 0) {
            if (tmp % 2 == 1) {
                count++;
            }
            tmp /= 2;
        }

        return count;
    }

    public int peakIndexInMountainArray(int[] A) {
        int i = 0, j = A.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (A[mid] >= A[mid - 1] && A[mid] >= A[mid+ 1]) {
                return mid;
            } else if (A[mid] > A[mid + 1]) {
                j = mid - 1;
            }  else {
                i = mid + 1;
            }
        }

        return 0;
    }

    public int findComplement(int num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            if (num % 2 == 1) {
                sb.append(0);
            } else {
                sb.append(1);
            }

            num /= 2;
        }

        String cStr = sb.reverse().toString();

        int res = 0;
        for (int i = 0, len = cStr.length(); i < len; i++) {
            res += (cStr.charAt(i) - '0') * Math.pow(2, len - i - 1);
        }

        return res;
    }

    public boolean judgeCircle(String moves) {
        int rl = 0;
        int ud = 0;
        for (int i = 0, len = moves.length(); i < len; i++) {
            char c = moves.charAt(i);
            switch (c) {
                case 'U':
                    ud++;
                    break;
                case 'D':
                    ud--;
                    break;
                case 'R':
                    rl++;
                    break;
                case 'L':
                    rl--;
                    break;
            }
        }

        if (rl == 0 && ud == 0) {
            return true;
        }

        return false;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        TreeNode node = new TreeNode(0);
        mergeHelper(node, t1, t2);
        return node;
    }

    public void mergeHelper(TreeNode node, TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return;
        }

        int val1 = t1 == null ? 0 : t1.val;
        int val2 = t2 == null ? 0 : t2.val;
        node.val = val1 + val2;
        TreeNode left1 = t1 == null ? null : t1.left;
        TreeNode left2 = t2 == null ? null : t2.left;
        TreeNode right1 = t1 == null ? null : t1.right;
        TreeNode right2 = t2 == null ? null : t2.right;
        if (left1 != null || left2 != null) {
            node.left = new TreeNode(0);
            mergeHelper(node.left, left1, left2);
        }
        if (right1 != null || right2 != null) {
            node.right = new TreeNode(0);
            mergeHelper(node.right, right1, right2);
        }
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<Integer>();

        for (int i = left; i <= right; i++) {
            if (i < 10) {
                res.add(i);
            } else {
                String str = String.valueOf(i);
                if (str.contains("0")) {
                    continue;
                }

                boolean flag = true;
                for (int j = 0, len = str.length(); j < len; j++) {
                    int num = str.charAt(j) - '0';
                    if (i % num != 0) {
                        flag = false;
                    }
                }

                if (flag) {
                    res.add(i);
                }
            }
        }

        return res;
    }

    public List<Integer> postorder(NxNode root) {
        List<Integer> res = new ArrayList<Integer>();

        postHelper(root, res);

        return res;
    }

    public void postHelper(NxNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.children != null && !node.children.isEmpty()) {
            for (int i = 0, size = node.children.size(); i < size; i++) {
                postHelper(node.children.get(i), list);
            }
        }
        list.add(node.val);
    }

    public int[][] transpose(int[][] A) {
        if (A == null || A.length == 0) {
            return null;
        }

        int[][] b = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            int[] row = A[i];
            for (int j = 0; j < row.length; j++) {
                b[j][i] = row[j];
            }
        }

        return b;
    }

    public List<Integer> preorder(NxNode root) {
        List<Integer> res = new ArrayList<Integer>();

        preHelper(root, res);

        return res;
    }

    public void preHelper(NxNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        list.add(node.val);
        if (node.children != null && !node.children.isEmpty()) {
            for (int i = 0, size = node.children.size(); i < size; i++) {
                preHelper(node.children.get(i), list);
            }
        }
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<String>();

        Map<String, Integer> domainMap = new HashMap<String, Integer>();
        for (int i = 0; i < cpdomains.length; i++) {
            String[] tmp = cpdomains[i].split(" ");
            String domain = tmp[1];
            while (true) {
                int index = domain.indexOf(".");
                domainMap.put(domain, domainMap.getOrDefault(domain,0) + Integer.valueOf(tmp[0]));
                domain = domain.substring(index + 1);
                if (index == -1) {
                    break;
                }
            }
        }

        for (java.util.Map.Entry<String, Integer> entry: domainMap.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }

        return res;
    }

    public boolean hasAlternatingBits(int n) {
        int last = -1;
        while (n > 0) {
            int mod = n % 2;
            if (last >= 0 && last == mod) {
                return false;
            }

            last = mod;
            n /= 2;
        }

        return true;
    }

    public int arrayPairSum(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i+=2) {
            sum += nums[i];
        }

        return sum;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val == root.val) {
            return root;
        }

        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    public int findLUSlength(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        if (lenA != lenB) {
            return Math.max(lenA, lenB);
        }
        if (a.equals(b)) {
            return -1;
        } else {
            return lenA;
        }
    }

    public int islandPerimeter(int[][] grid) {
        int sum = 0;

        for (int i = 0; i < grid.length; i++) {
            int[] tmp = grid[i];
            for (int j = 0; j < tmp.length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                sum += 4;

                if (i > 0 && grid[i - 1][j] == 1) {
                    sum--;
                }
                if (j > 0 && grid[i][j - 1] == 1) {
                    sum--;
                }
                if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                    sum--;
                }
                if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                    sum--;
                }
            }
        }

        return sum;
    }

    public int distributeCandies(int[] candies) {
        Set<Integer> kinds = new HashSet<>();
        for (int candy : candies) {
            kinds.add(candy);
        }
        return kinds.size() >= candies.length / 2 ? candies.length / 2 : kinds.size();
    }

    public int projectionArea(int[][] grid) {
        int total = 0;

        int n = grid.length;
        int colMax = 0;
        int rowMax = 0;
        for (int i = 0; i < n; i++) {
            colMax = 0;
            rowMax = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    total++;
                }

                colMax = Math.max(grid[i][j], colMax);
                rowMax = Math.max(grid[j][i], rowMax);
            }
            total += rowMax + colMax;
        }

        return total;
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) {
            root = root.right;
            return trimBST(root, L, R);
        } else if (root.val > R) {
            root = root.left;
            return trimBST(root, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                map.put(nums2[i], i);
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            int start = map.get(nums1[i]);
            res[i] = -1;
            for (int j = start + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }

        return res;
    }

    public int binaryGap(int N) {
        int max = 0;
        int last = 0;
        int curr = 0;
        while (N > 0) {
            curr ++;
            if (N % 2 == 1) {
                if (last > 0) {
                    int tmp = curr - last;
                    if (tmp > max) {
                        max = tmp;
                    }
                }

                last = curr;
            }

            N = N / 2;
        }

        return max;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Double> avg = new ArrayList<Double>();

        levelHelper(root, list, 1);

        for (List<Integer> subList: list) {
            double sum = 0;
            for (int i = 0; i < subList.size(); i++) {
                sum += subList.get(i);
            }
            avg.add(sum / subList.size());
        }

        return avg;
    }

    public void levelHelper(TreeNode node, List<List<Integer>> list, int depth) {
        if (node == null) {
            return;
        }
        if (depth > list.size()) {
            list.add(new ArrayList<Integer>());
        }

        list.get(depth - 1).add(node.val);
        levelHelper(node.left, list, depth + 1);
        levelHelper(node.right, list, depth + 1);
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < n - 1; i++) {
            int col = i + 1;
            int row = 1;
            while (col < n && row < m) {
                if (matrix[row][col] != matrix[row - 1][col - 1]) {
                    return false;
                }
                col++;
                row++;
            }
        }

        for (int i = 1; i < m - 1; i++) {
            int col = 1;
            int row = i + 1;
            while (col < n && row < m) {
                if (matrix[row][col] != matrix[row - 1][col - 1]) {
                    return false;
                }
                col++;
                row++;
            }
        }

        return true;
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();

        leafHelper(root1, list1);
        leafHelper(root2, list2);

        return list1.equals(list2);
    }

    public void leafHelper(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
        } else {
            leafHelper(node.left, list);
            leafHelper(node.right, list);
        }
    }

    public int countPrimeSetBits(int L, int R) {
        int res = 0;
        for (int i = L; i <= R; i++) {
            int count = 0;
            for (int n = i; n > 0; n >>= 1) {
                count += n & 1;
            }
            if (count == 2 || count == 3 || count == 5 || count == 7
                || count == 11 || count == 13 || count == 17 || count == 19
                || count == 23 || count == 29 || count == 31 || count == 37) {
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Second100 s = new Second100();
        int[] nums = {18,29,38,59,98,100,99,98,90};
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        //t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(3);

        TreeNode t2 = new TreeNode(1);
        //t2.left = new TreeNode(1);
        t2.right = new TreeNode(2);
        //t2.left.right = new TreeNode(4);
        t2.right.right = new TreeNode(3);

        String[] domains = {"9001 discuss.leetcode.com"};

        System.out.println(s.countPrimeSetBits(977581, 983119));
    }
}
