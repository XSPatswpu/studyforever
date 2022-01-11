package com.cherry.algorithm;

public class Leetcode_34 {

    public static void main(String[] args) {
        Leetcode_34 leetcode_34 = new Leetcode_34();
        int[] array = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] ints = leetcode_34.searchRange(array, target);
        System.out.println(ints);
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int start = -1, end = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // hit
                while (mid > 0 && nums[mid - 1] == nums[mid]) {
                    mid--;
                }
                start = mid;
                while (mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                    mid++;
                }
                end = mid;
                break;
            }
        }
        return new int[]{start, end};

    }
}
