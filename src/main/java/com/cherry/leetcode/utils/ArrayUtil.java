package com.cherry.leetcode.utils;

/**
 * @author: xiangshaopeng
 * @date: 2020/5/21 21:27
 */
public class ArrayUtil {
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                if (j != arr[i].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    public static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
                if (j != arr[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
