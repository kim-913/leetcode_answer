/*
 * You are given n tasks labeled from 0 to n - 1, and a list of prerequisite
 * pairs.
 * Each pair [a, b] means you must finish task b before you can do task a.
 * 
 * Return the maximum number of tasks that can be completed.
 * If there are circular dependencies (a cycle), the tasks in the cycle and any
 * tasks depending on them cannot be completed.
 * 
 * Input:
n = 5
prerequisites = [[1, 0], [2, 1], [3, 2], [1, 3]]
Output:
1
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskCompletion {

    public static int maxTasks(int n, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int completed = 0;

        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0)
                queue.offer(i);

        while (!queue.isEmpty()) {
            int task = queue.poll();
            completed++;

            for (int neighbor : graph.get(task)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        return completed;
    }

    public static void main(String[] args) {
        int[][] prereqs1 = { { 1, 0 }, { 2, 1 }, { 3, 2 } };
        System.out.println(maxTasks(4, prereqs1)); // Output: 4

        int[][] prereqs2 = { { 1, 0 }, { 0, 1 } };
        System.out.println(maxTasks(4, prereqs2)); // Output: 2

        int[][] prereqs3 = { { 1, 0 }, { 2, 1 }, { 3, 2 }, { 1, 3 } };
        System.out.println(maxTasks(5, prereqs3)); // Output: 1
    }
}
