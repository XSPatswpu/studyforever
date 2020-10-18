package com.cherry.algorithm.tree;

import com.cherry.algorithm.utils.TreeNode;

public class Leetcode_404 {

    /**
     * 树的遍历方式：
     * 1、根 -> 左 -> 右 前序遍历
     * 2、左 -> 右 -> 根 后续遍历
     * 3、左 -> 根 -> 右 中续遍历
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int sum = 0;
        return doSumTree(root.left, 1, sum) + doSumTree(root.right, 2, sum);
    }
    public int doSumTree(TreeNode root, int dir, int sum) {
        if (root == null) {
            return sum;
        }

        if (root.left == null && root.right == null) {
            if (dir == 1) {
                sum += root.val;
            }
            return sum;
        }
        return doSumTree(root.left, 1, sum) + doSumTree(root.right, 2, sum);
    }
}
