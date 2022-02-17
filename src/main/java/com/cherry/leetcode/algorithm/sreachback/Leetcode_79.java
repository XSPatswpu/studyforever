package com.cherry.leetcode.algorithm.sreachback;

public class Leetcode_79 {
    public static void main(String[] args) {
        Leetcode_79 leet = new Leetcode_79();
        char[][] arr = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCB";

        boolean exist = leet.exist(arr, word);
        System.out.println(exist);
    }

    /**
     * [["a","a","a","a"],["a","a","a","a"],["a","a","a","a"]]
     * "aaaaaaaaaaaaa"
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {

        boolean[][] path = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    path[i][j] = true;
                    dfs(board, word, 1, i, j, path);
                    if (flag) {
                        return true;
                    }
                    path[i][j] = false;
                }
            }
        }
        return flag;
    }

    public boolean flag = false;

    public void dfs(char[][] board, String word, int index, int y, int x, boolean[][] path) {
        if (index == word.length()) {
            flag = true;
            return;
        }

        // 东 1
        if (!flag && (x + 1) < board[y].length && !path[y][x + 1] && board[y][x + 1] == word.charAt(index)) {
            path[y][x + 1] = true;
            dfs(board, word, index + 1, y, x + 1, path);
            path[y][x + 1] = false;
        }

        // 南 2
        if (!flag && (y + 1) < board.length && !path[y + 1][x] && board[y + 1][x] == word.charAt(index)) {
            path[y + 1][x] = true;
            dfs(board, word, index + 1, y + 1, x, path);
            path[y + 1][x] = false;
        }

        // 西 3
        if (!flag && (x - 1) >= 0 && !path[y][x - 1] && board[y][x - 1] == word.charAt(index)) {
            path[y][x - 1] = true;
            dfs(board, word, index + 1, y, x - 1, path);
            path[y][x - 1] = false;
        }

        // 北 4
        if (!flag && (y - 1) >= 0 && !path[y - 1][x] && board[y - 1][x] == word.charAt(index)) {
            path[y - 1][x] = true;
            dfs(board, word, index + 1, y - 1, x, path);
            path[y - 1][x] = false;
        }
    }
}
