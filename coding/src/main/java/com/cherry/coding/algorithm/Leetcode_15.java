package com.cherry.coding.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 思路参考两数相加
 *
 * 要注意返回去重的结果
 *
 * 思路很自然，采用枚举的方式，把3数相加转化为2数相加，然后再采用双指针
 */
public class Leetcode_15 {


    public static void main(String[] args) {
        Leetcode_15 leetcode_15 = new Leetcode_15();

        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {0, 0, 0, 0};
        List<List<Integer>> lists = leetcode_15.threeSum(nums);
        System.out.println(lists);

    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        // 排序
        Arrays.sort(nums);

        // {a, b, c}

        // a
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // b + c
            int target = -nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int subSum = nums[start] + nums[end];


                if (subSum < target) {
                    while (start < end && nums[start] == nums[++start]) {}
                } else if (subSum > target) {
                    while (start < end && nums[end] == nums[--end]) {}
                } else {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[start]);
                    temp.add(nums[end]);
                    result.add(temp);
                    while (start < end && nums[start] == nums[++start]) {}
                }
            }

        }
        return result;
    }

}
