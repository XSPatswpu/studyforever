package com.cherry.algorithm.tree;

import com.cherry.algorithm.utils.TreeNode;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/21 20:38
 */
public class Leetcode_538 {

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    private int sum = 0;
    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        sum += root.val;
        root.val = sum;
        dfs(root.left);
    }
}
