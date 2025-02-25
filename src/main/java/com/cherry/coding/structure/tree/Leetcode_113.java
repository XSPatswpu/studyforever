package com.cherry.coding.structure.tree;

import com.cherry.coding.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_113 {

    /**
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(5);
        TreeNode node11 = new TreeNode(4);
        TreeNode node12 = new TreeNode(8);
        TreeNode node21 = new TreeNode(11);
        TreeNode node22 = new TreeNode(13);
        TreeNode node23 = new TreeNode(4);
        TreeNode node31 = new TreeNode(7);
        TreeNode node32 = new TreeNode(2);
        TreeNode node33 = new TreeNode(5);
        TreeNode node34 = new TreeNode(1);

        node0.left = node11;
        node0.right = node12;

        node11.left = node21;
        node12.left = node22;
        node12.right = node23;

        node21.left = node31;
        node21.right = node32;
        node23.left = node33;
        node23.right = node34;

        Leetcode_113 leetcode = new Leetcode_113();
        List<List<Integer>> lists = leetcode.pathSum(node0, 22);
        System.out.println(lists);
    }


    // 树的遍历方式有很3中，可以思考下，是不是3种遍历方式都能行得通
    // 先序遍历
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(root, sum, path, result);
        return result;
    }

    private int pathSum = 0;

    /**
     * 注意基本数据类型在递归调用栈中传递都时候和引用数据类型都区别
     *
     * 基本数据类型，必须要维护一个公公变量，才能达到和引用数据类型一样都效果！
     *
     * @param root
     * @param sum
     * @param path
     * @param result
     */
    void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {

        path.add(root.val);
        pathSum += root.val;

        if (root.left == null && root.right == null) {
            // result
            if (sum == pathSum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        if (pathSum >= sum) {
            return;
        }


        if (root.left != null) {
            dfs(root.left, sum, path, result);
            pathSum = pathSum - path.get(path.size() - 1);
            path.remove(path.size() - 1);

        }

        if (root.right != null) {
            dfs(root.right, sum, path, result);
            pathSum = pathSum - path.get(path.size() - 1);
            path.remove(path.size() - 1);
        }

    }
}
