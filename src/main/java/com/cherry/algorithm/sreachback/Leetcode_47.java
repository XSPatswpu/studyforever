package com.cherry.algorithm.sreachback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_47 {

    /**
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int[] arrStatus = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arrStatus[i] = 1;
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int len = nums.length;
        dfs(nums, arrStatus, len, 0, result, path);
        return result;
    }

    private void dfs(int[] nums, int[] arrStatus, int len, int deep, List<List<Integer>> result, List<Integer> path) {
        if (deep == len) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int j = 0; j < len; j++) {
            if (arrStatus[j] == 0 || (j > 0 && arrStatus[j - 1] == 1 && nums[j] == nums[j - 1])) {
                continue;
            }
            if (arrStatus[j] == 1) {
                path.add(nums[j]);
                arrStatus[j] = 0;
                dfs(nums, arrStatus, len, deep + 1, result, path);
                path.remove(path.size() - 1);
                arrStatus[j] = 1;
            }
        }

    }

    public static void main(String[] args) {
        Leetcode_47 leetcode47 = new Leetcode_47();

        int[] arr = {1, 1, 2,4};
        List<List<Integer>> lists = leetcode47.permuteUnique(arr);
        System.out.println(lists);
    }


}
