package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import common.*;

/**
 * 从第一题开始
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName FromOne.java
 * @Description TODO
 * @createTime 2018年10月23日 14:37:00
 */
public class First100 {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == tmp) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }

        return res;
    }

    public int reverse(int x) {
        if (x / 10 == 0) {
            return x;
        }

        List<Integer> numList = new ArrayList<Integer>();
        while (true) {
            int mod = x % 10;
            x = x / 10;
            if (x == 0) {
                if (mod != 0) {
                    numList.add(mod);
                }
                break;
            }
            numList.add(mod);
        }

        int val = 0;
        for (int i = 0, size = numList.size(); i < size; i++) {
            val += numList.get(i) * Math.pow(10, size - i - 1);
        }

        int max = (int)Math.pow(2, 31) - 1;
        int min = (int)Math.pow(2, 31) * -1;
        if (val > max || val < min) {
            return 0;
        }

        return val;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        List<Integer> numList = new ArrayList<Integer>();
        while (true) {
            int mod = x % 10;
            x = x / 10;
            if (x == 0) {
                if (mod != 0) {
                    numList.add(mod);
                }
                break;
            }
            numList.add(mod);
        }

        for (int i = 0, j = numList.size() - 1; i < j; i ++, j --) {
            if (!numList.get(i).equals(numList.get(j))) {
                return false;
            }
        }

        return true;
    }

    public int romanToInt(String s) {
        int num = 0;

        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            char next = i < charArr.length - 1 ? charArr[i + 1] : 0;
            switch (charArr[i]) {
                case 'I':
                    if (next == 'V') {
                        num += 4;
                        i ++;
                    } else if (next == 'X') {
                        num += 9;
                        i ++;
                    } else {
                        num += 1;
                    }
                    break;
                case 'V':
                    num += 5;
                    break;
                case 'X':
                    if (next == 'L') {
                        num += 40;
                        i ++;
                    } else if (next == 'C') {
                        num += 90;
                        i ++;
                    } else {
                        num += 10;
                    }
                    break;
                case 'L':
                    num += 50;
                    break;
                case 'C':
                    if (next == 'D') {
                        num += 400;
                        i ++;
                    } else if (next == 'M') {
                        num += 900;
                        i ++;
                    } else {
                        num += 100;
                    }
                    break;
                case 'D':
                    num += 500;
                    break;
                case 'M':
                    num += 1000;
                    break;
                default:
                    continue;
            }
        }

        return num;
    }

    public String longestCommonPrefix(String[] strs) {
        String ret = "";
        if (strs == null || strs.length == 0) {
            return ret;
        }

        if (strs.length == 1) {
            return strs[0];
        }

        char[] charArr = strs[0].toCharArray();
        for (int i = 0; i < charArr.length; i ++) {
            for (int j = 1; j < strs.length; j ++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != charArr[i]) {
                    return ret;
                }
            }

            ret += charArr[i];
        }

        return ret;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i ++) {
            char need = 0;
            switch (charArr[i]) {
                case '[':
                case '{':
                case '(':
                    stack.push(charArr[i]);
                    break;
                case ']':
                    need = '[';
                    break;
                case ')':
                    need = '(';
                    break;
                case '}':
                    need = '{';
                    break;
            }
            if (need != 0) {
                if (stack.isEmpty()) {
                    return false;
                }

                char tmp = stack.pop();
                if (tmp != need) {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode mergeList = null;
        if (l1.val > l2.val) {
            mergeList = new ListNode(l2.val);
            l2 = l2.next;
        } else {
            mergeList = new ListNode(l1.val);
            l1 = l1.next;
        }

        ListNode current = mergeList;
        while (true) {
            if (l1 == null) {
                current.next = l2;
                break;
            }

            if (l2 == null) {
                current.next = l1;
                break;
            }

            if (l1.val > l2.val) {
                current.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                current.next = new ListNode(l1.val);
                l1 = l1.next;
            }

            current = current.next;
        }

        return mergeList;
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }

            nums[index] = nums[i];
            index ++;
        }

        return index;
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }

            nums[index] = nums[i];
            index ++;
        }

        return index;
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")) {
            return 0;
        }

        int pos = -1;

        int needLen = needle.length();
        for (int i = 0, size = haystack.length(); i < size; i ++) {
            if ((size - i) >= needLen) {
                int j = i;
                for (; j < i + needLen; j++) {
                    if (needle.charAt(j - i) != haystack.charAt(j)) {
                        break;
                    }
                }

                if (j == i + needLen) {
                    return i;
                }
            }
        }

        return pos;
    }

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i ++) {
            if (target == nums[i]) {
                return i;
            }

            if (target < nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    public String countAndSay(int n) {
        String[] countArr = new String[30];
        countArr[0] = "1";

        for (int i = 1; i < 30; i ++) {
            char[] tmp = countArr[i - 1].toCharArray();
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (int j = 1; j < tmp.length; j ++) {
                if (tmp[j] != tmp[j - 1]) {
                    sb.append(j - index);
                    sb.append(tmp[j - 1]);
                    index = j;
                }
            }

            sb.append(tmp.length - index);
            sb.append(tmp[tmp.length - 1]);
            countArr[i] = sb.toString();
        }

        return countArr[n - 1];
    }

    public int maxSubArrayDP(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int sum = dp[0];

        for (int i = 1; i < nums.length; i ++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }

            if (dp[i] > sum) {
                sum = dp[i];
            }
        }

        return sum;
    }

    public int maxSubArrayFenZhi(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        return sumHelper(nums, 0, nums.length - 1);
    }

    public int sumHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        if (left > right) {
            return Integer.MIN_VALUE;
        }

        int mid = (left + right) / 2;
        int leftMax = sumHelper(nums, 0, mid - 1);
        int rightMax = sumHelper(nums, mid + 1, right);
        int sum = nums[mid];
        int tmp = nums[mid];
        for (int i = mid - 1; i >= left; i --) {
            tmp += nums[i];
            if (tmp > sum) {
                sum = tmp;
            }
        }

        tmp = sum;
        for (int i = mid + 1; i <= right; i ++) {
            tmp += nums[i];
            if (tmp > sum) {
                sum = tmp;
            }
        }

        return Math.max(Math.max(rightMax, leftMax), sum);
    }

    public int lengthOfLastWord(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        String[] tmp = s.trim().split(" ");
        return tmp[tmp.length - 1].length();
    }

    public int[] plusOne(int[] digits) {
        boolean flag = true;
        for (int i= digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }

            digits[i] = 0;
        }

        int[] ret = new int[digits.length + 1];
        System.arraycopy(digits, 0, ret, 1, digits.length);
        ret[0] = 1;

        return ret;
    }

    public String addBinary(String a, String b) {
        char[] charArr1 = a.toCharArray();
        char[] charArr2 = b.toCharArray();

        int i = charArr1.length - 1;
        int j = charArr2.length - 1;

        int extAdd = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while (true) {
            if (i < 0 && j < 0) {
                break;
            }

            int add1 = i >= 0 ? charArr1[i] - '0' : 0;
            int add2 = j >= 0 ? charArr2[j] - '0' : 0;
            int tmp = add1 + add2 + extAdd;
            if (tmp >= 2) {
                extAdd = 1;
            } else {
                extAdd = 0;
            }

            stack.push(tmp % 2);

            i--;
            j--;
        }

        if (extAdd > 0) {
            stack.push(1);
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.toString();
    }

    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int half = x / 2;
        for (int i = 1; i <= half; i++) {
            long tmp = (long)i * i;
            if (tmp == x) {
                return i;
            }

            if (tmp > x) {
                return i - 1;
            }
        }

        return half;
    }

    public int climbStairs(int n) {
        int[] cache = new int[10000000];
        return climbHelper(n, cache);
    }

    public int climbHelper(int n, int[] cache) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int res1 = cache[n - 1];
        int res2 = cache[n - 2];
        if (res1 == 0) {
            res1 = climbStairs(n - 1);
            cache[n - 1] = res1;
        }
        if (res2 == 0) {
            res2 = climbStairs(n - 2);
            cache[n - 2] = res2;
        }

        return res1 + res2;
    }

    public int climbStairsWithoutRecu(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }

        int[] res = new int[n + 1];
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }

        return res[n];
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        while (true) {
            if (current == null || current.next == null) {
                break;
            }

            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (true) {
            if (i < 0 && j < 0) {
                break;
            }

            if (i < 0) {
                nums1[index--] = nums2[j--];
                continue;
            }
            if (j < 0) {
                nums1[index--] = nums1[i--];
                continue;
            }

            int num1 = nums1[i];
            int num2 = nums2[j];
            if (num1 > num2) {
                nums1[index] = num1;
                i --;
            } else {
                nums1[index] = num2;
                j --;
            }

            index--;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return treeHelper(root.left, root.right);
    }

    public boolean treeHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val == right.val && treeHelper(left.left, right.right) && treeHelper(left.right, right.left)) {
            return true;
        }

        return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depthL = root.left == null ? 0 : maxDepth(root.left);
        int depthR = root.right == null ? 0 : maxDepth(root.right);

        return Math.max(depthL, depthR) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        if (root == null) {
            return resList;
        }

        Map<Integer, List<Integer>> valMap = new TreeMap<Integer, List<Integer>>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int val1 = o1.intValue();
                int val2 = o2.intValue();
                if (val1 < val2) {
                    return 1;
                } else if (val1 == val2) {
                    return 0;
                }

                return -1;
            }
        });
        depthHelper(root, 0, valMap);

        for (Entry<Integer, List<Integer>> entry: valMap.entrySet()) {
            resList.add(entry.getValue());
        }

        return resList;
    }

    public void depthHelper(TreeNode node, int depth, Map<Integer, List<Integer>> valMap) {
        if (node == null) {
            return;
        }

        if (!valMap.containsKey(depth)) {
            valMap.put(depth, new ArrayList<Integer>());
        }

        valMap.get(depth).add(node.val);
        depthHelper(node.left, depth + 1, valMap);
        depthHelper(node.right, depth + 1, valMap);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return sortHelper(nums, 0, nums.length - 1);
    }

    public TreeNode sortHelper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (end + start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortHelper(nums, start, mid - 1);
        node.right = sortHelper(nums, mid + 1, end);

        return node;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int depthL = maxDepth(root.left);
        int depthR = maxDepth(root.right);
        if (Math.abs(depthL - depthR) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.right == null && root.left == null) {
            return root.val == sum;
        }

        boolean hasLeft = hasPathSum(root.left, sum - root.val);
        boolean hasRight = hasPathSum(root.right, sum - root.val);

        return hasLeft || hasRight;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        if (numRows <= 0) {
            return resList;
        }

        List<Integer> first = new ArrayList<Integer>();
        first.add(1);
        resList.add(first);

        for (int i = 1; i < numRows; i++) {
            List<Integer> last = resList.get(i - 1);
            List<Integer> current = new ArrayList<Integer>();
            current.add(1);
            for (int j = 1, size = last.size(); j < size; j++) {
                current.add(last.get(j) + last.get(j - 1));
            }
            current.add(1);
            resList.add(current);
        }

        return resList;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> resList = new ArrayList<Integer>();
        if (rowIndex < 0) {
            return resList;
        }

        for (int i = 0; i <= rowIndex; i++) {
            resList.add(0, 1);
            for (int j = 1; j < resList.size() - 1; j++) {
                resList.set(j, resList.get(j) + resList.get(j + 1));
            }
        }

        return resList;
    }

    public int maxProfitOnce(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int res = 0;
        int[] profit = new int[prices.length];
        profit[0] = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < minPrice) {
                minPrice = prices[i - 1];
            }

            profit[i] = Math.max(prices[i] - minPrice, profit[i - 1]);
            if (profit[i] > res) {
                res = profit[i];
            }
        }

        return res;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int profit = 0;
        int buyIndex = 0;
        int saleIndex = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= prices[buyIndex] || prices[i] < prices[saleIndex]) {
                profit += prices[saleIndex] - prices[buyIndex];
                saleIndex = i;
                buyIndex = i;
            }
            if (prices[i] >= prices[saleIndex]) {
                saleIndex = i;
            }
        }

        profit += prices[saleIndex] - prices[buyIndex];

        return profit;
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        char[] chars = s.toLowerCase().toCharArray();
        for (int i = 0, j = chars.length - 1; i < j;) {
            boolean flag = false;
            if (!(chars[i] >= 'a' && chars[i] <= 'z') && !(chars[i] >= '0' && chars[i] <= '9')) {
                i++;
                flag = true;
            }
            if (!(chars[j] >= 'a' && chars[j] <= 'z') && !(chars[j] >= '0' && chars[j] <= '9')) {
                j--;
                flag = true;
            }
            if (flag) {
                continue;
            }

            if (chars[i] != chars[j]) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        if (head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (true) {
            if (slow == null || fast == null) {
                return false;
            }
            if (slow == fast) {
                return true;
            }
            if (slow.next == null || fast.next == null) {
                return false;
            }

            fast = fast.next.next;
            slow = slow.next;
        }
    }

    public int singleNumber(int[] nums) {
        int x = 0;
        for (int a : nums) {
            x = x ^ a;
        }

        return x;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        if (headA.val == headB.val) {
            return headA;
        }

        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }

    public String convertToTitle(int n) {
        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<Character> titleList = new ArrayList<Character>();

        while (n != 0) {
            int m = n % 26;
            if (m > 0) {
                titleList.add(0, a.charAt(m - 1));
                n = (n - m) / 26;
            } else {
                titleList.add(0, 'Z');
                n = (n - 1) / 26;
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0, size = titleList.size(); i < size; i++) {
            s.append(titleList.get(i));
        }
        return s.toString();
    }

    public int titleToNumber(String s) {
        int res = 0;

        for (int i = 0, size = s.length(); i < size; i++) {
            res += (s.charAt(i) - 'A' + 1) * Math.pow(26, size - i - 1);
        }

        return res;
    }

    public int trailingZeroes(int n) {
        int num = 0;

        while (n > 1) {
            n /= 5;
            num += n;
        }

        return num;
    }

    /**
     * 摩尔投票法
     * 算法在局部变量中定义一个序列元素(m)和一个计数器(i)，初始化的情况下计数器为0. 算法依次扫描序列中的元素，当处理元素x的时候，如果计数器为0，那么将x赋值给m，然后将计数器(i)设置为1，如果计数器不为0，那么将序列元素m和x比较，如果相等，那么计数器加1，如果不等，那么计数器减1。处理之后，最后存储的序列元素(m)，就是这个序列中最多的元素。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int counter = 0;
        int x = 0;

        for (int i = 0; i < nums.length; i++) {
            if (counter == 0) {
                x = nums[i];
                counter++;
            } else {
                if (x == nums[i]) {
                    counter++;
                } else {
                    counter--;
                }
            }
        }

        return x;
    }

    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int exchange = nums[nums.length - 1];
            for (int j = nums.length - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = exchange;
        }
    }

    public void rotate1(int[] nums, int k) {
        if(k%nums.length==0) {
            return;
        }

        int index = 0;
        int prev = nums[0];
        boolean isEvenLen = nums.length%2==0;
        int startIndex = 0;
        for(int i = 0; i<nums.length; i++) {
            int nextNumIndex = (index+k)%nums.length;
            int temp = nums[nextNumIndex];
            nums[nextNumIndex] = prev;
            prev = temp;
            index = nextNumIndex;

            if(isEvenLen && index==startIndex) {
                index++;
                startIndex = index;
                prev = nums[index];
            }
        }
    }

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result += (n & 1);
            n >>= 1;
        }
        return result;
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                count++;
            }

            n >>= 1;
        }

        return count;
    }

    //public int rob(int[] nums) {
    //
    //}

    public boolean isHappy(int n) {
        Set<Integer> dataSet = new HashSet<Integer>();
        while(true) {
            int sum = 0;
            while (n != 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (sum == 1) {
                return true;
            }

            if (dataSet.contains(sum)) {
                break;
            }
            dataSet.add(sum);
            n = sum;
        }

        return false;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode current = new ListNode(0);
        current.next = head;
        while (current.next != null) {
            if (current.next.val == val) {
                if (current.next == head) {
                    head = current.next.next;
                }
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public int countPrimes(int n) {
        int count = 0;

        boolean[] noPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (noPrimes[i] == false) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    noPrimes[i * j] = true;
                }
            }
        }

        return count;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> charMap = new HashMap<Character, Character>();
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        for (int i = 0; i < charsS.length; i++) {
            if (charMap.containsKey(charsS[i])) {
                if (charMap.get(charsS[i]) != charsT[i]) {
                    return false;
                }
            } else {
                if (charMap.values().contains(charsT[i])) {
                    return false;
                }
                charMap.put(charsS[i], charsT[i]);
            }
        }

        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode start = new ListNode(0);
        start.next = head;
        ListNode current = head.next;
        ListNode next = current.next;
        while (current != null) {
            if (head == start.next) {
                head.next = null;
            }

            current.next = head;
            head = current;
            if (next == null) {
                break;
            }
            current = next;
            next = next.next;
        }
        return head;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] moneys = new int[nums.length + 1];
        moneys[0] = 0;
        moneys[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            moneys[i + 1] = Math.max(moneys[i], moneys[i - 1] + nums[i]);
        }

        return moneys[nums.length];
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!indexMap.containsKey(nums[i])) {
                indexMap.put(nums[i], i);
            } else {
                if ((i - indexMap.get(nums[i])) <= k) {
                    return true;
                } else {
                    indexMap.put(nums[i], i);
                }
            }
        }

        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public boolean isPowerOfTwo(int n) {
        for (int i = 0; i <= n / 2; i++) {
            long tmp = (long)Math.pow(2, i);
            if (tmp == n) {
                return true;
            }

            if (tmp > n) {
                return false;
            }
        }

        return false;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        if (head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode recv = reverseList(slow);
        head.dump();
        recv.dump();
        while (recv != null) {
            if (recv.val != head.val) {
                return false;
            }
            recv = recv.next;
            head = head.next;
        }

        return true;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if(root.val > Math.max(p.val,q.val)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if(root.val < Math.min(p.val, q.val)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    public void deleteNode(ListNode node) {
        if (node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    public boolean isAnagram(String s, String t) {
        if ((s == null || t == null) || (s.length() != t.length())) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        HashMap<Character, Integer> cMap = new HashMap<Character, Integer>();
        for (int i = 0, size = s.length(); i < size; i++) {
            char cS = s.charAt(i);
            char cT = t.charAt(i);
            if (cMap.containsKey(cS)) {
                cMap.put(cS, cMap.get(cS) + 1);
            } else {
                cMap.put(cS, 1);
            }

            if (cMap.containsKey(cT)) {
                cMap.put(cT, cMap.get(cT) - 1);
            } else {
                cMap.put(cT, -1);
            }

            if (cMap.get(cS) == 0) {
                cMap.remove(cS);
            }
            if (cMap.containsKey(cT) && cMap.get(cT) == 0) {
                cMap.remove(cT);
            }
        }

        if (cMap.isEmpty()) {
            return true;
        }

        return false;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();

        pathHelper(root, new ArrayList<Integer>(), res);

        return res;
    }

    public void pathHelper(TreeNode node, List<Integer> path, List<String> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0, size = path.size(); i < size; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(node.val);
            list.add(sb.toString());
        } else {
            path.add(node.val);
            pathHelper(node.left, new ArrayList<Integer>(path), list);
            pathHelper(node.right, new ArrayList<Integer>(path), list);
        }
    }

    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }

        if (num % 9 == 0) {
            return 9;
        }

        return num % 9;
    }

    public boolean isUgly(int num) {
        while (num != 0 && num % 2 == 0) {
            num /= 2;
        }
        while (num != 0 && num % 3 == 0) {
            num /= 3;
        }
        while (num != 0 && num % 5 == 0) {
            num /= 5;
        }

        if (num == 1) {
            return true;
        }

        return false;
    }

    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + i - nums[i];
        }

        sum += nums.length;

        return sum;
    }

    public boolean isBadVersion(int v) {
        return false;
    }

    public int firstBadVersion(int n) {
        int res = -1;
        int s = 1;
        int f = n;
        while (s <= f) {
            int m = s + (f - s) / 2;
            if (isBadVersion(m)) {
                if (res == -1 || m < res) {
                    res = m;
                }
                f = m - 1;
            } else {
                s = m + 1;
            }
        }
        return res;
    }

    public void moveZeroes(int[] nums) {
        int zeroIdx = -1;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                if (zeroIdx >= 0) {
                    int tmp = nums[i];
                    nums[i] = nums[zeroIdx];
                    nums[zeroIdx] = tmp;
                    zeroIdx++;
                }
            } else {
                if (zeroIdx < 0) {
                    zeroIdx = i;
                }
            }
        }
    }

    public boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null) {
            return true;
        }

        if (pattern == null || str == null) {
            return false;
        }

        String[] tmp = str.trim().split(" ");
        if (tmp.length != pattern.length()) {
            return false;
        }

        HashMap<Character, String> map = new HashMap<Character, String>();
        for (int i = 0; i < tmp.length; i++) {
            char tmpC = pattern.charAt(i);
            //System.out.println(tmp[i] + "\t" + tmpC + "\t" + map);
            if (!map.containsKey(tmpC)) {
                if (map.values().contains(tmp[i])) {
                    return false;
                }
                map.put(tmpC, tmp[i]);
            } else {
                if (!map.get(tmpC).equals(tmp[i])) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    public boolean isPowerOfThree(int n) {
        return n > 0 && Math.pow(3, 19) % n == 0;
    }

    public boolean isPowerOfFour(int num) {
        if (num == 1) {
            return true;
        }
        int mod = num % 10;
        return (num > 0 && Math.pow(2, 30) % num == 0) && (mod == 4 || mod == 6);
    }

    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public String reverseVowels(String s) {
        Set<Character> vowelsSet = new HashSet<Character>();
        vowelsSet.add('a');
        vowelsSet.add('e');
        vowelsSet.add('i');
        vowelsSet.add('o');
        vowelsSet.add('u');
        vowelsSet.add('A');
        vowelsSet.add('E');
        vowelsSet.add('I');
        vowelsSet.add('O');
        vowelsSet.add('U');
        char[] chars = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j;) {
            char tmpi = s.charAt(i);
            char tmpj = s.charAt(j);
            if (vowelsSet.contains(tmpi) && vowelsSet.contains(tmpj)) {
                chars[i] = tmpj;
                chars[j] = tmpi;
                i++;
                j--;
            } else if (!vowelsSet.contains(tmpi)) {
                i++;
            } else if (!vowelsSet.contains(tmpj)) {
                j--;
            }
        }

        return new String(chars);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) {
                set2.add(nums2[i]);
            }
        }

        int[] res = new int[set2.size()];
        int i = 0;
        for (Integer num: set2) {
            res[i++] = num;
        }

        return res;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int num : nums1){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else{
                map.put(num, 1);
            }
        }
        for(int num : nums2){
            if(map.containsKey(num) && map.get(num) > 0){
                map.put(num, map.get(num) - 1);
                list.add(num);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for(int num : list){
            res[i++] = num;
        }
        return res;
    }

    public boolean isPerfectSquare(int num) {
        // binary search
        if (num < 0) {
            return false;
        }
        if (num == 0 || num == 1) {
            return true;
        }
        int lo = 0, hi = num;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid < num / mid) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (lo == num / lo && num % lo == 0) {
            return true;
        }
        if (hi * hi == num && num % hi == 0) {
            return true;
        }
        return false;
    }

    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }

        int sum, carry;
        sum = a ^ b;
        carry = (a & b) << 1;
        return getSum(sum, carry);
    }

    public int guessNumber(int n) {
        return guessHelper(1, n);
    }

    public int guessHelper(int start, int end) {
        int mid = start + (end - start) / 2;
        if (guess(mid) == 0) {
            return mid;
        }
        if (guess(mid) == 1) {
            return guessHelper(mid + 1, end);
        }
        return guessHelper(start, mid - 1);
    }

    public int guess(int num) {
        return 0;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0, size = magazine.length(); i < size; i++) {
            char tmp = magazine.charAt(i);
            if (map.containsKey(tmp)) {
                map.put(tmp, map.get(tmp) + 1);
            } else {
                map.put(tmp, 1);
            }
        }
        for (int i = 0, size = ransomNote.length(); i < size; i++) {
            char tmp = ransomNote.charAt(i);
            if (!map.containsKey(tmp) || map.get(tmp) <= 0) {
                return false;
            } else {
                map.put(tmp, map.get(tmp) - 1);
            }
        }

        return true;
    }

    public int firstUniqChar(String s) {
        int[] a = new int[26];
        for (int i = 0, len = s.length(); i < len; i++) {
            a[s.charAt(i) - 'a']++;
        }

        for (int i = 0, len = s.length(); i < len; i++) {
            char tmp = s.charAt(i);
            if (a[tmp - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public char findTheDifference(String s, String t) {
        int[] a = new int[26];
        int i = 0;
        for (int size = s.length(); i < size; i++) {
            a[s.charAt(i) - 'a']++;
            a[t.charAt(i) - 'a']--;
        }
        a[t.charAt(i) - 'a']--;

        for (i = 0; i < 26; i++) {
            System.out.println(a[i]);
            if (a[i] != 0) {
                return (char)('a' + i);
            }
        }

        return  '\0';
    }

    /**
     * https://www.cnblogs.com/grandyang/p/5891871.html
     * https://blog.csdn.net/u013554860/article/details/80264890
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        // 位数
        long digit = 1;
        // 每位数的第一个数
        long ith = 1;
        // 每位数的个数,一位的9，二位的90
        long cnt = 9;
        while (n > cnt * digit) {
            n -= cnt * digit;
            digit++;
            ith += cnt;
            cnt *= 10;
        }

        return String.valueOf(ith + (n - 1) / digit).charAt((int)((n - 1) % digit)) - '0';
    }

    // 试试队列
    public int thirdMax(int[] nums) {
        int maxIdx = -1;
        int secondIdx = -1;
        int thirdIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (maxIdx == -1 || nums[i] > nums[maxIdx]) {
                if (maxIdx != -1 && (secondIdx == -1 || nums[maxIdx] > nums[secondIdx])) {
                    if (secondIdx != -1 && (thirdIdx == -1 || nums[secondIdx] > nums[thirdIdx])) {
                        thirdIdx = secondIdx;
                    }

                    secondIdx = maxIdx;
                }
                maxIdx = i;
            } else if ((nums[i] < nums[maxIdx])&& (secondIdx == -1 || nums[i] > nums[secondIdx])) {
                if (secondIdx != -1 && (thirdIdx == -1 || nums[secondIdx] > nums[thirdIdx])) {
                    thirdIdx = secondIdx;
                }

                secondIdx = i;
            } else if ((secondIdx != -1 && nums[i] < nums[secondIdx]) && (thirdIdx == -1 || nums[i] > nums[thirdIdx])) {
                thirdIdx = i;
            }
        }
        if (thirdIdx == -1) {
            return nums[maxIdx];
        }

        return nums[thirdIdx];
    }

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                res.add("FizzBuzz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else {
                res.add(String.valueOf(i));
            }
        }

        return res;
    }

    public int longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }

        int len = 0;
        boolean singleFlag = true;
        for (java.util.Map.Entry<Character, Integer> entry: map.entrySet()) {
            int value = entry.getValue();
            if (value % 2 == 1) {
                if (singleFlag) {
                    len += 1;
                    singleFlag = false;
                }
                value -= 1;
            }


            len += value;
        }

        return len;
    }

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int val = 0xf & num;
            char c = (char)(val + '0');
            if (val >= 10) {
                c = (char)('a' + val - 10);
            }
            num = num >>> 4;
            sb.append(c);
        }

        return sb.reverse().toString();
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return sumHelper(root, true);
    }

    public int sumHelper(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null && isLeft) {
            return root.val;
        }

        int sum = 0;
        sum += sumHelper(root.left, true);
        sum += sumHelper(root.right, false);
        return sum;
    }

    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<String>();

        HashMap<Integer, List<Integer>> hourMap = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<String>> minuteMap = new HashMap<Integer, List<String>>();
        for (int i = 0; i <= 11; i++) {
            int tmp = Integer.toBinaryString(i).replace("0", "").length();
            if (!hourMap.containsKey(tmp)) {
                hourMap.put(tmp, new ArrayList<Integer>());
            }
            hourMap.get(tmp).add(i);
        }

        for (int i = 0; i <= 59; i++) {
            int tmp = Integer.toBinaryString(i).replace("0", "").length();
            if (!minuteMap.containsKey(tmp)) {
                minuteMap.put(tmp, new ArrayList<String>());
            }
            if (i >= 10) {
                minuteMap.get(tmp).add(String.valueOf(i));
            } else {
                minuteMap.get(tmp).add("0" + i);
            }
        }

        for (int i = 0; i <= num; i++) {
            List<Integer> hourList = hourMap.get(i);
            List<String> minuteList = minuteMap.get(num - i);
            if (hourList == null || minuteList == null) {
                continue;
            }

            for (int h = 0, hLen = hourList.size(); h < hLen; h++) {
                for (int m = 0, mLen = minuteList.size(); m < mLen; m++) {
                    res.add(hourList.get(h) + ":" + minuteList.get(m));
                }
            }
        }

        return res;
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        while (i >= 0 && j >= 0) {
            int sum = num1.charAt(i) - '0' + num2.charAt(j) - '0' + add;
            sb.append(sum % 10);
            add = sum / 10;
            i--;
            j--;
        }

        String remain = i > j ? num1 : num2;
        for (int k = Math.max(i, j); k >= 0; k--) {
            int sum = remain.charAt(k) - '0' + add;
            sb.append(sum % 10);
            add = sum / 10;
        }

        if (add > 0) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    /**
     * https://leetcode.com/problems/construct-quad-tree/discuss/182762/Java-Recursive-Solution-2ms-beats-100-simple-to-understand-code
     * @param grid
     * @return
     */
    public Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid.length);
    }

    public Node construct(int[][] grid, int r, int c, int ss) {
        int target = grid[r][c];
        for (int i = r; i < r + ss; i++) {
            for (int j = c; j < c + ss; j++) {
                if (target != grid[i][j]) {
                    return new Node(true, false,
                        construct(grid, r, c, ss / 2),
                        construct(grid, r, c + ss / 2, ss / 2),
                        construct(grid, r + ss / 2, c, ss / 2),
                        construct(grid, r + ss / 2, c + ss / 2, ss / 2));
                }
            }
        }

        return new Node((target == 1) ? true : false, true, null, null, null, null);
    }

    public List<List<Integer>> levelOrder(NxNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        levelOrderHelper(root, list, 1);

        return list;
    }

    public void levelOrderHelper(NxNode node, List<List<Integer>> list, int depth) {
        if (node == null) {
            return;
        }

        if (list.isEmpty() || list.size() < depth) {
            list.add(new ArrayList<Integer>());
        }

        list.get(depth - 1).add(node.val);
        if (node.children != null && !node.children.isEmpty()) {
            for (int i = 0, size = node.children.size(); i < size; i++) {
                levelOrderHelper(node.children.get(i), list, depth + 1);
            }
        }
    }

    public int countSegments(String s) {
        if (s == null) {
            return 0;
        }

        int count = 0;
        String[] tmp = s.trim().split(" ");
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].length() > 0) {
                count++;
            }
        }

        return count;
    }

    /**
     * 已知：数列nums，初始和s0，长度n，最小的数为m
     假设移动k步
     每移动一步，n-1个数会被＋1，则最终和s = s0 +(n-1) x k
     平均数为 s/n
     最小数每次移动都被+1，因此有：k =s/n -m
     即：（s0 +(n-1) x k）/n -m =k
     求得： k = s0 - m x n
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            sum += nums[i];
        }

        return sum - min * nums.length;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            int tmp = Math.abs(nums[index]);

            if (tmp > 0) {
                nums[index] = tmp * -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }

    public int numberOfBoomerangs(int[][] points) {
        int count = 0;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                int d = (int)(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            for (int val: map.values()) {
                count += val * (val - 1);
            }

            map.clear();
        }

        return count;
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        return (node.val == sum ? 1 : 0)
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();

        if (s == null || s.length() < p.length()) {
            return res;
        }

        int pLen = p.length();
        int[] hash = new int[256];
        for (int i = 0, len = p.length(); i < len; i++) {
            hash[p.charAt(i)]++;
        }

        int left = 0, right = 0, count = pLen;
        while (right < s.length()) {
            if (hash[s.charAt(right++)]-- >= 1) {
                count--;
            }

            if (count == 0) {
                res.add(left);
            }

            if (right - left == pLen && hash[s.charAt(left++)]++ >= 0) {
                count++;
            }
        }
        return res;
    }

    public int arrangeCoins(int n) {
        if(n < 1) {
            return 0;
        }
        for(int i = 1; ;i++){
            n -=i;
            if(n < 0) {
                return i-1;
            }
        }
    }

    public int compress(char[] chars) {
        int count = 1;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            while (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }
            if (count == 1) {
                chars[index++] = chars[i];
            } else {
                chars[index++] = chars[i];
                String num = String.valueOf(count);
                for (int j = 0; j < num.length(); j++) {
                    chars[index++] = num.charAt(j);
                }
            }
            count = 1;
        }
        return index;
    }

    public void test() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(5);

        int[] nums = {4,3,2,7,8,2,3,1};
        int[][] points = {{0,0},{1,0},{2,0}};

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(1);

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(4);
        //System.out.
        // println(lowestCommonAncestor(root, p, q).val);
        System.out.println(arrangeCoins(1));
        System.out.println(arrangeCoins(2));
        System.out.println(arrangeCoins(5));
        System.out.println(arrangeCoins(8));
    }
}