package com.cherry.coding.algorithm.greedy;

public class Leetcode_53 {

    /**
     * 贪心算法
     *
     * 丢弃之前的负数，重新开始计算最大子序和
     */
    class Solution {
        public int maxSubArray(int[] nums) {
            int prevSum = Integer.MIN_VALUE;
            int maxSum = prevSum;
            for (int i = 0; i < nums.length; i++) {
                if (prevSum > 0) {
                    prevSum += nums[i];
                } else {
                    prevSum = nums[i];
                }
                maxSum = Math.max(maxSum, prevSum);
            }
            return maxSum;
        }
    }

    /**
     * 动态规划
     *
     * 1. 寻找子问题
     * 2. 寻找子问题和递推的后一个问题的关系，得出递推公式
     * 3. 根据递推公式，看一看子问题能不能求解（一般寻找初始值）
     */
    class Solution1 {
        public int maxSubArray(int[] nums) {
            int prev = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                prev = Math.max(prev + nums[i], nums[i]);
                max = Math.max(prev, max);
            }
            return max;
        }
    }
}
