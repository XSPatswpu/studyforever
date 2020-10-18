package com.cherry.algorithm.list;

import com.cherry.algorithm.utils.ListNode;

public class Leetcode_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode quick = head;
        int count = 0;
        while (count < n) {
            quick = quick.next;
            count++;
        }

        if (quick == null) {
            return slow.next;
        }

        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    /**
     * 哨兵 + 快慢指针
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode sentry = new ListNode(-1);
        sentry.next = head;
        ListNode quick = sentry;
        ListNode slow = sentry;

        int count = 0;
        while (count < n) {
            quick = quick.next;
            count++;
        }
        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next;
        }

        slow.next = slow.next.next;
        return sentry.next;
    }
}
