package com.cherry.algorithm.tree;

import utils.ArrayUtil;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/24 16:49
 */
public class Leetcode_501 {
    //[4,3,6,2,4,6,7,null,null,4,4,6]
    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(2);
//        node1.right = node2;
//        node2.left = node3;
        TreeNode node0 = new TreeNode(4);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        node0.left = node1;
        node0.right = node2;
        TreeNode node1le = new TreeNode(2);
        TreeNode node1ri = new TreeNode(4);
        node1.left = node1le;
        node1.right = node1ri;
        TreeNode node2le = new TreeNode(6);
        TreeNode node2ri = new TreeNode(7);
        node2.left = node2le;
        node2.right = node2ri;

        TreeNode node1rile = new TreeNode(4);
        TreeNode node1riri = new TreeNode(4);
        node1ri.left = node1rile;
        node1ri.right = node1riri;
        TreeNode node2lele = new TreeNode(6);
        node2le.left = node2lele;



        Leetcode_501 leetcode_501 = new Leetcode_501();
        int[] mode = leetcode_501.findMode(node0);
        ArrayUtil.print(mode);


    }


    public int[] findMode(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        search(root, list);
        LinkedList<Integer> queue = new LinkedList<>();
        int count = 0;
        int start = 0;
        for (int i = 0; i < list.size(); i++) {
            if ((i == list.size() - 1) || ((i < list.size() - 1) && (!list.get(i).equals(list.get(i + 1))))) {
                int curCount = i - start + 1;
                if (curCount > count) {
                    count = curCount;
                    if (!queue.isEmpty()) {
                        queue.poll();
                    }
                    queue.offer(list.get(i));
                } else if (curCount == count) {
                    queue.offer(list.get(i));
                }
                start = i + 1;
            }
        }

        int[] result = new int[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            result[i] = queue.get(i);
        }
        return result;

    }

    public void search(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        search(root.left, list);
        list.add(root.val);
        search(root.right, list);
    }
}
