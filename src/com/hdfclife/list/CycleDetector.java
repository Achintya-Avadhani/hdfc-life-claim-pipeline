package com.hdfclife.list;

public class CycleDetector {

    public static ClaimNode findMiddle(ClaimLinkedList list) {

        ClaimNode slow = list.head;
        ClaimNode fast = list.head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static boolean hasCycle(ClaimLinkedList list) {

        ClaimNode slow = list.head;
        ClaimNode fast = list.head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static ClaimNode findCycleStart(ClaimLinkedList list) {

        ClaimNode slow = list.head;
        ClaimNode fast = list.head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = list.head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}