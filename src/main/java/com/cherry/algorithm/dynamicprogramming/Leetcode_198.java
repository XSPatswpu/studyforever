package com.cherry.algorithm.dynamicprogramming;

public class Leetcode_198 {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 1};
//        int[] nums = {2, 7, 9, 3, 1};
        int[] nums = {1, 1};
//        int[] nums = {1};
//        int[] nums = {1, 2};
//        int[] nums = {1, 3, 1};
//        int[] nums = {0, 0, 0};
        int rob = rob2(nums);
        System.out.println(rob);
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];

    }

    /**
     * 优化内存
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int current = 0, pre, pre2;
        pre2 = nums[0];
        pre = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            current = Math.max(pre, pre2 + nums[i]);
            pre2 = pre;
            pre = current;
        }
        return current;

    }


    public static int rob0(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }


        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum == 0) {
            return 0;
        }

        boolean[][] status = new boolean[nums.length][sum];

        // init money
        int money = 0;

        // 处理初始值 i = 0, i = 1
        status[0][0] = true;
        status[0][nums[0]] = true;
        money = Math.max(money, nums[0]);
        status[1][nums[0]] = true;
        status[1][nums[1]] = true;
        money = Math.max(money, nums[1]);
        status[2][nums[1]] = true;
        status[2][nums[0]] = true;
        status[2][nums[0] + nums[2]] = true;
        money = Math.max(money, nums[0] + nums[2]);
        for (int i = 3; i < nums.length; i++) {
            for (int j = 0; j < sum; j++) {
                if (status[i - 1][j]) {
                    // 如果上一家偷了
                    // 不能偷
                    if (!status[i - 2][j]) {
                        status[i][j] = true;
                        continue;
                    }

                    // 偷
                    status[i][j + nums[i]] = true;

                    // 记录最大值
                    if (money < j + nums[i]) {
                        money = j + nums[i];
                    }

                    // 如果上一家没有偷，且上上家也没有偷
                    // 只能偷
                    if (status[i - 3][j]) {
                        continue;
                    }

                    // 不偷
                    status[i][j] = true;
                }
            }
        }

        return money;
    }
}
