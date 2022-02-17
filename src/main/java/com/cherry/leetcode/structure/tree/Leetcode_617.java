package com.cherry.leetcode.structure.tree;

import com.cherry.leetcode.utils.TreeNode;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/24 17:29
 */
public class Leetcode_617 {

    public void search(TreeNode node) {
        if (node == null) {
            return;
        }

    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1, t2);
        node.right = mergeTrees(t1, t2);
        return node;
    }
}
