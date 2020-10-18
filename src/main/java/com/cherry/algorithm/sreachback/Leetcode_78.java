package com.cherry.algorithm.sreachback;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_78 {
    /**
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, 0, 0, i, result, path);
        }
        return result;
    }

    private void dfs(int[] nums, int index, int deep, int total, List<List<Integer>> result, List<Integer> path) {
        if (deep == total) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, index + 1, deep + 1, total, result, path);
            path.remove(path.size() - 1);
        }
    }
}
