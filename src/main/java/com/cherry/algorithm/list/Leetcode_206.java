package com.cherry.algorithm.list;

import com.cherry.algorithm.utils.ListNode;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/22 16:21
 */
public class Leetcode_206 {
    /**
     * pre cur 双指针
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 递归实现
     * @param head
     * @return
     */
    public ListNode reverseList02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = reverseList02(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }


}
