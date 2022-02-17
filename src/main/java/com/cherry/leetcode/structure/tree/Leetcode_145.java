package com.cherry.leetcode.structure.tree;

import com.cherry.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/1 21:59
 */
public class Leetcode_145 {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        TreeNode node31 = new TreeNode(3);
        node1.right = node21;
        node21.left = node31;

        Leetcode_145 leetcode = new Leetcode_145();
        List<Integer> integers = leetcode.postorderTraversal(node1);
        System.out.println(integers);

    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // root.right == null || root.right == pre 这个判断语句想了好久都没想出来
            // 可能是必须要再维护一个 pre 指针 来判断是不是倒退过来的，防止出现死循环
            if (root.right == null || root.right == pre) {
                list.add(root.val);
                pre = root;
                root = null;
            } else {
                // 这两句话也很金典，只 push 当前指针，而让 root 指向右节点
                stack.push(root);
                root = root.right;
            }
        }
        return list;
    }

    public List<Integer> doSearch_01(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        doSearch_01(root.left, list);
        doSearch_01(root.right, list);
        list.add(root.val);
        return list;
    }


}
