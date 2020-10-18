package com.cherry.algorithm.string;

public class Leetcode_415 {
    public String addStrings(String num1, String num2) {
        int high = 0;
        int len1 = num1.length();
        int len2 = num2.length();
        StringBuilder result = new StringBuilder();
        for (int i = len1 - 1, j = len2 - 1; i > -1 || j > -1; i--,j--) {
            int value1 = 0;
            int value2 = 0;
            if (i > -1) {
                value1 = getInt(num1.charAt(i));
            }
            if (j > -1) {
                value2 = getInt(num2.charAt(j));
            }
            int currentValue = value1 + value2 + high;
            if (currentValue > 9) {
                high = 1;
                currentValue = currentValue - 10;
            } else {
                high = 0;
            }
            result.append(currentValue);
        }
        if (high == 1) {
            result.append(1);
        }
        return result.reverse().toString();
    }

    public int getInt(char c) {
        return c - '0';
    }
}
