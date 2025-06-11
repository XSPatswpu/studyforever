package com.cherry.coding.algorithm;

public class Leetcode_42 {

    class Solution {
        public int trap(int[] height) {
            int ans = 0;
            for (int i = 1; i < height.length - 1; i++) {
                int curr = height[i];
                int left = height[i - 1];
                for (int j = i - 1; j >= 0; j--) {
                    left = Math.max(height[j], left);
                }
                int right = height[i + 1];
                for (int k = i + 1; k < height.length; k++) {
                    right = Math.max(height[k], right);
                }
                int temp = Math.min(left, right);
                if (curr < temp) {
                    ans += temp - curr;
                }
            }
            return ans;
        }
    }
}
