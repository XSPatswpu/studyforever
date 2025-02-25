package com.cherry.coding.structure.list;

import com.cherry.coding.utils.ListNode;

public class Leetcode_160 {

    /**
     * 双指针循环遍历，想办法让他们相等
     */
    public class Solution {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            ListNode listA = headA;
            ListNode listB = headB;

            while (listA != listB) {

                if (listA == null) {
                    listA = headB;
                } else {
                    listA = listA.next;
                }

                if (listB == null) {
                    listB = headA;
                } else {
                    listB = listB.next;
                }
            }
            return listA;

        }
    }
}
