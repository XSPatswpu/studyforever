package com.cherry.coding.structure.array;

import com.cherry.coding.utils.PrintUtil;

public class Leetcode_283 {

    public static void main(String[] args) {
        Leetcode_283 lc = new Leetcode_283();

//        int[] array = {0, 1, 0, 3, 12};
        int[] array = {0 , 0};
        lc.moveZeroes(array);
        PrintUtil.print(array);

    }

    // 暴力法
    public void moveZeroes(int[] nums) {
        int i = 0;
        int len = nums.length;
        for (int j = 0; j < len - i; j++) {
            if (nums[j] == 0) {
                i++;
                for (int k = j; k < len - i; k++) {
                    nums[k] = nums[k + 1];
                }
                nums[len - i] = 0;
                j--;
            }
        }
    }

    // 双指针
    // 快慢指针一起移动，每次都把快指针的元素赋值给慢指针
    // 快指针遇到 0 跳过
    public void moveZeroes1(int[] nums) {
        int slow = 0, quick = 0;
        for (; quick < nums.length; quick++) {
            if (nums[quick] != 0) {
                nums[slow] = nums[quick];
                slow++;
            }
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


}
