package com.cherry.coding.algorithm.sreachback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/10 14:42
 */
public class Leetcode_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> currentList = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(candidates);
        dfs(candidates, 0, target, list, currentList);

        List<Integer> numK = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (Integer integer : numK) {
            sb.append(integer);
        }


        String s = "";

        return list;
    }

    public void dfs(int[] candidates, int index, int target, List<List<Integer>> result, List<Integer> currentList) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            currentList.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], result, currentList);
            currentList.remove(currentList.size() - 1);
        }

    }

}
