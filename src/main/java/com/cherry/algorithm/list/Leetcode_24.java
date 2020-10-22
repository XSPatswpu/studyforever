package com.cherry.algorithm.list;

import com.cherry.algorithm.utils.ListNode;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/22 17:11
 */
public class Leetcode_24 {
    public ListNode swapPairs(ListNode head) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode cur = preHead;
        while (cur.next != null && cur.next.next != null) {
            reverse(cur, cur.next, cur.next.next);
            cur = cur.next.next;
        }
        return preHead.next;
    }

    public void reverse(ListNode cur, ListNode left, ListNode right) {
        ListNode temp = right.next;
        right.next = left;
        left.next = temp;
        cur.next = right;
    }
}
