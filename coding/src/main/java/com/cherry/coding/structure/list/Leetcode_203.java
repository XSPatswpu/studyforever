package com.cherry.coding.structure.list;

import com.cherry.coding.utils.ListNode;
import com.cherry.coding.utils.PrintUtil;

public class Leetcode_203 {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(7);
        node1.next = node2;
        ListNode node3 = new ListNode(7);
        node2.next = node3;
        ListNode node4 = new ListNode(7);
        node3.next = node4;
        PrintUtil.printList(node1);

        Leetcode_203 lc203 = new Leetcode_203();
        ListNode ListNodelistNode = lc203.removeElements(node1, 7);
        PrintUtil.printList(ListNodelistNode);

    }

    public ListNode removeElements(ListNode head, int val) {

        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode pre = hair;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return hair.next;
    }
}
