package com.cherry.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/8 12:31
 */
public class Leetcode_146 {

    public static void main(String[] args) {


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

    class LRUCache{

        class DoubleList {
            public DoubleList () {

            }
            public DoubleList (Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
            DoubleList prev;
            DoubleList next;
            Integer key;
            Integer value;
        }

        private Map<Integer, DoubleList> cache;

        private DoubleList head;
        private DoubleList tail;
        private int size;
        private int capacity;

        public LRUCache(int capacity) {
            cache = new HashMap<>(capacity);
            this.capacity = capacity;
            this.size = 0;

            // 虚拟头节点、尾节点
            head = new DoubleList();
            tail = new DoubleList();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DoubleList node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 移动到 head
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DoubleList node = cache.get(key);
            DoubleList newNode = new DoubleList(key, value);
            if (node == null) {
                // 插入到 head
                addToHead(newNode);
                // 插入到 cache
                cache.put(key, newNode);
                size++;
                // 插入完成，判断 size > capacity, 移除链表最后一个元素
                if (isNeedMove()) {
                    DoubleList tail = removeTail();
                    cache.remove(tail.key);
                    size--;
                }
            } else {
                node.value = value;
                // 移动节点到头部
                moveToHead(node);
            }
        }

        public void moveToHead(DoubleList node) {
            removeNode(node);
            addToHead(node);
        }

        /**
         * 移动节点至头部
         *
         * 注意: 头节点是虚拟节点
         * @param node
         */
        public void addToHead(DoubleList node) {
            DoubleList temp = head.next;
            head.next = node;
            temp.prev = node;
            node.prev = head;
            node.next = temp;
        }

        public boolean isNeedMove() {
            return size > capacity;
        }

        /**
         * 移除尾节点
         *
         * 注意：尾节点是虚拟节点
         */
        public DoubleList removeTail() {
            DoubleList prev = tail.prev;
            removeNode(prev);
            return prev;
        }

        /**
         * 移除中间节点
         *
         * @param node
         */
        public void removeNode(DoubleList node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }


}
