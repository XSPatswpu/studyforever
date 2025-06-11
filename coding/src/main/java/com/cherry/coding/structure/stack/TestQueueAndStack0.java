package com.cherry.coding.structure.stack;

import java.util.Stack;

/**
 * @author: xiangshaopeng
 * @date: 2019-11-21 17:19
 */
public class TestQueueAndStack0 {
    public static void main(String[] args) {

    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 用两个栈来实现一个队列，
     * 完成队列的Push和Pop操作。
     * 队列中的元素为int类型。
     */

    /**
     * 分析：
     * stack1栈负责存入push的元素
     * stack2栈负责存放pop的元素
     */
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack1.empty() && stack2.empty()) {
            return -1;
        }

        if (stack2.empty()) {
            // 把stack1中的数据存入stack2中
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
