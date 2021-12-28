package com.cherry.algorithm.string;

class Solution {
    public String addStrings(String num1, String num2) {

        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;

        int temp = 0;
        StringBuilder number = new StringBuilder();
        while (n1 >= 0 || n2 >= 0) {
            int value1 = n1 < 0 ? 0 : getNum(num1.charAt(n1));
            int value2 = n2 < 0 ? 0 : getNum(num2.charAt(n2));
            int sum = value1 + value2 + temp;
            temp = sum / 10;
            number.append(sum % 10);
            n1--;
            n2--;
        }

        if (temp > 0) {
            number.append(1);
        }

        return number.reverse().toString();
    }

    public int getNum(char ch) {
        return ch - '0';
    }
}
