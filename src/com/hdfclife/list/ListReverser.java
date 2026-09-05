package com.hdfclife.list;

public class ListReverser {

    public static void reverseIterative(ClaimLinkedList list) {

        ClaimNode previous = null;
        ClaimNode current = list.head;

        while (current != null) {

            ClaimNode next = current.next;

            current.next = previous;

            previous = current;
            current = next;
        }

        list.head = previous;
    }

    public static void reverseRecursive(ClaimLinkedList list) {

        list.head = reverse(
                list.head, null
        );
    }

    private static ClaimNode reverse(ClaimNode current, ClaimNode previous) {
        if (current == null) {
            return previous;
        }

        ClaimNode next = current.next;

        current.next = previous;

        return reverse(next, current);
    }
}
