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
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        int left = -1;
        int right = 0;
        // for 循环中i代表right指针
        // 区间 (]
        for (; right < nums.length; right++) {
            sum += nums[right];
            // right ->
            if (sum < target) {
                continue;
            }
            // left ->
            while (sum >= target && left < right) {
                // 更新最小长度
                minLength = Math.min(minLength, right - left);
                sum -= nums[++left];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;

    }
}
