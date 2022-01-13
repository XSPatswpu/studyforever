package com.cherry.algorithm;

public class Leetcode_367 {

    public boolean isPerfectSquare(int num) {
        long left = 0;
        long right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            // 利用临时变量 cur 储存结果，比直接写 mid * mid，要节省内存
            long cur = mid * mid;
            if (cur > num) {
                right = mid - 1;
            } else if (cur < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
