package toutiao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import common.Interval;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName ArrSolution.java
 * @Description TODO
 * @createTime 2019年01月04日 12:38:00
 */
public class ArrSolution {
    /**
     * https://blog.csdn.net/weixin_42156844/article/details/82856554
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while (r > l) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> list = Arrays.asList(nums[i], nums[l], nums[r]);
                    res.add(list);
                    while (r > l && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    while (r > l && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    r--;
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSumDfs(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();
        Arrays.sort(nums);
        dfs(current, 0, 0, nums, res);

        return res;
    }

    public void dfs(List<Integer> current, int start, int sum, int[] nums, List<List<Integer>> res) {
        if (current.size() == 3 && sum == 0) {
            if (sum != 0) {
                return;
            }
            res.add(new ArrayList<Integer>(current));
        } else if (current.size() > 3) {
            return;
        } else {
            for (int i = start; i < nums.length; i++) {
                sum += nums[i];
                current.add(nums[i]);
                dfs(current, i + 1, sum, nums, res);
                sum -= nums[i];
                current.remove(current.size() - 1);
                while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                    i++;
                }
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int x = grid.length;
        int y = grid[0].length;

        HashMap<String, Integer> sumMap = new HashMap<String, Integer>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                max = Math.max(max, islandDfs(grid, i, j, sumMap));
            }
        }

        return max;
    }

    public int islandDfs(int[][] grid, int i, int j, HashMap<String, Integer> sumMap) {
        int x = grid.length;
        int y = grid[0].length;
        if (grid[i][j] == 0) {
            return 0;
        }

        String key = i + "," + j;
        if (sumMap.containsKey(key)) {
            return sumMap.get(key);
        }

        grid[i][j] = 0;

        int sum = 1;
        if (i + 1 < x && grid[i + 1][j] == 1) {
            sum += islandDfs(grid, i + 1, j, sumMap);
        }
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            sum += islandDfs(grid, i - 1, j, sumMap);
        }
        if (j + 1 < y && grid[i][j + 1] == 1) {
            sum += islandDfs(grid, i, j + 1, sumMap);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            sum += islandDfs(grid, i, j - 1, sumMap);
        }

        sumMap.put(key, sum);

        return sum;
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (target >= nums[0]) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    return i;
                } else if (target < nums[i]) {
                    return -1;
                }
                if (i + 1 < nums.length && nums[i + 1] < nums[i]) {
                    return -1;
                }
            }
        } else {
            for (int j = nums.length - 1; j >= 0; j--) {
                if (nums[j] == target) {
                    return j;
                } else if (target > nums[j]) {
                    return -1;
                }
                if (j - 1 >= 0 && nums[j - 1] > nums[j]) {
                    return -1;
                }
            }
        }

        return -1;
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                len ++;
            } else {
                res = Math.max(len, res);
                len = 1;
            }
        }
        res = Math.max(len, res);

        return res;
    }

    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num: nums) {
            if (map.containsKey(num)) {
                continue;
            }

            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int sum = left + right + 1;
            map.put(num, sum);

            if (left != 0) {
                map.put(num - left, sum);
            }
            if (right != 0) {
                map.put(num + right, sum);
            }
            res = Math.max(sum, res);
        }

        return res;
    }

    public String getPermutation(int n, int k) {
        List<Integer> num = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            num.add(i);
        }

        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }

        k --;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int idx = k / fact[i - 1];
            k = k % fact[i - 1];
            sb.append(num.get(idx));
            num.remove(idx);
        }

        return sb.toString();
    }

    public int findCircleNum(int[][] M) {
        int res = 0;

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    res ++;
                    dfs(i, j, M);
                }
            }
        }

        return res;
    }

    public void dfs(int i, int j, int[][] M) {
        int x = M.length;
        int y = M[0].length;

        if (M[i][j] == 1) {
            M[i][j] = 0;

            for (int k = 0; k < x; k++) {
                dfs(k, j, M);
            }
            for (int k = 0; k < y; k++) {
                dfs(i, k, M);
            }
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return intervals;
        }

        List<Interval> res = new ArrayList<Interval>();

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                } else {
                    return o1.end - o2.end;
                }
            }
        });

        Interval first = intervals.get(0);
        int start = first.start;
        int end = first.end;
        for (int i = 1; i < intervals.size(); i++) {
            Interval tmp = intervals.get(i);
            if (tmp.start > end) {
                res.add(new Interval(start, end));
                start = tmp.start;
                end = tmp.end;
            } else {
                end = Math.max(end, tmp.end);
            }
        }

        res.add(new Interval(start, end));

        return res;
    }

    /**
     * https://blog.csdn.net/i000zheng/article/details/79725417
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxL = height[0];
        int[] maxRs = new int[height.length];
        int sum = 0;
        int maxR = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > maxR) {
                maxRs[i] = maxR = height[i];
            } else {
                maxRs[i] = maxR;
            }
        }

        for (int i = 1; i < height.length - 1; i++) {
            if (height[i] > maxL) {
                maxL = height[i];
            }
            sum += Math.max(Math.min(maxL, maxRs[i]) - height[i], 0);
        }

        return sum;
    }

    public static void main(String[] args) {
        ArrSolution s = new ArrSolution();
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        //int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        //int[] nums = {5, 4, 1, 2};
        int[] nums = {9,6,8,8,5,6,3};
        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        int[][] M = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};

        //int[][] intervals = {{1,3},{2,6}, {8,10},{15,18}};
        int[][] intervals = {{1,4},{2,3}};
        System.out.println(s.trap(nums));
    }
}
