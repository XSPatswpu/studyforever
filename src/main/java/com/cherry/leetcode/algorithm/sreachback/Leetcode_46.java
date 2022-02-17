package com.cherry.leetcode.algorithm.sreachback;

import java.util.ArrayList;
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
}
