package com.cherry.algorithm;

import com.cherry.algorithm.utils.TreeNode;

public class Leetcode_236 {
    /**
     * 递归，注意终止条件
     *
     * 有空多看看题解吧
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null) {
                return right;
            }

            if (right == null) {
                return left;
            }

            return root;
        }
    }
}
