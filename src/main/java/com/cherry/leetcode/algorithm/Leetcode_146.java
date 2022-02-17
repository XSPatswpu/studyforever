package com.cherry.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/8 12:31
 */
public class Leetcode_146 {

    public static void main(String[] args) {

        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(1);// 返回 1
        System.out.println(i);
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int i1 = lRUCache.get(2);// 返回 -1 (未找到)
        System.out.println(i1);
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int i2 = lRUCache.get(1);// 返回 -1 (未找到)
        System.out.println(i2);
        int i3 = lRUCache.get(3);// 返回 3
        System.out.println(i3);
        int i4 = lRUCache.get(4);// 返回 4
        System.out.println(i4);

    }


    /*
    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
    */

//    class LRUCache{
//
//        class DoubleList {
//            public DoubleList () {
//
//            }
//            public DoubleList (Integer key, Integer value) {
//                this.key = key;
//                this.value = value;
//            }
//            DoubleList prev;
//            DoubleList next;
//            Integer key;
//            Integer value;
//        }
//
//        private Map<Integer, DoubleList> cache;
//
//        private DoubleList head;
//        private DoubleList tail;
//        private int size;
//        private int capacity;
//
//        public LRUCache(int capacity) {
//            cache = new HashMap<>(capacity);
//            this.capacity = capacity;
//            this.size = 0;
//
//            // 虚拟头节点、尾节点
//            head = new DoubleList();
//            tail = new DoubleList();
//            head.next = tail;
//            tail.prev = head;
//        }
//
//        public int get(int key) {
//            DoubleList node = cache.get(key);
//            if (node == null) {
//                return -1;
//            }
//            // 移动到 head
//            moveToHead(node);
//            return node.value;
//        }
//
//        public void put(int key, int value) {
//            DoubleList node = cache.get(key);
//            DoubleList newNode = new DoubleList(key, value);
//            if (node == null) {
//                // 插入到 head
//                addToHead(newNode);
//                // 插入到 cache
//                cache.put(key, newNode);
//                size++;
//                // 插入完成，判断 size > capacity, 移除链表最后一个元素
//                if (isNeedMove()) {
//                    DoubleList tail = removeTail();
//                    cache.remove(tail.key);
//                    size--;
//                }
//            } else {
//                node.value = value;
//                // 移动节点到头部
//                moveToHead(node);
//            }
//        }
//
//        public void moveToHead(DoubleList node) {
//            removeNode(node);
//            addToHead(node);
//        }
//
//        /**
//         * 移动节点至头部
//         *
//         * 注意: 头节点是虚拟节点
//         * @param node
//         */
//        public void addToHead(DoubleList node) {
//            DoubleList temp = head.next;
//            head.next = node;
//            temp.prev = node;
//            node.prev = head;
//            node.next = temp;
//        }
//
//        public boolean isNeedMove() {
//            return size > capacity;
//        }
//
//        /**
//         * 移除尾节点
//         *
//         * 注意：尾节点是虚拟节点
//         */
//        public DoubleList removeTail() {
//            DoubleList prev = tail.prev;
//            removeNode(prev);
//            return prev;
//        }
//
//        /**
//         * 移除中间节点
//         *
//         * @param node
//         */
//        public void removeNode(DoubleList node) {
//            node.prev.next = node.next;
//            node.next.prev = node.prev;
//        }
//    }

    static class LRUCache {

        public class DListNode {
            private int key;
            private int value;

            private DListNode pre;
            private DListNode next;

            public DListNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, DListNode> map = new HashMap<>();

        private int capacity;

        private int size;

        private DListNode head;
        private DListNode tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new DListNode(-1, -1);
            tail = new DListNode(-1, -1);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            DListNode dListNode = map.get(key);
            if (dListNode == null) {
                return -1;
            }
            moveToHead(dListNode);
            return dListNode.value;

        }

        public void put(int key, int value) {
            DListNode dListNode = map.get(key);
            if (dListNode == null) {

                DListNode newNode = new DListNode(key, value);
                map.put(key, newNode);

                addToHead(newNode);
                size++;

                if (size > capacity) {
                    DListNode removeTail = removeTail();
                    map.remove(removeTail.key);
                    size--;
                }

            } else {
                dListNode.value = value;
                moveToHead(dListNode);
            }

        }

        private void moveToHead(DListNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DListNode removeTail() {
            DListNode pre = tail.pre;
            removeNode(tail.pre);
            return pre;
        }

        private void removeNode(DListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void addToHead(DListNode node) {
            node.pre = head;
            node.next = head.next;
            head.next = node;
            node.next.pre = node;
        }


    }


}
