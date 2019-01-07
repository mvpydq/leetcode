package simple;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName NumArr.java
 * @Description TODO
 * @createTime 2018年11月02日 15:20:00
 */
public class NumArray {
    private int[] sums;

    public NumArray(int[] nums) {
        if (nums != null && nums.length > 0) {
            sums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2,0,3,-5,2,-1};
        NumArray array = new NumArray(nums);
        System.out.println(array.sumRange(0, 2));
        System.out.println(array.sumRange(2, 5));
        System.out.println(array.sumRange(0, 5));

        System.out.println(Math.pow(2, 30) > Integer.MAX_VALUE);
    }
}
