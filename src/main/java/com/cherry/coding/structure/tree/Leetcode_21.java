package com.cherry.coding.structure.tree;

import com.cherry.coding.utils.ListNode;

public class Leetcode_21 {

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

            if (list1 == null) {
                return list2;
            }

            if (list2 == null) {
                return list1;
            }

            ListNode head;
            ListNode sub;
            if (list1.val < list2.val) {
                head = list1;
                sub = list2;
            } else {
                head = list2;
                sub = list1;
            }
            ListNode subHead = mergeTwoLists(head.next, sub);
            head.next = subHead;
            return head;

        }
    }
}
