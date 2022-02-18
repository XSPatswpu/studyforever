package com.cherry.leetcode.structure.array;

import com.cherry.leetcode.utils.ArrayUtil;

public class Leetcode_977 {

    public static void main(String[] args) {
        int[] array = {-4, -1, 0};

        Leetcode_977 lc977 = new Leetcode_977();
        int[] ints = lc977.sortedSquares1(array);

        ArrayUtil.print(ints);


    }

    /**
     * 双指针
     * @param nums
     * @return
     */
    public int[] sortedSquares1(int[] nums) {

        int length = nums.length;
        int left = 0;
        int right = length - 1;
        int[] newNums = new int[length];
        int newIndex = length - 1;
        while (left <= right) {
            int leftValue = nums[left] * nums[left];
            int rightValue = nums[right] * nums[right];

            if (leftValue <= rightValue) {
                newNums[newIndex] = rightValue;
                right--;
            } else {
                newNums[newIndex] = leftValue;
                left++;
            }

            newIndex--;
        }
        return newNums;

    }

    /**
     * 归并排序
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int right = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] < 0) {
                right++;
            }
        }

        int[] newNums = new int[length];
        // 全正
        if (right == 0) {
            for (int i = 0; i < length; i++) {
                newNums[i] = nums[i] * nums[i];
            }
            return newNums;
        }

        // 全负
        if (right == length) {

            for (int i = length - 1; i >= 0; i--) {
                newNums[length - 1 - i] = nums[i] * nums[i];
            }
            return newNums;

        }
        int left = right - 1;
        int newIndex = 0;
        while (left >= 0 || right < length) {
            if (left >= 0 && right < length) {
                int l = nums[left] * nums[left];
                int r = nums[right] * nums[right];
                if (l <= r) {
                    newNums[newIndex] = l;
                    newIndex++;
                    left--;
                    continue;
                } else {
                    newNums[newIndex] = r;
                    newIndex++;
                    right++;
                    continue;
                }
            }

            if (left < 0) {
                newNums[newIndex] = nums[right] * nums[right];
                newIndex++;
                right++;
            } else {
                newNums[newIndex] = nums[left] * nums[left];
                newIndex++;
                left--;
            }
        }
        return newNums;
    }

}
