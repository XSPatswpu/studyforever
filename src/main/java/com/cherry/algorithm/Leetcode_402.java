package com.cherry.algorithm;

import java.util.Deque;
import java.util.LinkedList;

public class Leetcode_402 {
    /**
     * 贪心 + 单调栈
     */
    class Solution {
        public String removeKdigits(String num, int k) {
            Deque<Character> deque = new LinkedList<>();
            for (int i = 0; i < num.length(); i++) {
                Character ch = num.charAt(i);
                while (k > 0 && !deque.isEmpty() && deque.peekLast() > ch) {
                    deque.pollLast();
                    k--;
                }
                deque.offerLast(num.charAt(i));
            }

            while (k > 0) {
                deque.pollLast();
                k--;
            }

            StringBuilder sb = new StringBuilder();
            boolean checkFlag = true;
            while (!deque.isEmpty()) {
                if (checkFlag && deque.peekFirst() == '0') {
                    deque.pollFirst();
                    continue;
                } else {
                    checkFlag = false;
                }
                sb.append(deque.pollFirst());
            }

            return sb.length() == 0 ? "0" : sb.toString();
        }
    }
}
