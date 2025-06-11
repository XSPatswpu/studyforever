package com.cherry.coding.structure.list;

import com.cherry.coding.utils.ListNode;

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
        ListNode hair = new ListNode(-1);
        hair.next = head;
        return doSwapPairs(hair, head);
    }

    public ListNode doSwapPairs(ListNode prev, ListNode cur) {
        if (cur == null || cur.next == null) {
            return cur;
        }

        // 处理前两个结点
        ListNode temp = cur.next.next;
        cur.next.next = cur;
        prev.next = cur.next;

        // 处理后面结点
        cur.next = doSwapPairs(cur, temp);
        return prev.next;
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
