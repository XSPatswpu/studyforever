package com.cherry.coding.structure.list;

import com.cherry.coding.utils.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_141 {

    /**
     * set 去重
     */
    public class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> set = new HashSet<>();

            if (head == null) {
                return false;
            }

            while (head != null) {
                if (set.contains(head)) {
                    return true;
                }
                set.add(head);
                head = head.next;
            }

            return false;

        }
    }

    /**
     * 快慢指针
     */
    public class Solution1 {
        public boolean hasCycle(ListNode head) {

            if (head == null) {
                return false;
            }

            ListNode quick = head;
            ListNode slow = head;

            while (quick != null && quick.next != null && slow != null) {
                slow = slow.next;
                quick = quick.next.next;
                if (quick == slow) {
                    return true;
                }
            }

            return false;

        }
    }
}
