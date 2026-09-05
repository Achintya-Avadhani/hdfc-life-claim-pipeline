package com.hdfclife.queue;

import com.hdfclife.exception.QueueEmptyException;
import com.hdfclife.exception.QueueFullException;

public class CircularClaimQueue {

    private int[] queue;
    private int front;
    private int rear;
    private int size;

    public CircularClaimQueue(int capacity) {

        queue = new int[capacity];

        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(int value) {

        if (isFull()) {
            throw new QueueFullException("Queue is full");
        }

        queue[rear] = value;

        rear = (rear + 1) % queue.length;

        size++;
    }

    public int dequeue() {

        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty");
        }

        int value = queue[front];

        front = (front + 1) % queue.length;

        size--;

        return value;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return queue[(front + index) % queue.length];
    }
}
