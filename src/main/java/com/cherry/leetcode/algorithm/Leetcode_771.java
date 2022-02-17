package com.cherry.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/2 15:18
 */
public class Leetcode_771 {

    public int numJewelsInStones(String J, String S) {
        Map<Character, Integer> charMap = new HashMap<>();
        for(int i = 0; i < S.length(); i++) {
            Integer count = charMap.get(S.charAt(i));
            if (count == null) {
                charMap.put(S.charAt(i), 1);
            } else {
                charMap.put(S.charAt(i), count + 1);
            }
        }

        int result = 0;
        for (int i = 0; i < J.length(); i++) {
            Integer count = charMap.get(J.charAt(i));
            if (count != null) {
                result += count;
            }
        }
        return result;
    }
}
