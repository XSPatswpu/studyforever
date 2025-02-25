package com.cherry.coding.structure.tree;

import com.cherry.coding.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_106 {

    /**
     * 说一说关于递归的心得：
     * 1、找出递归公式，即：求解当前问题，也就是要求解当前问题的一个或多个子问题
     * 用在本题中可归纳为：
     * 核心步骤：构造一颗二叉树，只需要构造 当前节点 和 当前节点的左儿子树 以及 当前节点的右儿子树 就好了
     * 而 构造左、右儿子树 又可以采用 核心步骤 去构造。
     *
     * 递归一定是存在重复步骤的！上面的核心步骤就是在一直重复！
     *
     * 还有一个体会就是：架构 > 实现细节
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for ( int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return build(0, inorder.length - 1, postorder, postorder.length - 1, inorderMap);
    }

    public TreeNode build(int l, int r, int[] postorder, int rootIndex, Map<Integer, Integer> inorderMap) {
        if (l > r) {
            return null;
        }
        int rootVal = postorder[rootIndex];
        TreeNode node = new TreeNode(postorder[rootIndex]);
        int index = inorderMap.get(rootVal);

        // right
        node.right = build(index + 1, r, postorder, rootIndex - 1, inorderMap);
        // left
        node.left = build(l, index - 1, postorder, rootIndex - (r - index + 1), inorderMap);
        return node;
    }
}
