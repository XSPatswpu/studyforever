package com.cherry.dailycoding.dp;

public class DP2 {
    // lc 70

    /**
     * n > 0
     * 1. 定义 dp 数组：dp[n]
     * 2. 寻找递推公式：定义dp[n] = dp[n - 1] + dp[n - 2]
     * 3. 初始化递推公式的依赖的边界值：dp[1] = 1 dp[2] = 2
     * 4. 确定递推顺序
     * 5. 举例推导 dp 数组
     */
    public int func(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
