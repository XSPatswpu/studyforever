package com.cherry.dailycoding.dp;

public class DP1 {
    // lc 509
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 1. 确定 dp 数据及下标的含义
     * 3. 寻找递推公式
     * 2. 初始化 dp[0] dp[1]
     * 4. 确定推导顺序
     * 5. 举例推导
     *
     */
    public int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib3(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

}
