package com.cherry.coding.algorithm.sort;

import com.cherry.coding.utils.PrintUtil;

/**
 * @author: xiangshaopeng
 * @date: 2020-01-19 16:59
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {2,18,6,1,33,23};
        BubbleSort bs = new BubbleSort();
        PrintUtil.print(bs.bubbleSort(array));
    }

    public int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    private void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
