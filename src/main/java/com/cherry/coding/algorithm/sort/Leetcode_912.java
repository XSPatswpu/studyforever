package com.cherry.coding.algorithm.sort;

import com.cherry.coding.utils.PrintUtil;

import java.util.Random;

public class Leetcode_912 {

    public static void main(String[] args) {
        Leetcode_912 leetcode_912 = new Leetcode_912();

        int[] nums = {5, 1, 1, 2, 0, 0};


        leetcode_912.sortArray(nums);
        PrintUtil.print(nums);
    }

    public Random random = new Random();

    public int[] sortArray(int[] nums) {

        quickSort(nums, 0, nums.length - 1);

        return nums;
    }

    public void quickSort(int[] nums, int start, int end) {

        if (end <= start) {
            return;
        }

        int point = sort(nums, start, end);
        quickSort(nums, start, point - 1);
        quickSort(nums, point + 1, end);
        return;
    }

    public int sort(int[] nums, int start, int end) {
        int point = random.nextInt(end - start + 1) + start;
        swap(nums, point, end);
        // i 的左边的元素都小于分区元素，i 本身的元素不确定是否小于分区元素。相当于是一个边界值以外的一个值
        int i = start;

        for (int j = start; j < end; j++) {
            if (nums[j] < nums[end]) {
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, end);
        return i;
    }

    public void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
