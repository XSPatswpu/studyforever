package com.cherry.leetcode.algorithm.dynamicprogramming;

import com.cherry.leetcode.utils.ArrayUtil;

/**
 * @author: xiangshaopeng
 * @date: 2020/8/31 11:35
 */
public class Test_01 {


//    private int currentW = Integer.MIN_VALUE;
//
//    /**
//     * 0 - 1 背包问题
//     * @param maxNumber 物品数量
//     * @param maxWeight 最大背包重量
//     * @param items {2,2,3,3,5,8}
//     */
//    public void optimal(int maxNumber, int maxWeight, int[] items) {
//
//
//        boolean[][] result = new boolean[maxNumber][maxWeight + 1];
//
//        result[0][0] = true;
//        if (items[0] <= maxWeight) {
//            result[0][items[0]] = true;
//        }
//
//        for (int i = 1; i < maxNumber; i++) {
//
//            // 不进背包
//            for (int j = 0; j <= maxWeight; j++) {
//                if (result[i - 1][j]) {
//                    result[i][j] = result[i - 1][j];
//                }
//            }
//
//            // 进背包
//            for (int j = 0; j <= maxWeight; j++) {
//                if (result[i - 1][j] && j + items[i] <= maxWeight) {
//                    result[i][j + items[i]] = true;
//                }
//            }
//
//        }
//
//        ArrayUtil.print(result);
//    }




    private static int result = Integer.MIN_VALUE;

    /**
     * 回溯
     * @param n
     * @param cw
     * @param items
     * @param limit
     */
    public static void sdf(int n, int cw, int[] items, int limit) {
        if (n == items.length || cw == limit) {
            if (cw > result) {
                result = cw;
            }
            return;
        }

        // 不选择当前物品
        sdf(n + 1, cw, items, limit);

        // 选择当前物品
        if (cw + items[n] <= limit) {
            sdf(n + 1, cw + items[n], items, limit);
        }
    }


    /**
     * 0-1 背包问题有很多变体，我这里介绍一种比较基础的。
     * 我们有一个背包，背包总的承载重量是 Wkg。
     * 现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
     * 我们现在期望选择几件物品，装载到背包中。
     * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
     */
    public static void dynamic(int n, int[] items, int lw) {
        boolean[][] status = new boolean[n][lw + 1];

        // 第一个物品做特殊处理

        // 不进背包
        status[0][0] = true;

        // 进背包
        status[0][items[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= lw; j++) {

                if (status[i - 1][j]) {
                    // 不进背包
                    status[i][j] = true;


                    // 进背包
                    if (j + items[i] <= lw) {
                        status[i][j + items[i]] = true;
                    }

                }

            }
        }

//        for (int i = lw; i >= 0; i--) {
//            if (status[n - 1][i]) {
//                System.out.println(i);
//                break;
//            }
//        }

        ArrayUtil.print(status);

    }

    /**
     * 背包问题升级版
     * 物品重量 items = {2，2，4，6，3}
     * 物品价格 values = {3，4，8，9，6}
     * 背包承受的最大重量 w = 9
     * 在不超过背包总重量的情况下，使得背包的总价值最大
     *
     */
    private static void dynamic2(int n, int mw, int[] items, int[] values) {
        // 注意状态数组只记录价值最高的状态

        int[][] status = new int[n][mw + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= mw; j++) {
                status[i][j] = -1;
            }
        }


        // 第一个物品特殊处理
        status[0][0] = 0;
        status[0][items[0]] = values[0];

        for (int i = 1; i < n; i++) {

            for (int j = 0; j <= mw; j++) {
                if (status[i - 1][j] >= 0) {
                    // 不进背包
                    if (status[i][j] < status[i - 1][j]) {
                        status[i][j] = status[i - 1][j];
                    }

                    // 进背包
                    if (j + items[i] <= mw) {
                        if (status[i][j + items[i]] < status[i - 1][j] + values[i]) {
                            status[i][j + items[i]] = status[i - 1][j] + values[i];
                        }
                    }
                }
            }


        }

        ArrayUtil.print(status);


    }


    public static void knapsack3(int n, int w, int[] weight, int[] value) {
        int[][] states = new int[n][w+1];
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第i个物品
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        ArrayUtil.print(states);
    }

    /**
     * 满 200 元减 50 元
     * 购物车中有 n 个（n>100）想买的商品，她希望从里面选几个，在凑够满减条件的前提下，
     * 让选出来的商品价格总和最大程度地接近满减条件（200 元）
     *
     * 商品总价大于200元，且最低
     *
     */
    public static void dynamic4(int n, int minPrice, int maxPrice, int[] price) {

        boolean[][] status = new boolean[n][maxPrice + 1];


        // int[0][0]
        status[0][0] = true;
        status[0][price[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < maxPrice + 1; j++) {
                if (status[i - 1][j]) {

                    // 不购买
                    status[i][j] = status[i - 1][j];

                    // 购买
                    if (j + price[i] <= maxPrice) {
                        if (j < minPrice) {
                            status[i][j + price[i]] = true;
                        }
                    }
                }
            }
        }

        int i = n - 1;
        int j;
        for (j = maxPrice; j > -1; j--) {
            if (status[i][j]) {
                break;
            }
        }

        for (; i > -1; i--) {
            if (i > 0 && !status[i - 1][j]) {
                System.out.println(price[i]);

                // 确定新状态
                if (j - price[i] >= 0) {
                    j = j - price[i];
                }
            }
        }

    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4};
        int[] b = {3, 4, 5, 6};
        int[] c = {6, 7, 8, 9};
        int[] d = {2, 2, 4, 6, 3};

        int[] items = {2, 2, 4, 6, 3};
        int[] values = {3,4,8,9,6};

//        int[][] aa = new int[3][4];
//        aa[0] = a;
//        aa[1] = b;
//        aa[2] = c;
//        ArrayUtil.print(aa);
//        Test_01 test = new Test_01();
//        int[] arr = {2,2,3,4,5,8};
//        test.optimal(5, 14, arr);
//        sdf(0, 0, a, 7);
//        System.out.println(result);

//        dynamic(5, d, 9);


//        knapsack3(5, 9, items, values);

        int[] price = {50, 39, 100, 17, 48, 30, 26, 78};

        dynamic4(8, 200, 1000, price);
    }

}
