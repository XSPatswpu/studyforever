package com.cherry.coding.algorithm.sreachback;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_77 {

    public static void main(String[] args) {
        Leetcode_77 test = new Leetcode_77();
        List<List<Integer>> combine = test.combine_01(4, 2);
        System.out.println(combine);
    }

    private List<List<Integer>> list = new ArrayList<>();

    /**
     * 考虑简化
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        doCombine(1, 0, n, k, list, currentList);
        return list;
    }


    public List<List<Integer>> combine_01(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        doCombine(1, 0, n, k, list, currentList);
        return list;
    }

    public void doCombine(int current, int count, int n, int k, List<List<Integer>> lists, List<Integer> currentList) {

        if (count == k) {
            copytoList(lists, currentList);
            return;
        }

        for (int j = current; j <= n; j++) {
            currentList.add(j);
            doCombine(j + 1, count + 1, n, k, lists, currentList);
            currentList.remove(count);
        }

    }

    private void copytoList(List<List<Integer>> lists, List<Integer> currentList) {
        List<Integer> list = new ArrayList<>(currentList);
        lists.add(list);
    }

}
