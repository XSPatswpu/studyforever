package com.cherry.leetcode.structure.list;

import com.cherry.leetcode.utils.ListNode;

public class Leetcode_25 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;

        Leetcode_25 leetcode_25 = new Leetcode_25();
//        ListNode listNode = leetcode_25.reverseKGroup(node1, 2);
//        System.out.println(listNode.val);
    }


    /**
     * 1. 写链表反转
     * 2. 弄清楚有多少个指针
     * 3. 局部的链表反转完之后，要和外面的链表连起来
     * 4. 再循环中处理好之后，记得指针复位
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode hair = new ListNode(-1);
            hair.next = head;
            ListNode tail = head;
            ListNode pre = hair;

            while (head != null) {

                for (int i = 0; i < k - 1; i++) {
                    tail = tail.next;
                    if (tail == null) {
                        // 结束
                        return hair.next;
                    }

                }
                ListNode nex = tail.next;

                // 反转操作
                ListNode[] reverse = reverse(head, tail);
                head = reverse[0];
                tail = reverse[1];

                // 把反转后的链表，与外面的链表节点连起来
                pre.next = head;
                tail.next = nex;

                // 指针复位
                pre = tail;
                head = tail.next;
                tail = head;
            }

            return hair.next;

        }

        public ListNode[] reverse(ListNode head, ListNode tail) {
            if (head == tail) {
                return new ListNode[]{head, tail};
            }

            ListNode prev = null;
            ListNode cur = head;

            while (prev != tail) {
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }
            return new ListNode[]{prev, cur};

        }

    }


}
