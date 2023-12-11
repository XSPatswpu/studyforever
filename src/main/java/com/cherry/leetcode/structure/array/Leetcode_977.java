package com.cherry.leetcode.structure.array;

import com.cherry.leetcode.utils.PrintUtil;

public class Leetcode_977 {



    /**
     * 逆序归并排序
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
     * 1. 先找到分界点
     * 2. 正序归并排序
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int right = 0;
        for (int num : nums) {
            if (num < 0) {
                right++;
            }
        }

        int[] newNums = new int[length];

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
                } else {
                    newNums[newIndex] = r;
                    newIndex++;
                    right++;
                }
            } else if (left < 0) {
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


    public int[] sortedSquares2(int[] nums) {
        int[] newNums = new int[nums.length];
        int ahead = 0, behind = nums.length - 1, index = nums.length - 1;
        while (ahead <= behind) {
            if (nums[ahead] * nums[ahead] >=  nums[behind] * nums[behind]) {
                newNums[index] = nums[ahead] * nums[ahead];
                ahead++;
            } else {
                newNums[index] = nums[behind] * nums[behind];
                behind--;
            }
            index--;
        }

        return newNums;
    }

    public static void main(String[] args) {
        int[] array = {-4, -1, 0};

        Leetcode_977 lc977 = new Leetcode_977();
        int[] ints = lc977.sortedSquares2(array);

        PrintUtil.print(ints);


    }
}
