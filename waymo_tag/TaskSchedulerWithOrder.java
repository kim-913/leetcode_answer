import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskSchedulerWithOrder {

    public static List<Integer> taskOrder(int numTasks, int[][] prerequisites, int[] durations, int deadline) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numTasks];

        for (int i = 0; i < numTasks; i++)
            graph.add(new ArrayList<>());

        // Build the graph
        for (int[] pre : prerequisites) {
            int before = pre[1];
            int after = pre[0];
            graph.get(before).add(after);
            indegree[after]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] earliestFinish = new int[numTasks];

        // start tasks without prerequisites
        for (int i = 0; i < numTasks; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                earliestFinish[i] = durations[i];
            }
        }

        int completedTasks = 0;
        List<Integer> executionOrder = new ArrayList<>();

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            executionOrder.add(curr); // Record task execution order
            completedTasks++;

            for (int neighbor : graph.get(curr)) {
                earliestFinish[neighbor] = Math.max(
                        earliestFinish[neighbor],
                        earliestFinish[curr] + durations[neighbor]);

                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Cycle check
        if (completedTasks != numTasks) {
            System.out.println("Cycle detected, cannot finish tasks.");
            return new ArrayList<>();
        }

        int maxFinishTime = Arrays.stream(earliestFinish).max().orElse(0);
        if (maxFinishTime > deadline) {
            System.out.println("Tasks exceed the given deadline.");
            return new ArrayList<>();
        }

        // Return the successful execution order
        return executionOrder;
    }

    public static void main(String[] args) {
        int numTasks = 4;
        int[][] prerequisites = { { 1, 0 }, { 2, 1 }, { 3, 2 } };
        int[] durations = { 2, 2, 3, 1 };
        int deadline = 8;

        List<Integer> order = taskOrder(numTasks, prerequisites, durations, deadline);

        if (!order.isEmpty()) {
            System.out.println("Tasks can be executed in the following order:");
            for (int task : order) {
                System.out.print("Task " + task + " → ");
            }
            System.out.println("Done");
        }
    }
}
