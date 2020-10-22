package com.cherry.algorithm.list;

import com.cherry.algorithm.utils.ListNode;

public class Leetcode_19 {

    /**
     * 哨兵 + 快慢指针
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentry = new ListNode(-1);
        sentry.next = head;
        ListNode slow = sentry;
        ListNode quick = sentry;
        while (n > 0) {
            quick = quick.next;
            n--;
        }
        while (quick.next != null) {
            quick = quick.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return sentry.next;
    }
}
