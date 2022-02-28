package com.cherry.leetcode.structure.list;

import com.cherry.leetcode.utils.ListNode;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/22 17:11
 */
public class Leetcode_24 {
    /**
     * 递归
     * @param head
     * @return
     */
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

    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        // 虚拟节点的作用：把头节点的问题变得和其他节点一样
        ListNode first = new ListNode(-1);
        first.next = head;
        ListNode hair = first;
        while (hair != null && hair.next != null && hair.next.next != null) {
            ListNode prev = hair.next;
            ListNode cur = hair.next.next;
            ListNode temp = cur.next;
            cur.next = prev;
            prev.next = temp;
            hair.next = cur;
            // hair 指针归位
            hair = prev;
        }
        return first.next;
    }
}
