package com.cherry.leetcode.algorithm.dynamicprogramming;

public class Leetcode_213 {

    public static void main(String[] args) {
//        int[] nums = {2, 3, 2};
//        int[] nums = {1, 2, 3, 1};
        int[] nums = {0};
        System.out.println(rob(nums));

    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int current = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            current = Math.max(rob1(nums, 1, i), nums[0] + rob1(nums, 2, i - 1));
        }
        return current;
    }

    public static int rob1(int[] nums, int start, int end) {
        int length = end - start + 1;
        if (length <= 0) {
            return 0;
        }
        if (length == 1) {
            return nums[start];
        }
        int current, pre, pre2;
        pre2 = nums[start];
        current = pre = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < length; i++) {
            current = Math.max(pre, pre2 + nums[start + i]);
            pre2 = pre;
            pre = current;
        }
        return current;

    }
}
