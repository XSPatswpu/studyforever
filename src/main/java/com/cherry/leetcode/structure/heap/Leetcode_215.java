package com.cherry.leetcode.structure.heap;

import java.util.Random;

public class Leetcode_215 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
//        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        Leetcode_215 leetcode_215 = new Leetcode_215();
        int kthLargest = leetcode_215.findKthLargest(nums, 2);

        System.out.println(kthLargest);

    }
    Random random = new Random();



    public int findKthLargest(int[] nums, int k) {

        int index = nums.length - k;

        int i = find(nums, 0, nums.length - 1, index);
        return nums[i];


    }

    public int find(int[] nums, int start, int end, int index) {

        int place = findLarget(nums, start, end);
        if (place < index) {
            return find(nums, place + 1, end, index);
        } else if (place > index) {
            return find(nums, start, place - 1, index);
        }
        return place;
    }

    public int findLarget(int[] nums, int start, int end) {
        int posi = random.nextInt(end - start + 1) + start;
        swap(nums, posi, end);
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

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
