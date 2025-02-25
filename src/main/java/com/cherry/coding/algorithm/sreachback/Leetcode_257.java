package com.cherry.coding.algorithm.sreachback;

import com.cherry.coding.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_257 {
    /**
     * 257. 二叉树的所有路径
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<Integer> path = new LinkedList<>();
        return doBinaryTreePaths(root, list, path);
    }

    public List<String> doBinaryTreePaths(TreeNode root, List<String> list, LinkedList<Integer> path) {
        path.add(root.val);

        if (root.left == null && root.right == null) {
            storePath(list, path);
            path.removeLast();
            return list;
        }

        if (root.left != null) {
            list = doBinaryTreePaths(root.left, list, path);
        }

        if (root.right != null) {
            list = doBinaryTreePaths(root.right, list, path);
        }

        path.removeLast();

        return list;
    }

    private void storePath(List<String> list, LinkedList<Integer> path) {
        StringBuilder ab = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            ab.append(path.get(i));
            if (i != path.size() - 1) {
                ab.append("->");
            }
        }
        list.add(ab.toString());
    }
}
