package com.hdfclife.stack;

import com.hdfclife.exception.StackEmptyException;
import com.hdfclife.list.ClaimNode;

public class LinkedClaimStack implements ClaimStack {

    private ClaimNode top;

    @Override
    public void push(int value) {

        ClaimNode newNode = new ClaimNode(value);

        newNode.next = top;
        top = newNode;
    }

    @Override
    public int pop() {

        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }

        int value = top.amount;
        top = top.next;

        return value;
    }

    @Override
    public int peek() {

        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }

        return top.amount;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}