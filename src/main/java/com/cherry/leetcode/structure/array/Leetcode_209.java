package com.cherry.leetcode.structure.array;

public class Leetcode_209 {
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
    public int minSubArrayLen2(int target, int[] nums) {
        int len = 1000000, sub = 0;
        int left = 0, right = 0;
        for (; right < nums.length; right++) {
            sub += nums[right];
            // while
            while (sub >= target && left <= right) {
                len = Math.min(len, right - left + 1);
                sub -= nums[left];
                left++;
            }
        }
        return len == 1000000 ? 0 : len;
    }

    public static void main(String[] args) {
        int target = 11;
        int[] array = {1,1,1,1,1,1,1,1};

        Leetcode_209 lc = new Leetcode_209();
        int i = lc.minSubArrayLen2(target, array);
        System.out.println(i);

    }

}
