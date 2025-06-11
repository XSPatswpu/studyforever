package com.cherry.coding.structure.tree;

import com.cherry.coding.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/14 18:26
 */
public class Leetcode_94 {
    /**
     *    1
     *     \
     *      2
     *     /
     *    3
     */
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;

        Leetcode_94 leet = new Leetcode_94();
        List<Integer> integers = leet.inorderTraversal(node1);
        System.out.println(integers);

    }


    /**
     * 理解迭代的思路：
     * 和递归很相似，找到每次循环的共性，就是每次处理节点的重复动作
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public void doInorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            doInorderTraversal(root.left, list);
        }

        list.add(root.val);

        if (root.right != null) {
            doInorderTraversal(root.right, list);
        }
    }
}
