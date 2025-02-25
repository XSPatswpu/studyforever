package com.cherry.coding.algorithm.divideandconquer;

import java.util.Random;

public class Leetcode_215 {
    public static void main(String[] args) {
//        int[] nums = {3, 2, 1, 5, 6, 4};
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
//        Leetcode_215 leetcode_215 = new Leetcode_215();
//        int kthLargest = leetcode_215.findKthLargest(nums, 2);
//
//        System.out.println(kthLargest);

        MaxHeap maxHeap = new MaxHeap();
        int kthLargest1 = maxHeap.findKthLargest1(nums, 4);

        System.out.println(kthLargest1);


    }
    Random random = new Random();



    public int findKthLargest(int[] nums, int k) {

        int index = nums.length - k;

        int i = find(nums, 0, nums.length - 1, index);
        return nums[i];


    }

    public int find(int[] nums, int start, int end, int index)
    {

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


    public static class MaxHeap {

        public int findKthLargest1(int[] nums, int k) {
            buildHeap(nums);

            for (int i = 1; i < k; i++) {
                removeTop();
            }

            return getTop();

        }

        // field
        public int size;

        public int[] elements;

        // capacity
        public int capacity;

        // method

        // build heap
        public void buildHeap(int[] arr) {
            elements = new int[arr.length + 1];
            capacity = arr.length;

            for (int i = 0; i < arr.length; i++) {
                insert(arr[i]);
            }
        }

        // get top of heap
        public int getTop() {
            return elements[1];
        }

        // remove top of heap
        public void removeTop() {
            if (size <= 0) {
                return;
            }

            elements[1] = elements[size];
            size--;
            int i = 1;
            while (true) {
                int j = i;

                if (2 * i <= size && elements[2 * i] > elements[j]) {
                    j = 2 * i;
                }

                if (2 * i + 1 <= size && elements[2 * i + 1] > elements[j]) {
                    j = 2 * i + 1;
                }

                if (i == j) {
                    break;
                }
                swap(elements, i, j);
                i = j;
            }
        }

        // insert
        public void insert(int data) {
            if (size >= capacity) {
                return;
            }

            size++;
            elements[size] = data;

            int i = size;
            while (i / 2 > 0 && elements[i] > elements[i / 2]) {
                swap(elements, i, i / 2);
                i = i / 2;
            }
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }
}
