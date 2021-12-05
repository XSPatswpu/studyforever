package com.cherry.algorithm.list;

import com.cherry.algorithm.utils.ListNode;

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
        ListNode listNode = leetcode_25.reverseKGroup(node1, 2);
        System.out.println(listNode.val);
    }


//    class Solution {
//        public ListNode reverseKGroup(ListNode head, int k) {
//            ListNode hair = new ListNode(0);
//            hair.next = head;
//            ListNode pre = hair;
//
//            while (head != null) {
//                ListNode tail = pre;
//                // 查看剩余部分长度是否大于等于 k
//                for (int i = 0; i < k; ++i) {
//                    tail = tail.next;
//                    if (tail == null) {
//                        return hair.next;
//                    }
//                }
//                ListNode nex = tail.next;
//                ListNode[] reverse = myReverse(head, tail);
//                head = reverse[0];
//                tail = reverse[1];
//                // 把子链表重新接回原链表
//                pre.next = head;
//                tail.next = nex;
//                pre = tail;
//                head = tail.next;
//            }
//
//            return hair.next;
//        }
//
//        public ListNode[] myReverse(ListNode head, ListNode tail) {
//            ListNode prev = tail.next;
//            ListNode p = head;
//            while (prev != tail) {
//                ListNode nex = p.next;
//                p.next = prev;
//                prev = p;
//                p = nex;
//            }
//            return new ListNode[]{tail, head};
//        }
//    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode tail = head;
        ListNode pre = hair;

        while (head != null) {

            for (int i = 0; i < k - 1; i++) {
                tail = tail.next;
                if (tail.next == null) {
                    // 结束
                    return hair.next;
                }

            }
            ListNode nex = tail.next;
            ListNode[] reverse = reverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            pre.next = head;
            tail.next = nex;
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
