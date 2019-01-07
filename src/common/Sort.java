package common;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Sort.java
 * @Description TODO
 * @createTime 2018年11月21日 16:22:00
 */
public class Sort {
    public void quickSort(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
    }

    public void quickSortHelper(int[] nums, int start, int end) {
        int target = nums[start];
        int l = start, r = end;
        while (l < r) {
            while (nums[r] >= target && r >= l) {
                r--;
            }


            if (r > l) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
            }

            while (nums[l] <= target && l <= r) {
                l++;
            }

            if (l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                r--;
            }
        }

        if (l > start) {
            quickSortHelper(nums, start, l - 1);
        }
        if (r < end) {
            quickSortHelper(nums, l + 1, end);
        }
    }

    public void mergeSort(int[] nums) {
        int[] tmp = new int[nums.length];
        mergeSortHelper(nums, 0, nums.length - 1, tmp);
    }

    public void mergeSortHelper(int[] nums, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(nums, left, mid, tmp);
            mergeSortHelper(nums, mid + 1, right, tmp);
            merge(nums, left, mid, right, tmp);
        }
    }

    public void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[t++] = nums[i++];
            } else {
                tmp[t++] = nums[j++];
            }
        }

        while (i <= mid) {
            tmp[t++] = nums[i++];
        }
        while (j <= right) {
            tmp[t++] = nums[j++];
        }

        t = 0;
        while (left <= right) {
            nums[left++] = tmp[t++];
        }
    }

    public static void main(String[] args) {
        Sort s = new Sort();

        int[] nums = {1, 4, 2, 3, 0, 5};
        s.mergeSort(nums);
        //s.sort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
