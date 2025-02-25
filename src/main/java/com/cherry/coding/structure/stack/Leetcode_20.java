package com.cherry.coding.structure.stack;

import java.util.Stack;

public class Leetcode_20 {
    class Solution {
        public boolean isValid(String s) {

            int n = s.length();
            if (n % 2 == 1) {
                return false;
            }

            Stack<Character> leftStack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (isLeft(s.charAt(i))) {
                    leftStack.push(s.charAt(i));
                    continue;
                }

                if (leftStack.isEmpty()) {
                    return false;
                }

                Character ch = leftStack.pop();
                if (!isEquals(ch, s.charAt(i))) {
                    return false;
                }
            }

            if (!leftStack.isEmpty()) {
                return false;
            }
            return true;

        }

        public boolean isLeft(Character ch) {
            return ch == '(' || ch == '{' || ch == '[';
        }

        public boolean isEquals(char left, char right) {
            if (left == '(' && right == ')') {
                return true;
            }

            if (left == '{' && right == '}') {
                return true;
            }

            if (left == '[' && right == ']') {
                return true;
            }

            return false;
        }
    }
}
