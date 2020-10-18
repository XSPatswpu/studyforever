package com.cherry.algorithm;

/**
 * @author: xiangshaopeng
 * @date: 2020/9/25 11:06
 */
public class Leetcode_69 {

    /**
     * 注意下java基本数字类型转换语法：
     * 当操作数中所有的数字类型都比int底，会自动转换成int 再进行运算
     * 当操作中包含long类型当数字时，全部自动转换为long 再进行运算
     * 当操作数中只有int，不会进行类型转换
     * 类型转换和赋值语句无关！
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int start = 0;
        int end = x;
        int mid = start + (end - start) / 2;
        while(start <= end) {
            long cur = (long)mid * mid;
            if (cur == x) {
                return mid;
            } else if (cur < x){
                if ((long)(mid + 1) * (mid + 1) > x) {
                    return mid;
                }
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = start + (end - start) / 2;
        }
        return mid;
    }
}
