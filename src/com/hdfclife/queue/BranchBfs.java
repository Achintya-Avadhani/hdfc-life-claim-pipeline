package com.hdfclife.queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BranchBfs {

    public static void bfs(String start) {

        Map<String, String[]> graph = new HashMap<>();

        graph.put("MUMBAI", new String[]{"PUNE", "DELHI"});
        graph.put("PUNE", new String[]{"HYDERABAD"});
        graph.put("DELHI", new String[]{"KOLKATA"});
        graph.put("HYDERABAD", new String[]{"CHENNAI"});
        graph.put("KOLKATA", new String[]{});
        graph.put("CHENNAI", new String[]{});

        Queue<String> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();

        queue.add(start);
        visited.put(start, true);

        boolean first = true;

        while (!queue.isEmpty()) {

            String branch = queue.poll();

            if (!first) {
                System.out.print(", ");
            }

            System.out.print(branch);
            first = false;

            String[] neighbours = graph.get(branch);

            for (String neighbour : neighbours) {

                if (!visited.containsKey(neighbour)) {

                    visited.put(neighbour, true);
                    queue.add(neighbour);
                }
            }
        }

        System.out.println();
    }
}