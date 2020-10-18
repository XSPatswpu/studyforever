package com.cherry.algorithm.sreachback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/10 17:07
 */
public class Leetcode_60 {

    public static void main(String[] args) {
        Leetcode_60 leet = new Leetcode_60();

        String permutation = leet.getPermutation(3, 5);

        System.out.println(permutation);


    }

    public String getPermutation(int n, int k) {
        List<Integer> curArrange = new ArrayList<>();
        int[][] numStatus = new int[n][2];

        if (n == 1) {
            return "1";
        }

        for (int i = 0; i < n; i++) {
            numStatus[i][0] = i + 1;
            numStatus[i][1] = 1;
        }

        int calNum = calNum(n - 1);

        doPermute(numStatus, calNum, numStatus.length, 0, curArrange, k);

        List<Integer> numK = result;
        StringBuilder sb = new StringBuilder();
        for (Integer integer : numK) {
            sb.append(integer);
        }

        return sb.toString();
    }

    public int index;
    public List<Integer> result;


    public void doPermute(int[][] numStatus, int calNum,  int len, int count, List<Integer> current, int k) {
        if (index == k) {
            return;
        }

        if (count == len) {
            index++;
            if (index == k) {
                result = new ArrayList<>(current);
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (numStatus[i][1] == 1) {

                if (count == 0) {
                    if (k > (i + 1) * calNum) {
                        index = (i + 1) * calNum;
                        continue;
                    }
                }

                current.add(numStatus[i][0]);
                numStatus[i][1] = 0;
                doPermute(numStatus, calNum,  len, count + 1, current, k);
                current.remove(current.size() - 1);
                numStatus[i][1] = 1;
            }
        }
    }
    public int calNum(int n) {
        if (n == 1) {
            return 1;
        }

        return n * calNum(n - 1);
    }
}
