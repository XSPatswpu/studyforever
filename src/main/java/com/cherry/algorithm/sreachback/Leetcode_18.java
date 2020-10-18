package com.cherry.algorithm.sreachback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/5 10:52
 */
public class Leetcode_18 {
    public static void main(String[] args) {
        Leetcode_18 leet = new Leetcode_18();
        int[] arr = {1, 0, -1, 0, -2, 2};

        List<List<Integer>> lists = leet.fourSum(arr, 0);
        System.out.println(lists);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, nums.length, 0, 0, target, result, list, sum);
        return result;
    }

    private int sum = 0;

    public void dfs(int[] nums, int len, int index, int deep, int target, List<List<Integer>> result, List<Integer> list, int sum) {
        if (deep == 4) {
            if (sum == target) {
                result.add(new ArrayList(list));
            }
            return;
        }
        if (index == len) {
            return;
        }
        if (sum > target && nums[index] > 0) {
            return;
        }

        for (int i = index; i < len; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            sum += nums[i];
            list.add(nums[i]);
            dfs(nums, len, i + 1, deep + 1, target, result, list, sum);
            int last = list.get(list.size() - 1);
            sum -= last;
            list.remove(list.size() - 1);
        }
    }
}
