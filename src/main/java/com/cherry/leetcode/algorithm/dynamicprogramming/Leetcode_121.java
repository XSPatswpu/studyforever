package com.cherry.leetcode.algorithm.dynamicprogramming;

public class Leetcode_121 {

    /**
     * 遍历数据，分别用两个变量表示 当前最小值 当前最大利润
     */
    class Solution {
        public int maxProfit(int[] prices) {
            int curMin = Integer.MAX_VALUE;
            int max = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < curMin) {
                    curMin = prices[i];
                }
                int cur = prices[i] - curMin;
                max = Math.max(max, cur);
            }
            return max;
        }
    }
    class Solution2 {
        // dp(i) = Math.min(dp(i + 1), price(i + 1))
        public int maxProfit(int[] prices) {
            int ans = 0;
            if (prices.length == 1) {
                return ans;
            }
            int dp = 0;
            for (int i = prices.length - 2; i >= 0; i--) {
                dp = Math.max(dp, prices[i + 1]);
                ans = Math.max(ans, dp - prices[i]);
            }
            return ans;

        }
    }








}
