package com.cherry.leetcode.structure.array;

public class Leetcode_59 {

    public int[][] generateMatrix(int n) {
        int num = 1;
        int target = n * n;
        int up = 0;
        int down = n - 1;
        int right = n - 1;
        int left = 0;
        int[][] matrix = new int[n][n];
        while (num <= target) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                matrix[up][i] = num++;
            }
            up++;
            // 从上到下
            for (int i = up; i <= down; i++) {
                matrix[i][right] = num++;
            }
            right--;
            // 从右到左
            for (int i = right; i >= left; i--) {
                matrix[down][i] = num++;
            }
            down--;
            // 从下到上
            for (int i = down; i >= up; i--) {
                matrix[i][left] = num++;
            }
            left++;
        }
        return matrix;
    }
}
