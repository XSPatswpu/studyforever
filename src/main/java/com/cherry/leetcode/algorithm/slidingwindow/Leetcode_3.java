package com.cherry.leetcode.algorithm.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }


    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int start = 0;
        int end = 0;
        int subLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.add(s.charAt(i))) {
                end++;
                subLength = Math.max(subLength, end - start);
            } else {
                if (end - start > 0) {
                    set.remove(s.charAt(start));
                }
                start++;
                i--;
            }
        }
        return subLength;
    }
}
