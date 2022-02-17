package com.cherry.leetcode.algorithm.binarysearch;

public class Leetcode_33 {
    class Solution {
        public int search(int[] nums, int target) {
            int k = searchK(nums, 0, nums.length - 1);

            if (target >= nums[0]) {
                return bs(nums, 0, k, target);
            }
            return bs(nums, k + 1, nums.length - 1, target);
        }

        public int searchK(int[] nums, int left, int right) {
            while (left < right) {
                // 靠右选择中间元素
                int mid = (left + right + 1) / 2;
                if (nums[mid] < nums[left]) {
                    right = mid - 1;
                } else {
                    // nums[mid] <= nums[left]
                    // 注意死循环
                    left = mid;
                }
            }
            return left;
        }

        public int bs(int[] nums, int left, int right, int target) {
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }
    }
}
