package com.cherry.coding.algorithm.sreachback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/10 23:03
 */
public class Leetcode_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curArrange = new ArrayList<>();
        int[][] numStatus = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            numStatus[i][0] = nums[i];
            numStatus[i][1] = 1;
        }

        doPermute(numStatus, 0, result, curArrange);
        return result;
    }

    public void doPermute(int[][] numStatus, int count, List<List<Integer>> result, List<Integer> cur) {
        if (count == numStatus.length) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < numStatus.length; i++) {
            if (numStatus[i][1] == 1) {
                cur.add(numStatus[i][0]);
                numStatus[i][1] = 0;
                doPermute(numStatus, count + 1, result, cur);
                cur.remove(cur.size() - 1);
                numStatus[i][1] = 1;
            }
        }
    }



    public static class Solution2 {
        public List<List<Integer>> permute(int[] nums) {

            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new LinkedList<>();
            boolean[] prev = new boolean[nums.length];
            dp(nums, res, path, prev);
            return res;
        }

        public void dp(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] prev) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (prev[i]) {
                    continue;
                }
                path.add(nums[i]);
                prev[i] = true;
                dp(nums, res, path, prev);
                path.remove(path.size() - 1);
                prev[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution2 s2 = new Solution2();
        List<List<Integer>> permute = s2.permute(nums);
        System.out.println(permute);
    }




}
