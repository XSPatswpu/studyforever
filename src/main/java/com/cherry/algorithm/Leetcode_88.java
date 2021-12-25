package com.cherry.algorithm;

import java.util.Arrays;

public class Leetcode_88 {

    /**
     * 数组合并排序
     */
    class Solution1 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for (int i = m, j = 0; i < nums1.length; i++, j++) {
                nums1[i] = nums2[j];
            }

            Arrays.sort(nums1);

        }
    }

    /**
     * 正向双指针（需要额外空间）
     */
    class Solution2 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {

            int pointer1 = 0, pointer2 = 0;
            int[] result = new int[m + n];

            for (int i = 0; i < result.length ; i++) {
                if (pointer1 == m) {
                    result[i] = nums2[pointer2];
                    pointer2++;
                    continue;
                }

                if (pointer2 == n) {
                    result[i] = nums1[pointer1];
                    pointer1++;
                    continue;
                }

                if (nums1[pointer1] <= nums2[pointer2]) {
                    result[i] = nums1[pointer1];
                    pointer1++;
                    continue;
                }

                result[i] = nums2[pointer2];
                pointer2++;
            }

            for (int i = 0; i < nums1.length; i++) {
                nums1[i] = result[i];
            }

        }
    }

    /**
     * 逆向指针，不用维护额外空间
     */
    class Solution3 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {

            int pm = m - 1, pn = n - 1, i = m + n - 1;

            while (i > -1) {
                if (pm == -1) {
                    nums1[i] = nums2[pn];
                    pn--;
                } else if (pn == -1) {
                    return;
                } else if (nums1[pm] >= nums2[pn]) {
                    nums1[i] = nums1[pm];
                    pm--;
                } else {
                    nums1[i] = nums2[pn];
                    pn--;
                }
                i--;
            }
        }
    }


}
