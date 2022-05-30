package com.cherry.leetcode.structure.array;

public class Leetcode_27 {

    /**
     * 快慢双指针
     * @param nums
     * @param val
     * @return
     */
    public int removeElement0(int[] nums, int val) {
        int left = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[++left] = nums[i];
            }
        }
        return left + 1;

    }

    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {

                if (i == len - 1) {
                    return len - 1;
                }
                // 移动后面元素
                for (int j = i + 1; j < len; j++) {
                    nums[j - 1] = nums[j];
                }
                // len--
                len--;
                i--;
            }
        }
        return len;

    }

    public int removeElement2(int[] nums, int val) {
        int slow = -1, quick = 0;
        for (; quick < nums.length; quick++) {
            if (nums[quick] == val) {
                continue;
            }

            slow++;
            if (slow != quick) {
                nums[slow] = nums[quick];
            }

        }
        return slow + 1;

    }
}
