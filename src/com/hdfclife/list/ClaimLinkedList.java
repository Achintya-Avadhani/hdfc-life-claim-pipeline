package com.hdfclife.list;

import com.hdfclife.exception.EmptyListException;
import com.hdfclife.exception.InvalidIndexException;

public class ClaimLinkedList {

    ClaimNode head = null;

    public void addLast(int amount) {

        ClaimNode newNode = new ClaimNode(amount);

        if (head == null) {
            head = newNode;
            return;
        }

        ClaimNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    public void addFirst(int amount) {

        ClaimNode newNode = new ClaimNode(amount);

        newNode.next = head;
        head = newNode;
    }

    public void insertAt(int index, int amount) {

        if (index < 0) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        if (index == 0) {
            addFirst(amount);
            return;
        }

        ClaimNode temp = head;
        int count = 0;

        while (count < index - 1 && temp != null) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        ClaimNode newNode = new ClaimNode(amount);

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void deleteAt(int index) {

        if (head == null) {
            throw new EmptyListException(
                    "The list is empty"
            );
        }

        if (index < 0) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        ClaimNode temp = head;
        int count = 0;

        while (count < index - 1 && temp != null) {
            temp = temp.next;
            count++;
        }

        if (temp == null || temp.next == null) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        temp.next = temp.next.next;
    }

    public ClaimNode nodeAt(int index) {

        if (head == null) {
            throw new EmptyListException(
                    "The list is empty"
            );
        }

        if (index < 0) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        ClaimNode temp = head;
        int count = 0;

        while (count < index && temp != null) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        return temp;
    }

    public int size() {

        ClaimNode temp = head;
        int size = 0;

        while (temp != null) {
            size++;
            temp = temp.next;
        }

        return size;
    }

    public int[] toArray() {

        int[] result = new int[size()];

        ClaimNode temp = head;
        int index = 0;

        while (temp != null) {
            result[index] = temp.amount;
            index++;
            temp = temp.next;
        }

        return result;
    }

    public void display() {

        ClaimNode temp = head;

        while (temp != null) {
            System.out.print(temp.amount);

            if (temp.next != null) {
                System.out.print(" -> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

}