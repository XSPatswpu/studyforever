package com.cherry.coding.structure.list;

import com.cherry.coding.utils.ListNode;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/22 16:21
 */
public class Leetcode_206 {

    /**
     * pre cur 双指针
     *
     * 思路其实很自然：
     * 我要反转链表，就要让后节点的next 指向前节点，但是直接这样做就会有问题，链表就中断了，没办法继续下去了。
     * 所以在做这个操作之前，要想办法把 当前节点的 next 节点存下来。
     * 又由于我要让 currentNode.next = previous，而 previous 我如果不储存的话，我压根儿就找不到，所以又需要一个指针储存 previous
     *
     * 核心思路：
     * 1. 用两个指针完成交换，然后迭代向后移动
     * 2. 交换的时候，考虑先临时储存然后再交换
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

    /**
     * 同样是递归，但是这种方案仿佛能看到迭代的影子
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        return doReverse(null, head);
    }

    public ListNode doReverse(ListNode prev, ListNode current) {
        if (current == null) {
            return prev;
        }
        ListNode temp = current.next;
        current.next = prev;
        return doReverse(current, temp);
    }


}
