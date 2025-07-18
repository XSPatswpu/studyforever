package com.cherry.coding.algorithm.dynamicprogramming;

import com.cherry.coding.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_337 {
    public static void main(String[] args) {

    }

    /**
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额.
     */
    public int rob(TreeNode root) {

        if (root == null) {
            return 0;
        }

        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));

    }

    // 从当前节点往下求和，选择当前节点
    Map<TreeNode, Integer> f = new HashMap<>();

    // 从当前节点往下求和，不选择当前节点
    Map<TreeNode, Integer> g = new HashMap<>();


    public void dfs(TreeNode node) {

        if (node == null) {
            return;
        }

        dfs(node.left);
        dfs(node.right);

        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


}
