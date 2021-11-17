package com.cherry.algorithm.dynamicprogramming;

import java.util.Arrays;

public class Leetcode_322 {
    public static void main(String[] args) {

        int[] coins = {1, 3, 5};
        int amount = 11;
        int i = coinChange(coins, amount);
        System.out.println(i);

    }

    public static int coinChange(int[] coins, int amount) {
        // 状态数组
        int[] status = new int[amount + 1];

        // 用不可达的最大值填充状态数组
        Arrays.fill(status, amount + 1);

        // 初始化初始值
        status[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    status[i] = Math.min(status[i], status[i - coins[j]] + 1);
                }
            }
        }

        return status[amount] == (amount + 1) ? -1 : status[amount];
    }
}
