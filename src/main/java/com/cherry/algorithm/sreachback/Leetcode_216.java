package com.cherry.algorithm.sreachback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/11 14:03
 */
public class Leetcode_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {

        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }

        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, 0, k, n, path, result);

        return result;
    }

    public void dfs(int[] nums, int index, int deep, int k, int n, List<Integer> path, List<List<Integer>> result) {

        if (deep == k) {
            if (n == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }


        for (int i = index; i < nums.length; i++) {
            if (n - nums[i] >= 0) {
                path.add(nums[i]);
                dfs(nums, i + 1, deep + 1, k, n - nums[i], path, result);
                path.remove(path.size() - 1);
            }
        }

    }
}
