package com.cherry.algorithm.list;

import com.cherry.algorithm.utils.ListNode;

public class Leetcode_2 {
    /**
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @param args
     */
    public static void main(String[] args) {
        ListNode l11 = new ListNode(5);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
//        l11.next = l12;
//        l12.next = l13;

        ListNode l21 = new ListNode(5);
//        ListNode l22 = new ListNode(6);
//        ListNode l23 = new ListNode(4);
//        l21.next = l22;
//        l22.next = l23;

        Leetcode_2 leetcode = new Leetcode_2();
        ListNode listNode = leetcode.addTwoNumbers(l11, l21);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode head = null;
//        ListNode node = head;
//
//        int high = 0;
//        while (true) {
//            int sum = 0;
//            if (l1 == null && l2 == null) {
//                if (high == 0) {
//                    break;
//                }
//            }
//            if ((l1 == null || l2 == null) && high == 0) {
//                if (head == null) {
//                    if (l1 == null) {
//                        head = l2;
//                    } else {
//                        head = l1;
//                    }
//                } else {
//                    if (l1 == null) {
//                        node.next = l2;
//                    } else {
//                        node.next = l1;
//                    }
//                }
//                break;
//            }
//            if (l1 != null) {
//                sum += l1.val;
//            }
//            if (l2 != null) {
//                sum += l2.val;
//            }
//            sum += high;
//            if (sum > 9) {
//                sum = sum - 10;
//                high = 1;
//            } else {
//                high = 0;
//            }
//            if (head == null) {
//                head = node = new ListNode(sum);
//            } else {
//                ListNode temp = node;
//                node = new ListNode(sum);
//                temp.next = node;
//            }
//            if (l1 != null) {
//                l1 = l1.next;
//            }
//            if (l2 != null) {
//                l2 = l2.next;
//            }
//        }
//
//        return head;
//    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode first = null;
        ListNode current = null;
        int last = 0;
        while (l1 != null || l2 != null) {

            int n1 = (l1 == null ? 0 : l1.val);
            int n2 = (l2 == null ? 0 : l2.val);
            int sum = n1 + n2 + last;

            if (current == null) {
                first = new ListNode(sum % 10);
                current = first;
            } else {
                current.next = new ListNode(sum % 10);
                current = current.next;
            }
            last = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (last == 1) {
            current.next = new ListNode(1);
        }

        return first;
    }
}
