package com.cherry.coding.algorithm.dynamicprogramming;

public class Leetcode_5 {

    /**
     * 动态规划
     */
    public static class Solution {
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







        public String longestPalindrome2(String s) {
            boolean[][] dp = new boolean[s.length()][s.length()];

            // init length 1
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
            }

            int left = 0;
            int right = 0;
            char[] arr = s.toCharArray();
            for (int l = 2; l < s.length() + 1; l++) {
                for (int i = 0; i < s.length() - l + 1; i++) {
                    int j = l + i - 1;
                    // i - 1, j - 1
                    // s(i) s(j)
                    if ((l == 2 || dp[i + 1][j - 1]) && arr[i] == arr[j]) {
                        dp[i][j] = true;
                        if (j - i > right - left) {
                            left = i;
                            right = j;
                        }
                    }
                }
            }
            return s.substring(left, right + 1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String str = s.longestPalindrome2("cbbd");
        System.out.println(str);
    }
}
