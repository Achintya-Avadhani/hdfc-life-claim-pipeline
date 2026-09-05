package com.hdfclife.thread;

import java.util.concurrent.Callable;

public class ClaimTotalCallable implements Callable<Integer> {

    private int[] amounts;

    public ClaimTotalCallable(int[] amounts) {
        this.amounts = amounts;
    }
    @Override
    public Integer call() {

        int total = 0;

        for (int amount : amounts) {
            total += amount;
        }

        return total;
    }
}