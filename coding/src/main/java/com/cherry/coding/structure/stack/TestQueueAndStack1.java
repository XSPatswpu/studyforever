package com.cherry.coding.structure.stack;

import java.util.Objects;
import java.util.Stack;

/**
 * @author: xiangshaopeng
 * @date: 2019-11-27 14:42
 */
public class TestQueueAndStack1 {
    /**
     * 定义栈的数据结构，
     * 请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     */
    public static void main(String[] args) {

    }

    /**
     * 主栈
     */
    private Stack<Integer> mainStack = new Stack<>();
    /**
     * 储存最小元素栈
     */
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        mainStack.push(node);
        if (minStack.isEmpty()) {
            minStack.push(node);
        } else if (node <= minStack.peek()) {
            minStack.push(node);
        }
    }

    public void pop() {
        if (Objects.equals(mainStack.peek(), minStack.peek())) {
            minStack.pop();
        }
        mainStack.pop();
    }

    public int top() {
        return mainStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

}
