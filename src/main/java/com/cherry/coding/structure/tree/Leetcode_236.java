package com.cherry.coding.structure.tree;

import com.cherry.coding.utils.TreeNode;

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
    class Solution1 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // stop
            if (root == null || root.val == p.val || root.val == q.val) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) {
                return root;
            }
            return left == null ? right : left;
        }
    }

















}
