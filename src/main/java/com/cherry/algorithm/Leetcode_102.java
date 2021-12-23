package com.cherry.algorithm;

import com.cherry.algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode_102 {

    public static void main(String[] args) {

    }


    /**
     * 深度优先
     */
    class Solution {

        public List<List<Integer>> list = new ArrayList<>();
        public List<List<Integer>> levelOrder(TreeNode root) {
            dfs(root, 0);
            return list;
        }

        public void dfs(TreeNode node, int level) {
            if (node == null) {
                return;
            }

            if (list.size() == level) {
                list.add(new ArrayList<>());
            }
            list.get(level).add(node.val);
            if (node.left != null) {
                dfs(node.left, level + 1);
            }
            if (node.right != null) {
                dfs(node.right, level + 1);
            }
            return;
        }

    }

    /**
     * 队列
     */
    class Solution1 {

        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> queue = new LinkedList<>();

            queue.add(root);

            while (!queue.isEmpty()) {
                List<Integer> levelList = new ArrayList<>();
                // 当前层级的大小
                int currentLevelSize = queue.size();
                for (int i = 0; i < currentLevelSize; i++) {
                    TreeNode node = queue.poll();
                    levelList.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                result.add(new ArrayList(levelList));

            }
            return result;
        }


    }
}
