package com.cherry.algorithm.dynamicprogramming;

import com.cherry.algorithm.utils.ArrayUtil;

/**
 * @author: xiangshaopeng
 * @date: 2020/8/31 11:35
 */
public class Test_01 {


    private int currentW = Integer.MIN_VALUE;

    /**
     * 0 - 1 背包问题
     * @param maxNumber 物品数量
     * @param maxWeight 最大背包重量
     * @param items {2,2,3,3,5,8}
     */
    public void optimal(int maxNumber, int maxWeight, int[] items) {


        boolean[][] result = new boolean[maxNumber][maxWeight + 1];

        result[0][0] = true;
        if (items[0] <= maxWeight) {
            result[0][items[0]] = true;
        }

        for (int i = 1; i < maxNumber; i++) {

            // 不进背包
            for (int j = 0; j <= maxWeight; j++) {
                if (result[i - 1][j]) {
                    result[i][j] = result[i - 1][j];
                }
            }

            // 进背包
            for (int j = 0; j <= maxWeight; j++) {
                if (result[i - 1][j] && j + items[i] <= maxWeight) {
                    result[i][j + items[i]] = true;
                }
            }

        }

        ArrayUtil.print(result);
    }

    /**
     * 0-1 背包问题有很多变体，我这里介绍一种比较基础的。
     * 我们有一个背包，背包总的承载重量是 Wkg。
     * 现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
     * 我们现在期望选择几件物品，装载到背包中。
     * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
     * @param args
     */
    public static void main(String[] args) {

//        int[] a = {1, 2, 3, 4};
//        int[] b = {3, 4, 5, 6};
//        int[] c = {6, 7, 8, 9};
//
//        int[][] aa = new int[3][4];
//        aa[0] = a;
//        aa[1] = b;
//        aa[2] = c;
//        ArrayUtil.print(aa);
        Test_01 test = new Test_01();
        int[] arr = {2,2,3,4,5,8};
        test.optimal(5, 14, arr);

    }
}
