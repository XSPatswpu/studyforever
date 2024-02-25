package com.cherry.leetcode.algorithm.binarysearch;

public class Leetcode_33 {
    class Solution {


        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[left] == target) {
                    return left;
                }

                if (nums[right] == target) {
                    return right;
                }

                // left
                if (nums[left] < nums[mid]) {
                    // target
                    if (target > nums[left] && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    // target
                    if (target > nums[mid] && target < nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            return -1;
        }



        /**
         * 思路：
         * 1. 先找到数组中的分界点
         * 2. 再对两边的数组，依次进行二分查找
         *
         *
         * @param nums
         * @param target
         * @return
         */
        public int search0(int[] nums, int target) {
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
                    // 因为 mid 是靠右选择，所以当命中左边区间时，mid - 1 可以有效支持。
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


        public int search2(int[] nums, int target) {

            int l = 0;
            int r = nums.length - 1;

            while (l <= r) {
                int mid = l + (r - l) / 2;

                if (nums[mid] == target) {
                    return mid;
                } else if (nums[l] <= nums[mid]) {
                    if (nums[l] <= target && nums[mid] >= target) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (nums[mid] <= target && nums[r] >= target) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }

            }

            return -1;
        }
    }
}
