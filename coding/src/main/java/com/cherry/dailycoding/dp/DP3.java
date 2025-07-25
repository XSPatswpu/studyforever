package com.cherry.dailycoding.dp;

public class DP3 {

       /*
    //给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
//
// 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
//
// 请你计算并返回达到楼梯顶部的最低花费。
//
//
//
// 示例 1：
//
//
//输入：cost = [10,15,20]
//输出：15
//解释：你将从下标为 1 的台阶开始。
//- 支付 15 ，向上爬两个台阶，到达楼梯顶部。
//总花费为 15 。
//
//
// 示例 2：
//
//
//输入：cost = [1,100,1,1,1,100,1,1,100,1]
//输出：6
//解释：你将从下标为 0 的台阶开始。
//- 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
//- 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
//- 支付 1 ，向上爬一个台阶，到达楼梯顶部。
//总花费为 6 。
//
//
//
//
// 提示：
//
//
// 2 <= cost.length <= 1000
// 0 <= cost[i] <= 999
//
//
// Related Topics 数组 动态规划 👍 1670 👎 0

     */

    /**
     * lc 746
     * 1. dp[i] 表示爬到 i 号 台阶的最低花费
     * 2. dp[i] = Math.min((dp[i - 1] + cost[i - 1]), (dp[i - 2] + cost[i - 2]));
     * 3. dp[0] = cost[0] dp[1] = cost[1]
     * 4. 以 cost 数组为基础，从左往右迭代
     * 5. dp[2] dp[3] dp[4] ... dp[i]
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = Math.min(dp[1] + cost[1], dp[0] + cost[0]);
        for (int i = 3; i < dp.length; i++) {
            dp[i] = Math.min((dp[i - 1] + cost[i - 1]), (dp[i - 2] + cost[i - 2]));
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        DP3 d = new DP3();
        int[] a = {10, 15, 20};
        int i = d.minCostClimbingStairs(a);
        System.out.println(i);
    }
}
