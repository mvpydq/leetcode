package org.ydq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author daiqing.ydq
 */
public class Main {

    public int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    indices[0] = i;
                    indices[1] = j;
                }
            }
        }

        return indices;
    }

    public int[] twoSumII(int[] nums, int target) {
        int[] indices = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    indices[0] = i + 1;
                    indices[1] = j + 1;
                }
            }
        }

        return indices;
    }

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<Integer>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
        if (root == null) {
            return false;
        }

        if (set.contains(k - root.val)) {
            return true;
        }

        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    public int reverse(int x) {
        System.out.println(x > Math.pow(2, 31) -1);
        System.out.println(x < (Math.pow(2, 31) * -1));
        System.out.println();
        System.out.println(Math.pow(2, 31) * -1);
        if (Math.abs(x) < 10) {
            return x;
        }

        char[] intCharArr = String.valueOf(x).toCharArray();
        int factor = 1;
        int start = 0;
        if (x < 0) {
            factor = -1;
            start = 1;
        }

        int end = intCharArr.length - 1;
        while (intCharArr[end] == '0' && end > 0){
            x /= 10;
            end --;
        }

        if (end == 0) {
            return x;
        }

        char[] reverseStr = new char[end + 1];
        while (start <= end) {
            reverseStr[start] = intCharArr[end];
            reverseStr[end] = intCharArr[start];
            start ++;
            end --;
        }

        try {
            int newInt = Integer.valueOf(new String(reverseStr).trim()) * factor;
            return newInt;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
	    // write your code here
        Main m = new Main();
        System.out.println(m.reverse(-2147483648));
    }
}
