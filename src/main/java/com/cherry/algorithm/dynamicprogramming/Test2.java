package com.cherry.algorithm.dynamicprogramming;

public class Test2 {

    /**
     * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。
     * 矩阵存储的都是正整数。棋子起始位置在左上角，
     * 终止位置在右下角。我们将棋子从左上角移动到右
     * 下角。每次只能向右或者向下移动一位。从左上角
     * 到右下角，会有很多不同的路径可以走。我们把每
     * 条路径经过的数字加起来看作路径的长度。那从左
     * 上角移动到右下角的最短路径长度是多少呢？
     */
    /**
     * 感觉这个解法从本质上来说，就像是在填满status 的状态
     * @param w
     * @param cp
     */
    public static void dynamic1(int[][] w, int cp) {
        int[][] status = new int[w.length][w.length];

        // 初始值特殊处理
        status[0][0] = w[0][0];
        for (int j = 1; j < w.length; j++) {
            status[0][j] = status[0][j - 1] + w[0][j];
        }

        for (int i = 1; i < w.length; i++) {
            status[i][0] += status[i - 1][0] + w[i][0];
        }

        for (int i = 1; i < w.length; i++) {
            for (int j = 1; j < w.length; j++) {
                if (status[i - 1][j] <= status[i][j - 1]) {
                    status[i][j] = status[i - 1][j] + w[i][j];
                } else {
                    status[i][j] = status[i][j - 1] + w[i][j];
                }
            }
        }

    }

    /**
     * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。
     * 如果我们要支付 w 元，求最少需要多少个硬币。
     * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
     */

}
