package com.cherry.algorithm;

public class Leetcode_4 {
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }
            int m = nums1.length;
            int n = nums2.length;

            // 分割线左边元素个数
            int k = (m + n + 1) / 2;

            // nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
            int left = 0, right = m;

            // 为什么二分查找完之后，left 和 right 一定相等
            while (left < right) {
                int i = (left + right + 1) / 2;
                int j = k - i;
                if (nums1[i - 1] > nums2[j]) {
                    // i 不能要
                    right = i - 1;
                } else {
                    // i 能要

                    // [1, 2] 为例子参与循环 left = 1, rigth = 2
                    // i = (1 + 2) / 2 = 1
                    // nums1[i - 1] <= nums2[j]
                    // left = i = 1
                    // right = 2
                    // 死循环
                    left = i;
                }
            }

            int i = left;
            int j = k - left;
            int nums1Left = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1Right = i == m ? Integer.MAX_VALUE : nums1[i];
            int nums2Left = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2Right = j == n ? Integer.MAX_VALUE : nums2[j];

            if ((m + n) % 2 == 1) {
                return Math.max(nums1Left, nums2Left);
            }
            return ((double)(Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2);
        }
    }
}
