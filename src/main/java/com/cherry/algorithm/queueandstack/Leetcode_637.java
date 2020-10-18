package com.cherry.algorithm.queueandstack;

import com.cherry.algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_637 {
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> countList = new ArrayList<>();
        List<Double> sumList = new ArrayList<>();

        dfs(root, 0, countList, sumList);

        // 处理结果

        List<Double> averageList = new ArrayList<>();
        for (int i = 0; i < countList.size(); i++) {
            averageList.add(sumList.get(i) / countList.get(i));
        }
        return averageList;
    }

    public void dfs(TreeNode node, int deep, List<Double> countList, List<Double> sumList) {
        if (node == null) {
            return;
        }

        if (deep > countList.size() - 1) {
            countList.add(1.0);
            sumList.add((double) node.val);
        } else {
            countList.set(deep, countList.get(deep) + 1);
            sumList.set(deep, sumList.get(deep) + node.val);
        }

        dfs(node.left, deep + 1, countList, sumList);
        dfs(node.right, deep + 1, countList, sumList);

    }
}
