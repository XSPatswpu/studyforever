package com.cherry.leetcode.structure.stack;

import java.util.Stack;

/**
 * @author: xiangshaopeng
 * @date: 2019-11-27 17:41
 */
public class TestQueueAndStack3 {
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，
     * 请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     * （注意：这两个序列的长度是相等的）
     */
    public static void main(String[] args) {
        int[] push = {1,2,3,4,5};
        int[] pop = {4,5,3,2,1};
        int[] pop1 = {4,3,5,1,2};
        int[] pop2 = {1,2,3,4,5};
        int[] pop3 = {3,4,2,1,5};
        TestQueueAndStack3 test = new TestQueueAndStack3();
        System.out.println(test.IsPopOrder(push, pop3));

    }

    /**
     * return false 条件：辅助栈的top元素不等于弹出数组中的目标元素 && 压栈数组全部压入辅助栈中也没找到弹出数组中的目标元素（压栈数组的索引指向length）
     * return true 条件：弹出数组的索引指向最后一个元素
     * TODO 待优化
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null || popA == null || pushA.length == 0) {
            return false;
        }
        if (pushA.length == 1) {
            return pushA[0] == popA[0];
        }
        Stack<Integer> tempStack = new Stack<>();
        for (int popIndex = 0, pushIndex = 0; popIndex < popA.length - 1; popIndex++) {
            if (!tempStack.isEmpty()) {
                if (tempStack.peek() == popA[popIndex]) {
                    tempStack.pop();
                    continue;
                }
            }
            if (pushIndex == pushA.length) {
                return false;
            }
            while (pushA[pushIndex] != popA[popIndex]) {
                tempStack.push(pushA[pushIndex]);
                pushIndex++;
            }
            pushIndex++;
        }
        return true;
    }
}
