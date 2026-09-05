package com.hdfclife;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hdfclife.exception.QueueEmptyException;
import com.hdfclife.exception.StackEmptyException;
import com.hdfclife.list.ClaimLinkedList;
import com.hdfclife.list.ClaimNode;
import com.hdfclife.list.CycleDetector;
import com.hdfclife.list.DigitListAdder;
import com.hdfclife.list.ListReverser;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Urgency;
import com.hdfclife.queue.BranchBfs;
import com.hdfclife.queue.CircularClaimQueue;
import com.hdfclife.queue.ClaimPriorityDesk;
import com.hdfclife.stack.ArrayClaimStack;
import com.hdfclife.stack.ParenthesesChecker;
import com.hdfclife.stack.PostfixEvaluator;
import com.hdfclife.thread.ClaimTotalCallable;
import com.hdfclife.thread.ProducerConsumer;
import com.hdfclife.thread.SeedRunnable;

public class Main {

    public static void main(String[] args) throws Exception {

        int[] amounts = {
                25000,
                18000,
                42000,
                15000,
                31000,
                9000
        };

        Claim[] claims = {

                new Claim(
                        "CLM-01",
                        25000,
                        "HDFC-LIFE-1001",
                        "Anita Sharma",
                        Urgency.HIGH
                ),

                new Claim(
                        "CLM-02",
                        18000,
                        "HDFC-LIFE-1002",
                        "Rahul Mehta",
                        Urgency.MEDIUM
                ),

                new Claim(
                        "CLM-03",
                        42000,
                        "HDFC-LIFE-1005",
                        "Sneha Patel",
                        Urgency.HIGH
                ),

                new Claim(
                        "CLM-04",
                        15000,
                        "HDFC-LIFE-1004",
                        "Vikram Singh",
                        Urgency.LOW
                ),

                new Claim(
                        "CLM-05",
                        31000,
                        "HDFC-LIFE-1001",
                        "Anita Sharma",
                        Urgency.MEDIUM
                ),

                new Claim(
                        "CLM-06",
                        9000,
                        "HDFC-LIFE-1003",
                        "Priya Nair",
                        Urgency.LOW
                )
        };


        // 1. Print seed linked list
        ClaimLinkedList list = createList(amounts);
        printArray(list.toArray());


        // 2. Insert 22000 at index 2
        list.insertAt(2, 22000);
        printArray(list.toArray());


        // 3. Delete element at index 2
        list.deleteAt(2);
        printArray(list.toArray());


        // 4. Reverse linked list iteratively
        ClaimLinkedList iterativeList = createList(amounts);
        ListReverser.reverseIterative(iterativeList);
        printArray(iterativeList.toArray());


        // 5. Reverse linked list recursively
        ClaimLinkedList recursiveList = createList(amounts);
        ListReverser.reverseRecursive(recursiveList);
        printArray(recursiveList.toArray());


        // 6. Find middle node using slow and fast pointers
        ClaimLinkedList middleList = createList(amounts);
        ClaimNode middle = CycleDetector.findMiddle(middleList);
        System.out.println(middle.amount);


        // 7. Check cycle in normal linked list
        ClaimLinkedList cycleList = createList(amounts);
        System.out.println(CycleDetector.hasCycle(cycleList));


        // 8. Create cycle and check hasCycle
        ClaimNode tail = cycleList.nodeAt(cycleList.size() - 1);
        ClaimNode cycleNode = cycleList.nodeAt(2);
        tail.next = cycleNode;

        System.out.println(CycleDetector.hasCycle(cycleList));


        // 9. Find starting node of the cycle
        ClaimNode cycleStart =
                CycleDetector.findCycleStart(cycleList);

        System.out.println(cycleStart.amount);

        // Break the cycle after the demonstration
        tail.next = null;


        // 10. Add two numbers using digit linked lists
        ClaimLinkedList number1 = createDigitList(25000);
        ClaimLinkedList number2 = createDigitList(18000);

        ClaimLinkedList sum =
                DigitListAdder.add(number1, number2);

        printArray(sum.toArray());


        // 11. Check balanced brackets - valid
        System.out.println(
                ParenthesesChecker.isBalanced(
                        "((TERM)(ULIP))"
                )
        );


        // 12. Check balanced brackets - missing bracket
        System.out.println(
                ParenthesesChecker.isBalanced(
                        "((TERM)(ULIP)"
                )
        );


        // 13. Check balanced brackets - wrong order
        System.out.println(
                ParenthesesChecker.isBalanced(
                        "([)]"
                )
        );


        // 14. Evaluate postfix expression
        System.out.println(
                PostfixEvaluator.evaluate(
                        "25000 18000 + 1000 -"
                )
        );


        // 15. Circular queue dequeue
        CircularClaimQueue queue =
                new CircularClaimQueue(4);

        queue.enqueue(25000);
        queue.enqueue(18000);
        queue.enqueue(42000);

        System.out.println(queue.dequeue());


        // 16. Circular queue after wrapping around
        queue.enqueue(15000);
        queue.enqueue(31000);

        printQueue(queue);


        // 17. Breadth-first search from MUMBAI
        BranchBfs.bfs("MUMBAI");


        // 18. Priority queue poll order
        ClaimPriorityDesk.process(claims);


        // 19. Thread state before start
        SeedRunnable runnable = new SeedRunnable();
        Thread thread = new Thread(runnable);

        System.out.println(thread.getState());


        // 20. Thread state after join
        thread.start();
        thread.join();

        System.out.println(thread.getState());


        // 21. Callable Future get total
        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        ClaimTotalCallable callable =
                new ClaimTotalCallable(amounts);

        Future<Integer> future =
                executor.submit(callable);

        System.out.println(future.get());


        // 22. Check Future isDone
        System.out.println(future.isDone());


        // 23. CompletableFuture asynchronous total
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> {

                    int total = 0;

                    for (int amount : amounts) {
                        total += amount;
                    }

                    return total;
                });

        System.out.println(completableFuture.get());


        // 24. Cancel a long-running Future
        Future<Integer> cancelFuture =
                executor.submit(() -> {

                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    return 0;
                });

        Thread.sleep(100);

        cancelFuture.cancel(true);

        System.out.println(cancelFuture.isCancelled());


        // 25. Check daemon thread flag
        Thread daemonThread = new Thread(() -> {
            // Daemon thread does not need to run
        });

        daemonThread.setDaemon(true);

        System.out.println(daemonThread.isDaemon());


        // 26. Producer-consumer using ArrayBlockingQueue
        List<Integer> consumed =
                ProducerConsumer.run();

        printIntegerList(consumed);


        // 27. Demonstrate invalid linked-list index exception
        try {

            ClaimLinkedList exceptionList =
                    createList(amounts);

            exceptionList.deleteAt(99);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }


        // 28. Demonstrate empty stack exception
        try {

            ArrayClaimStack stack =
                    new ArrayClaimStack(32);

            stack.pop();

        } catch (StackEmptyException e) {

            System.out.println(e.getMessage());
        }


        // 29. Demonstrate empty queue exception
        try {

            CircularClaimQueue emptyQueue =
                    new CircularClaimQueue(4);

            emptyQueue.dequeue();

        } catch (QueueEmptyException e) {

            System.out.println(e.getMessage());
        }


        // Shutdown executor so no worker threads remain
        executor.shutdown();
    }


    // Creates a linked list from the given amounts
    private static ClaimLinkedList createList(int[] amounts) {

        ClaimLinkedList list = new ClaimLinkedList();

        for (int amount : amounts) {
            list.addLast(amount);
        }

        return list;
    }


    // Creates least-significant-digit-first linked list
    private static ClaimLinkedList createDigitList(int number) {

        ClaimLinkedList list = new ClaimLinkedList();

        while (number > 0) {

            int digit = number % 10;

            list.addLast(digit);

            number = number / 10;
        }

        return list;
    }


    // Prints an integer array
    private static void printArray(int[] array) {

        for (int i = 0; i < array.length; i++) {

            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(array[i]);
        }

        System.out.println();
    }


    // Prints circular queue from front to back
    private static void printQueue(
            CircularClaimQueue queue) {

        for (int i = 0; i < queue.size(); i++) {

            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(queue.get(i));
        }

        System.out.println();
    }


    // Prints producer-consumer results
    private static void printIntegerList(
            List<Integer> list) {

        for (int i = 0; i < list.size(); i++) {

            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(list.get(i));
        }

        System.out.println();
    }
}
