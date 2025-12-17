package com.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CountNumbersBefore {

    public static int countNumbersBefore(String[] operations, int target) {
        Queue<Integer> queue = new LinkedList<>();

        Arrays.stream(operations).forEach(op -> {
            if (op.startsWith("ENQUEUE")) {
                queue.offer(Integer.parseInt(op.split(" ")[1])); //op.substring(8)
            } else if (op.equals("DEQUEUE")) {
                queue.poll();
            }
        });

        int count = 0;
        for (int num : queue) {
            if (num == target) break;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        String[] operations = {
                "ENQUEUE 19",
                "ENQUEUE 20",
                "DEQUEUE",
                "ENQUEUE 21",
                "ENQUEUE 22"
        };

        int target = 22;

        System.out.println(countNumbersBefore(operations, target));
    }
}
