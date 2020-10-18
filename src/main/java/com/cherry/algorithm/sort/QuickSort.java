package com.cherry.algorithm.sort;

import com.cherry.algorithm.utils.ArrayUtil;

/**
 * @author: xiangshaopeng
 * @date: 2020/2/19 13:18
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {-1,2,3,1,9,6};
        QuickSort qs = new QuickSort();
        qs.quickSort2(array, 0, array.length - 1);

        ArrayUtil.print(array);

    }

    /**
     * 快排思路1
     * 采用左右指针数字互换的方式
     * 基准数默认取index = 0
     *
     * @param array
     * @return
     */
    public int[] quickSort0(int[] array, int start, int end) {
        if (array == null || array.length == 0) {
            return null;
        }

        int index = doQuickSort0(array, start, end);
        if (start < index) {
            quickSort0(array, start, index - 1);
        }
        if (end > index) {
            quickSort0(array, index + 1, end);
        }
        return array;
    }

    /**
     * 方式一
     * 选取第一个数字为基准数，采用左右指针互换的方式
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    private int doQuickSort0(int[] array, int start, int end) {
        int index = start;
        int temp = array[index];
        int i = start, j = end;
        while (i < j) {
            // 忽略array[j] = temp的情况
            // 下面while条件不加判断i和j很可能会交叉，忽略 j == i的情况
            while (array[j] >= temp && j > i) {
                j--;
            }
            while (array[i] <= temp && i < j) {
                i++;
            }

            if (i < j) {
                int current = array[i];
                array[i] = array[j];
                array[j] = current;
            }
        }
        // 两个指针偏移量相等，最后交换基准元素
        array[index] = array[i];
        array[i] = temp;
        return i;
    }

    public int[] quickSort1(int[] array, int start, int end) {
        if (array == null || array.length == 0) {
            return null;
        }

        int index = doQuickSort1(array, start, end);
        if (start < index) {
            quickSort1(array, start, index - 1);
        }
        if (end > index) {
            quickSort1(array, index + 1, end);
        }
        return array;
    }

    /**
     * 快排方式二：采用左右指针与基准数交换的方式
     * 注意i、j指针移动的先后顺序
     * @param array
     * @param start
     * @param end
     * @return
     */
    private int doQuickSort1(int[] array, int start, int end) {
        int index = start;
        int i = start, j = end;
        while (i < j) {
            while (array[j] >= array[index] && i < j) {
                j--;
            }
            if (i < j) {
                swapInArray(array, j, index);
                index = j;
            }
            while (array[i] <= array[index] && i < j) {
                i++;
            }
            if (i < j) {
                swapInArray(array, i, index);
                index = i;
            }
        }
        return index;
    }

    private void swapInArray(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 快排实现方式三
     * @param array
     * @param start
     * @param end
     */
    public void quickSort2(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int index = doQuickSort2(array, start, end);
        quickSort2(array, start, index - 1);
        quickSort2(array, index + 1, end);
    }

    private int doQuickSort2(int[] array, int start, int end) {
        // 取最后元素为基准点
        int index = array[end];

        int i = start;

        for (int j = start; j < end; j++) {
            if (array[j] < index) {
                swapInArray(array, i, j);
                i++;
            }
        }

        swapInArray(array, end, i);
        return i;
    }

}
