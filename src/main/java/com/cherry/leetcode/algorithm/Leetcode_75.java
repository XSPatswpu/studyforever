package com.cherry.leetcode.algorithm;

import com.cherry.leetcode.utils.PrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/7 11:21
 */
public class Leetcode_75 {
    public static void main(String[] args) {
        int[] arr = {1,2,0};
        Leetcode_75 leet = new Leetcode_75();
        leet.sortColors(arr);
        PrintUtil.print(arr);
    }
    public void sortColors(int[] nums) {
        int front = 0;
        int back = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > back || front >= nums.length) {
                return;
            }
            if (nums[i] == 2) {
                while(back > 0 && nums[back] == 2) {
                    back--;
                }
                if (back < i) {
                    return;
                }
                swap(nums, i, back);
                if (nums[i] == 0) {
                    i--;
                }
                continue;
            }
            if (nums[i] == 0) {
                while (front < nums.length - 1 && nums[front] == 0) {
                    front++;
                }
                if (front < i) {
                    swap(nums, i, front);
                    if (nums[i] == 2) {
                        i--;
                    }
                }
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors_01(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], count + 1);
            }
        }

        int index4R = 0;
        if (map.get(0) != null) {
            index4R = map.get(0);
        }
        int index4W = index4R;
        if (map.get(1) != null) {
            index4W += map.get(1);
        }
        int index4B = index4W;
        if (map.get(2) != null) {
            index4B += map.get(2);
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < index4R) {
                nums[i] = 0;
            } else if (i < index4W) {
                nums[i] = 1;
            } else if (i < index4B) {
                nums[i] = 2;
            }
        }
    }
}
