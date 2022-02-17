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
}
