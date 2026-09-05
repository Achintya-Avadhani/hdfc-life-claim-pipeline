package com.hdfclife.list;

public class DigitListAdder {

    public static ClaimLinkedList add(ClaimLinkedList list1, ClaimLinkedList list2) {

        ClaimLinkedList result = new ClaimLinkedList();

        ClaimNode p = list1.head;
        ClaimNode q = list2.head;

        int carry = 0;

        while (p != null || q != null || carry != 0) {

            int value1 = 0;
            int value2 = 0;

            if (p != null) {
                value1 = p.amount;
            }

            if (q != null) {
                value2 = q.amount;
            }

            int sum = value1 + value2 + carry;

            int digit = sum % 10;
            carry = sum / 10;

            result.addLast(digit);

            if (p != null) {
                p = p.next;
            }

            if (q != null) {
                q = q.next;
            }
        }

        return result;
    }
}