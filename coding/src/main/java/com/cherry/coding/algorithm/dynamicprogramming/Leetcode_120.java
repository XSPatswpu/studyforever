package com.cherry.coding.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_120 {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        int i = s.minimumTotal(list);
        System.out.println(i);


    }


    public static class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {

            // status
            int[][] status = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];

            // init 0
            status[0][0] = triangle.get(0).get(0);

            for (int i = 1; i < triangle.size(); i++) {
               for (int j = 0; j < i + 1; j++) {
                   if (j == 0) {
                       status[i][j] = status[i - 1][0] + triangle.get(i).get(j);
                   } else if (j == i) {
                       status[i][j] = status[i - 1][i - 1] + triangle.get(i).get(j);
                   } else {
                       status[i][j] = Math.min(status[i - 1][j - 1], status[i - 1][j]) + triangle.get(i).get(j);
                   }
               }
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < status[triangle.size() - 1].length; i++) {
                min = Math.min(min, status[triangle.size() - 1][i]);
            }
            return min;
        }
    }
}
