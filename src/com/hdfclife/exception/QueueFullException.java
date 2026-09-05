package com.hdfclife.exception;

public class QueueFullException extends RuntimeException {
    public QueueFullException(String message) {
        super(message);
    }
}
