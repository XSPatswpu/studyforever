package com.cherry.leetcode.algorithm.sreachback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/10 14:39
 */
public class Leetcode_39 {

    public List<List<Integer>> list = new ArrayList<>();

    public List<Integer> current = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        doCombinationSum(candidates, target, list, current, 0);

        return list;
    }

    /**
     * 对于每一个数字，有 选择、跳过 两种决策方式。
     * @param candidates
     * @param target
     * @param list
     * @param currentList
     * @param index
     */
    public void doCombinationSum(int[] candidates, int target, List<List<Integer>> list, List<Integer> currentList, int index) {
        if (index == candidates.length) {
            return;
        }

        if (target == 0) {
            list.add(new ArrayList(currentList));
            return;
        }

        if (target - candidates[index] >= 0) {
            currentList.add(candidates[index]);
            doCombinationSum(candidates, target - candidates[index], list, currentList, index);
            currentList.remove(currentList.size() - 1);
        }

        doCombinationSum(candidates, target, list, currentList, index + 1);

    }
}
