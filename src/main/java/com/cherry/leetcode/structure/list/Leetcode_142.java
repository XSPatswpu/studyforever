package com.cherry.leetcode.structure.list;

import com.cherry.leetcode.utils.ListNode;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/6 21:59
 */
public class Leetcode_142 {
    public ListNode detectCycle(ListNode head) {
        ListNode quick = head;
        ListNode slow = head;
        int quickCount = 0;
        int slowCount = 0;
        while(true) {
            if (quick != null && quick.next != null && quick.next.next != null) {
                quick = quick.next.next;
                quickCount += 2;
            }else {
                return null;
            }
            if (slow != null && slow.next != null) {
                slow  = slow.next;
                slowCount++;
            } else {
                return null;
            }
            if (quick == slow) {
                break;
            }
        }
        int roll = quickCount - slowCount;
        slow = quick = head;
        while (roll > 0) {
            quick = quick.next;
            roll--;
        }
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;
        }
        return quick;
    }
}
