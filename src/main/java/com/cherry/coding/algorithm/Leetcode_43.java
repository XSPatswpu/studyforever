package com.cherry.coding.algorithm;

import java.util.Stack;

public class Leetcode_43 {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Leetcode_43 c = new Leetcode_43();
        int trap = c.trap2(height);
        System.out.println(trap);
    }

    /**
     * 1. 直接法
     * <p>
     * 判断当前格子是否能接雨水，也就是当前格子是否形成低洼？左右两边的最大值都比当前格子大
     * <p>
     * 如何求当前格子所接雨水量？
     * 寻找当前各自左边的最大值 leftMax 和右边 的最大值 rightMax，求其最小值 min(leftMax, rightMax)，然后减去当前格子的高度
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {

        int sum = 0;
        // 写代码前先梳理代码框架
        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1) {
                continue;
            }

            int leftMax = getLeftMax(i, height);
            int rightMax = getRightMax(i, height);
            // 判断是否形成低洼
            if (leftMax > height[i] && rightMax > height[i]) {
                int current = Math.min(leftMax, rightMax) - height[i];
                sum += current;
            }
        }
        return sum;
    }

    public int getLeftMax(int index, int[] height) {

        int max = 0;
        for (int i = 0; i < index; i++) {
            max = Math.max(height[i], max);
        }
        return max;
    }

    public int getRightMax(int index, int[] height) {
        int max = 0;
        for (int i = index + 1; i < height.length; i++) {
            max = Math.max(height[i], max);
        }
        return max;
    }

    /**
     * 2. 优化直接法
     * 因为直接法在计算每根柱子时，需要前后遍历查找，所以需要提前储存每个元素从左到右的最大值和从右到左的最大值。
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        // 储存每个格子左边的最大值
        // 只需要从左往右遍历一遍数组即可
        int[] leftMaxArray = new int[height.length];
        int leftMax = 0;
        for (int i = 0; i < leftMaxArray.length; i++) {
            leftMaxArray[i] = leftMax;
            leftMax = Math.max(leftMax, height[i]);
        }

        int[] rightMaxArray = new int[height.length];
        int rightMax = 0;
        for (int i = rightMaxArray.length - 1; i >= 0; i--) {
            rightMaxArray[i] = rightMax;
            rightMax = Math.max(rightMax, height[i]);
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1) {
                continue;
            }

            int left = leftMaxArray[i];
            int right = rightMaxArray[i];
            // 判断是否形成低洼
            if (left > height[i] && right > height[i]) {
                int current = Math.min(left, right) - height[i];
                sum += current;
            }
        }
        return sum;
    }

    /**
     * 单调栈
     *
     * 横向计算每个低洼的积水量
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        // 写代码前，先思考清楚框架
        Stack<Integer> st = new Stack<>();
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[st.peek()] < height[i]) {
                Integer cur = st.pop();
                // 还要获取左边界
                if (st.isEmpty()) {
                    break;
                }
                Integer left = st.peek();
                // 计算低洼积水量
                sum += (i - left - 1) * (Math.min(height[left], height[i]) - height[cur]);
            }
            st.push(i);
        }
        return sum;
    }
}
