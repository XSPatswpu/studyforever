package com.cherry.algorithm.sreachback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/3 14:13
 */
public class Leetcode_51 {
    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * @param args
     */
    public static void main(String[] args) {
        Leetcode_51 lc = new Leetcode_51();
        List<List<String>> lists = lc.solveNQueens(8);
        System.out.println(lists);
        lc.print(lists);

    }


    public List<List<String>> solveNQueens(int n) {

        boolean[][] num = new boolean[n][n];
        List<List<String>> list = new ArrayList<>();
        doSolveNQueens(0, n, num, list);
        return list;
    }

    public void doSolveNQueens(int row, int n, boolean[][] num, List<List<String>> list) {
        if (row == n) {
            // add
            add2List(num, list);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isOk(row, i, num)) {
                num[row][i] = true;
                doSolveNQueens(row + 1, n, num, list);
                // 子问题执行完成，状态复原
                num[row][i] = false;
            }

        }

    }

    private boolean isOk(int row, int column, boolean[][] num) {
        int leftColumn = column, rightColumn = column;

        for (int j = row - 1; j > -1 ; j--) {
            leftColumn--;
            rightColumn++;
            // 判断左上角
            if (leftColumn > -1) {
                if (num[j][leftColumn]) {
                    return false;
                }
            }

            // 右上角
            if (rightColumn < num.length) {
                if (num[j][rightColumn]) {
                    return false;
                }
            }

            // 正上方
            if (num[j][column]) {
                return false;
            }
        }
        return true;
    }

    private void add2List(boolean[][] num, List<List<String>> list) {
        List<String> inner = new ArrayList<>();
        list.add(inner);
        for (boolean[] booleans : num) {
            StringBuilder s = new StringBuilder();
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    s.append("Q");
                } else {
                    s.append(".");
                }
            }
            inner.add(s.toString());
        }

    }

    private void print(List<List<String>> list) {
        for (List<String> strings : list) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println();
        }
    }



}
