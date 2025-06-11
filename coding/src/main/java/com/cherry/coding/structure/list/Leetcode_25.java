package com.cherry.coding.structure.list;

import com.cherry.coding.utils.ListNode;
import com.cherry.coding.utils.PrintUtil;

public class Leetcode_25 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
//        ListNode node5 = new ListNode(5);
//        node4.next = node5;

        Leetcode_25 leetcode_25 = new Leetcode_25();
        PrintUtil.printList(node1);
        ListNode listNode = leetcode_25.reverseKGroup04(node1, 3);
        PrintUtil.printList(listNode);

    }


    /**
     * 1. 写链表反转
     * 2. 弄清楚有多少个指针
     * 3. 局部的链表反转完之后，要和外面的链表连起来
     * 4. 再循环中处理好之后，记得指针复位
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode tail = head;
        ListNode pre = hair;

        while (head != null) {

            for (int i = 0; i < k - 1; i++) {
                tail = tail.next;
                if (tail == null) {
                    // 结束
                    return hair.next;
                }

            }
            ListNode nex = tail.next;

            // 反转操作
            ListNode[] reverse = reverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            // 把反转后的链表，与外面的链表节点连起来
            pre.next = head;
            tail.next = nex;

            // 指针复位
            pre = tail;
            head = tail.next;
            tail = head;
        }

        return hair.next;

    }

    public ListNode[] reverse(ListNode head, ListNode tail) {
        if (head == tail) {
            return new ListNode[]{head, tail};
        }

        ListNode prev = null;
        ListNode cur = head;

        while (prev != tail) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return new ListNode[]{prev, cur};

    }

    public ListNode reverseKGroup02(ListNode head, int k) {
        if (k < 2) {
            return head;
        }

        ListNode tempHead = head;
        int length = 0;
        while (tempHead != null) {
            length++;
            tempHead = tempHead.next;
        }
        if (k > length) {
            return head;
        }
        int i = 0;
        ListNode reHead = null;
        ListNode[] prevArr = {null, null, head};
        do {
            if (i + k > length) {
                prevArr[1].next = prevArr[2];
                break;
            }
            ListNode[] arr = doReverse02(prevArr[2], k);
            // 拼接
            if (prevArr[0] != null) {
                prevArr[1].next = arr[0];
            } else {
                reHead = arr[0];
            }
            i += k;
            prevArr = arr;
        } while (prevArr[2] != null);
        return reHead;
    }

    public ListNode[] doReverse02(ListNode head, int k) {
        ListNode prev = null, cur = head;
        int count = 0;
        while (count < k) {
            ListNode tempNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tempNode;
            count++;
        }
        // {头，尾，下一个}
        return new ListNode[]{prev, head, cur};
    }

    public ListNode reverseKGroup03(ListNode head, int k) {
        if (k < 2 || head == null) {
            return head;
        }
        ListNode hair = new ListNode(-1);
        ListNode prevTail = hair;
        ListNode[] prevArr = {hair, head};
        while (prevArr[1] != null) {
            // 判断是否需要反转
            ListNode tail = prevArr[1];
            for (int i = 0; i < k - 1 && tail != null; i++) {
                tail = tail.next;
            }
            // 不反转
            if (tail == null) {
                break;
            }
            // 反转
            ListNode[] arr = doReverse(prevArr[1], k);

            // 拼接
            // prev tail -> cur head
            prevTail.next = arr[0];
            // cur tail -> next head
            prevArr[1].next = arr[1];

            prevTail = prevArr[1];
            prevArr = arr;
        }
        return hair.next;
    }

    public ListNode[] doReverse(ListNode head, int k) {
        ListNode prev = null, cur = head;
        int count = 0;
        while (count < k) {
            ListNode tempNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tempNode;
            count++;
        }
        // {当前头节点，下一个节点}
        return new ListNode[]{prev, cur};
    }

    public ListNode reverseKGroup04(ListNode head, int k) {
        if (k < 2 || head == null) {
            return head;
        }
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode prev = hair;
        while (head != null) {
            // get tail
            ListNode tail = prev;
            for (int i = 0; i < k && tail != null; i++) {
                tail = tail.next;
            }
            if (tail == null) {
                break;
            }
            ListNode[] arr = doReverse04(head, tail);
            prev.next = arr[0];
            arr[1].next = arr[2];

            prev = arr[1];
            head = prev.next;
        }
        return hair.next;
    }

    private ListNode[] doReverse04(ListNode head, ListNode tail) {
        ListNode prev = null, cur = head;
        while (prev != tail) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        // {head, tail, next}
        return new ListNode[]{prev, head, cur};
    }

}
