package com.cherry.coding.structure.tree;

import com.cherry.coding.utils.TreeNode;

import java.util.*;

public class Leetcode_103 {

    /**
     * 借助两个队列
     * 一个队列用来存每层的树节点（这个都是固定从左到右的顺序），另一个队列用来存value（需要控制从左到右或从右到左的顺序）
     */
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            boolean flag = true;
            queue.add(root);

            while (!queue.isEmpty()) {
                Deque<Integer> deque = new LinkedList<>();
                int currentSize = queue.size();
                for (int i = 0; i < currentSize; i++) {
                    TreeNode node = queue.poll();

                    // 巧妙之处在于，构造结果的时候利用队列的从头部插入和从尾部插入的方式
                    if (flag) {
                        deque.addLast(node.val);
                    } else {
                        deque.addFirst(node.val);
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                result.add(new ArrayList(deque));
                flag = !flag;
            }
            return result;

        }
    }

    /**
     * 深度优先搜索
     */
    class Solution1 {

        public List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

            dfs(root, 0, true);
            return result;
        }

        public void dfs(TreeNode node, int level, boolean flag) {
            if (node == null) {
                return ;
            }

            // 初始化
            if (result.size() == level) {
                result.add(new LinkedList<>());
            }

            LinkedList<Integer> deque = (LinkedList<Integer>)result.get(level);
            if (flag) {
                deque.addLast(node.val);
            } else {
                deque.addFirst(node.val);
            }
            if (node.left != null) {
                dfs(node.left, level + 1, !flag);
            }
            if (node.right != null) {
                dfs(node.right, level + 1, !flag);
            }
        }
    }
}
