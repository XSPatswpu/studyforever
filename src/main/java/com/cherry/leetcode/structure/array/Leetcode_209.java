package com.cherry.leetcode.structure.array;

public class Leetcode_209 {

    public static void main(String[] args) {
        int target = 7;
        int[] array = {2, 3, 1, 2, 4, 3};

        Leetcode_209 lc = new Leetcode_209();
        int i = lc.minSubArrayLen(target, array);
        System.out.println(i);


    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int cur = 0;
        int length = nums.length;

        while (left <= right && right < length) {
            sum = sum + nums[right];
            cur++;
            if (sum >= target) {
                // sum >= target record min
                min = Math.min(min, cur);

                if (min == 1) {
                    return 1;
                }

                // 缩小窗口
                while (sum - nums[left] >= target && left <= right) {
                    sum = sum - nums[left];
                    cur--;
                    min = Math.min(min, cur);
                    if (min == 1) {
                        return 1;
                    }
                    left++;
                }
            }
            right++;
        }

        return min;
    }
}
