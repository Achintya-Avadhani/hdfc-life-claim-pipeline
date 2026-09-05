package com.hdfclife.queue;

import java.util.PriorityQueue;

import com.hdfclife.model.Claim;
import com.hdfclife.model.Urgency;

public class ClaimPriorityDesk {

    public static void process(Claim[] claims) {

        PriorityQueue<Claim> queue = new PriorityQueue<>(
                (a, b) -> {

                    int urgencyCompare =
                            Integer.compare(
                                    getPriority(b.getUrgency()),
                                    getPriority(a.getUrgency())
                            );

                    if (urgencyCompare != 0) {
                        return urgencyCompare;
                    }

                    return Integer.compare(
                            b.getAmount(),
                            a.getAmount()
                    );
                }
        );

        for (Claim claim : claims) {
            queue.offer(claim);
        }

        boolean first = true;

        while (!queue.isEmpty()) {

            Claim claim = queue.poll();

            if (!first) {
                System.out.print(", ");
            }

            System.out.print(claim.getClaimId());

            first = false;
        }

        System.out.println();
    }

    private static int getPriority(Urgency urgency) {

        if (urgency == Urgency.HIGH) {
            return 3;
        }

        if (urgency == Urgency.MEDIUM) {
            return 2;
        }

        return 1;
    }
}
