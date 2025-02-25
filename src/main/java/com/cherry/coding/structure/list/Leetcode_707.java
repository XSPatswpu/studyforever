package com.cherry.coding.structure.list;

import com.cherry.coding.utils.ListNode;

public class Leetcode_707 {

    /**
     * 采用单链表的实现方式
     */
    class MyLinkedList {

        private int size;

        private ListNode hair;

        public MyLinkedList() {
            hair = new ListNode(-1);
            size = 0;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }

            ListNode cur = hair;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {

            if (index > size) {
                return;
            }

            if (index <= 0) {
                ListNode insertNode = new ListNode(val);
                size++;
                insertNode.next = hair.next;
                hair.next = insertNode;
                return;
            }

            ListNode prev = hair;
            ListNode cur = hair.next;
            for (int i = 1; i <= index; i++) {
                prev = cur;
                cur = cur.next;
            }

            ListNode insertNode = new ListNode(val);
            size++;
            insertNode.next = cur;
            prev.next = insertNode;
            return;
        }

        public void deleteAtIndex(int index) {
            if (size == 0 || index < 0 || index >= size) {
                return;
            }

            ListNode prev = hair;
            for (int i = 1; i <= index; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
            size--;
        }
    }
}
