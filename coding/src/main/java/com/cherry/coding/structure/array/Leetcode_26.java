package com.cherry.coding.structure.array;

public class Leetcode_26 {
    /**
     * 快慢双指针
     */
    class Solution {
        public int removeDuplicates(int[] nums) {
            int slow = 0, quick = 0;
            for (; quick < nums.length - 1; quick++) {
                if (nums[quick] != nums[quick + 1]) {
                    nums[++slow] = nums[quick + 1];
                }
            }
            return slow + 1;

        }
    }
}
