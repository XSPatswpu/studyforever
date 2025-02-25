package com.cherry.coding.structure.stack;

import java.util.Arrays;

/**
 * @author: xiangshaopeng
 * @date: 2019-12-23 15:35
 */
public class MyStack{
    private int[] elementData = new int[10];

    private int elementCount;

    public int[] getElements() {
        return Arrays.copyOfRange(elementData,0, elementCount);
    }

    public void push(int node) {
        sureCapacity(elementCount);
        elementData[elementCount++] = node;
    }

    private void sureCapacity(int elementCount) {
        if (elementCount >= size()) {
            elementData = Arrays.copyOf(elementData, size() << 1);
        }
    }

    public int pop() {
        int popValue = elementData[elementCount - 1];
        elementCount--;
        isShrinkCapacity(elementCount);
        return popValue;
    }

    private void isShrinkCapacity(int elementCount) {
        if ((elementCount << 1) <= size()) {
            elementData = Arrays.copyOf(elementData, size() >> 1);
        }
    }

    public int top() {
        return elementData[elementCount - 1];
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * 栈结构外壳的大小
     */
    public int size() {
        return elementData.length;
    }
}
