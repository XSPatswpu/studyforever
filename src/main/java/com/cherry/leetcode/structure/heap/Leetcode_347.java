package com.cherry.leetcode.structure.heap;

import com.cherry.leetcode.utils.PrintUtil;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_347 {

    public static void main(String[] args) {
        int[] arr = {1, 2};
        int k = 2;
        Leetcode_347 s = new Leetcode_347();
        PrintUtil.print(s.topKFrequent(arr, k));

    }


    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], count + 1);
            }
        }

        // 建堆 小顶堆
        Heap heap = new Heap(k);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            checkIfPut(heap, map, entry.getKey(), entry.getValue());
        }

        return topK(heap, k, map);

    }

    public int[] topK(Heap heap, int k, HashMap<Integer, Integer> map) {
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = heap.pop(map);
        }
        return arr;
    }



    public void checkIfPut(Heap heap, HashMap<Integer, Integer> map, int num, int count) {

        if (heap.count < heap.size) {
            // 插入
            heap.insert(num, map);
            return;
        }

        Integer currentLowCount = map.get(heap.getTop());

        if (count > currentLowCount) {
            // 替换堆顶，并堆化
            heap.swapTop(num, map);
        }

    }

    class Heap {
        public Heap(int capacity) {
            heapArray = new int[capacity + 1];
            size = capacity;
        }

        public int[] heapArray;

        // 当前元素个数
        public int count;

        // 最大个数
        public int size;

        public int get(int index) {
            return heapArray[index];
        }

        public int getTop() {
            return heapArray[1];
        }

        public void insert(int num, HashMap<Integer, Integer> map) {
            if (count >= size) {
                return;
            }

            count++;
            heapArray[count] = num;
            int i = count;
            while (i / 2 > 0 && map.get(heapArray[i / 2]) > map.get(heapArray[i]) ) {
                swap(heapArray, i, i / 2);
                i = i / 2;
            }

        }

        public void swapTop(int num, HashMap<Integer, Integer> map) {
            heapArray[1] = num;

            up2Bottom(1, map);
        }

        public int pop(HashMap<Integer, Integer> map) {
            int top = heapArray[1];
            heapArray[1] = heapArray[count];
            count--;
            up2Bottom(1, map);
            return top;
        }


        public void up2Bottom(int i, HashMap<Integer, Integer> map) {
            int minIndex = i;
            while (true) {

                if (i * 2 <= count && map.get(heapArray[i * 2]) < map.get(heapArray[i])) {
                    minIndex = i * 2;
                }

                if (i * 2 + 1 <= count && map.get(heapArray[i * 2 + 1]) < map.get(heapArray[minIndex])) {
                    minIndex = i * 2 + 1;
                }

                swap(heapArray, i, minIndex);

                if (minIndex == i) {
                    break;
                }
                i = minIndex;
            }
        }

        public void swap(int[] array, int a, int b) {
            int temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }


    }


}