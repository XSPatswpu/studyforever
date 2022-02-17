package com.cherry.algorithm;

import java.util.Stack;

public class Leetcode_844 {
    /**
     * 采用栈
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> s1 = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '#') {
                if (!s1.empty()) {
                    s1.pop();
                }
                continue;
            }
            s1.push(aChar);
        }

        Stack<Character> t1 = new Stack<>();
        char[] chars1 = t.toCharArray();
        for (char aChar : chars1) {
            if (aChar == '#') {
                if (!t1.empty()) {
                    t1.pop();
                }
                continue;
            }
            t1.push(aChar);
        }

        if (s1.size() != t1.size()) {
            return false;
        }

        while (!s1.empty() && !t1.empty()) {
            Character charS = s1.pop();
            Character charT = t1.pop();
            if (charS != charT) {
                return false;
            }
        }
        return true;

    }

    /**
     * 双指针
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare1(String s, String t) {
        int skipS = 0, skipT = 0;
        int indexS = s.length() - 1;
        int indexT = t.length() - 1;

        while (indexS >= 0 || indexT >= 0) {
            while (indexS >= 0) {
                if (s.charAt(indexS) == '#') {
                    skipS++;
                } else if (skipS > 0) {
                    skipS--;
                } else {
                    // 取到有效字符
                    break;
                }
                indexS--;
            }

            while (indexT >= 0) {
                // skip = 0 && index > 0
                // skip > 0
                // #
                if (t.charAt(indexT) == '#') {
                    skipT++;
                } else if (skipT > 0) {
                    skipT--;
                } else {
                    // 取到有效字符
                    break;
                }
                indexT--;
            }

            if (indexS >= 0 && indexT >= 0) {
                if (s.charAt(indexS) != t.charAt(indexT)) {
                    return false;
                }
            } else if (indexS >= 0 || indexT >= 0) {
                // 长度不一致
                return false;
            }

            indexS--;
            indexT--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "bxj##tw";
        String t = "bxj###tw";

        Leetcode_844 lc844 = new Leetcode_844();
        boolean b = lc844.backspaceCompare1(s, t);
        System.out.println(b);

    }
}
