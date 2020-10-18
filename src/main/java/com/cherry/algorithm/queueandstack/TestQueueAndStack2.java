package com.cherry.algorithm.queueandstack;

/**
 * @author: xiangshaopeng
 * @date: 2019-11-27 14:42
 */
public class TestQueueAndStack2 {
    /**
     * 定义栈的数据结构
     */
    public static void main(String[] args) {
        TestQueueAndStack2 test = new TestQueueAndStack2();
        MyStack testStack = new MyStack();
        for (int i = 0; i < 11; i++) {
            testStack.push(i);
        }

        for (int i = 0; i < 11; i++) {
            System.out.println(testStack.top());
            testStack.pop();
        }

    }

    /**
     * 主栈
     */
    private MyStack mainStack = new MyStack();
    /**
     * 储存最小元素栈
     */
    private MyStack minStack = new MyStack();

    public void push(int node) {
        mainStack.push(node);
        if (minStack.isEmpty()) {
            minStack.push(node);
        } else if (node <= minStack.top()) {
            minStack.push(node);
        }
    }

    public void pop() {
        if (mainStack.top() == minStack.top()) {
            minStack.pop();
        }
        mainStack.pop();
    }

    public int top() {
        return mainStack.top();
    }

    public int min() {
        return minStack.top();
    }

}
