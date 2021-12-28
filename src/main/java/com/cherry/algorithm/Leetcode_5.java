package com.cherry.algorithm;

public class Leetcode_5 {

    /**
     * 动态规划
     */
    class Solution {
        public String longestPalindrome(String s) {
            int length = s.length();
            char[] arr = s.toCharArray();
            boolean[][] dp = new boolean[length][length];

            int maxi = 0;
            int maxLength = 1;

            for (int j = 0; j < length; j++) {
                for (int i = 0; i < length; i++) {
                    if (i > j) {
                        break;
                    }
                    boolean flag = false;
                    if (arr[i] == arr[j]) {
                        if (j - i < 3) {
                            flag = true;
                        } else {
                            flag = dp[i + 1][j - 1];
                        }
                    }

                    dp[i][j] = flag;
                    if (flag) {
                        if (j - i + 1 > maxLength) {
                            maxi = i;
                            maxLength = j - i + 1;
                        }
                    }

                }
            }

            return s.substring(maxi, maxi + maxLength);
        }
    }
}
