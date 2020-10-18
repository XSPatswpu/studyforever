package com.cherry.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/3 23:28
 */
public class Leetcode_1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            Integer otherIndex = map.get(other);
            if (otherIndex != null && otherIndex != i) {
                return new int[]{i, otherIndex};
            }
        }
        return null;
    }
}
