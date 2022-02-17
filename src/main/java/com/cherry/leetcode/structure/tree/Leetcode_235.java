package com.cherry.leetcode.structure.tree;

import com.cherry.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/27 11:23
 */
public class Leetcode_235 {

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(6);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(8);
        TreeNode node21 = new TreeNode(0);
        TreeNode node22 = new TreeNode(4);
        TreeNode node23 = new TreeNode(7);
        TreeNode node24 = new TreeNode(9);
        TreeNode node33 = new TreeNode(3);
        TreeNode node34 = new TreeNode(5);
        node0.left = node11;
        node0.right = node12;
        node11.left = node21;
        node11.right = node22;
        node12.left = node23;
        node12.right = node24;
        node22.left = node33;
        node22.right = node34;

        Leetcode_235 leet = new Leetcode_235();
        TreeNode treeNode = leet.lowestCommonAncestor(node0, new TreeNode(2), new TreeNode(4));
        System.out.println(treeNode.val);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        dfs(root, p.val, pathP);

        TreeNode result = root;
        for (int i = pathP.size() - 1; i > -1; i--) {
            if (search(pathP.get(i), q.val)) {
                result = pathP.get(i);
                break;
            }
        }
        return result;

    }

    public boolean search(TreeNode root, int tagert) {
        if (root == null) {
            return false;
        }
        if (root.val == tagert) {
            return true;
        } else if(tagert > root.val) {
            return search(root.right, tagert);
        } else {
            return search(root.left, tagert);
        }
    }

    public void dfs(TreeNode root, int tagert, List<TreeNode> path) {
        path.add(root);
        if (tagert < root.val) {
            dfs(root.left, tagert, path);
        } else if (tagert > root.val) {
            dfs(root.right, tagert, path);
        }
    }
}
