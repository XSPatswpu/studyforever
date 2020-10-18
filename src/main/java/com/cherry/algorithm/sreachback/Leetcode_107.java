package com.cherry.algorithm.sreachback;


import com.cherry.algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode_107 {

    /**
     * 考察树的搜索方式：
     * 1、深度优先搜素，自然递归即可实现
     * 2、广度优先搜索，借助队列，把树的根、左儿子、右儿子依次入队列
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                list.add(node.val);
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                }

                if (node.right != null) {
                    nodeQueue.offer(node.right);
                }
            }
            result.add(0, list);
        }
        return result;
    }

    /**
     * dfs
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom_1(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(root, 0, result);
        return result;
    }

    public void dfs(TreeNode root, int deep, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (deep == result.size()) {
            result.add(0, new ArrayList<>());
        }
        result.get(result.size()-deep-1).add(root.val);
        dfs(root.left, deep + 1, result);
        dfs(root.right, deep + 1, result);
    }

}
