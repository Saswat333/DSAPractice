package rest_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerticalPaths {
    public static int countVerticalPaths(int[] cost, int edge_nodes, int[] edge_from, int[] edge_to, int k) {
        // Create an adjacency list to represent the tree
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= edge_nodes; i++) {
            tree.add(new ArrayList<>());
        }

        // Build the adjacency list for the tree
        for (int i = 0; i < edge_from.length; i++) {
            tree.get(edge_from[i]).add(edge_to[i]);
            tree.get(edge_to[i]).add(edge_from[i]);
        }

        // Visited array to prevent re-visiting nodes
        boolean[] visited = new boolean[edge_nodes + 1];

        // Result variable to store the number of valid paths
        int[] result = new int[1];  // Use an array to modify result inside DFS

        // Start DFS from the root node (assume root is 1)
        dfs(1, -1, k, cost, tree, visited, result);

        return result[0];
    }

    // DFS helper function
    private static void dfs(int node, int parent, int k, int[] cost, List<List<Integer>> tree, boolean[] visited, int[] result) {
        visited[node] = true;
        // Store the current path costs starting from this node
        List<Long> pathCosts = new ArrayList<>();
        pathCosts.add(0L);  // A base path cost of zero (starting point)

        // Explore all neighbors
        for (int neighbor : tree.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, node, k, cost, tree, visited, result);
            }
        }

        // After DFS, calculate the cost of vertical paths for each node
        // Move upwards from current node towards the root
        long currentCost = 0;
        int currentNode = node;

        while (currentNode != -1) {
            currentCost += cost[currentNode - 1];
            if (currentCost % k == 0) {
                result[0]++;
            }
            // Move upwards by going to the parent node in the tree structure
            currentNode = parent;
        }
    }
    // Example usage
    public static void main(String[] args) {
        int[] cost = {1,2,2,1,2};  // Node costs
        int edge_nodes = 5;
        int[] edge_from = {5,2,2,1,2};  // Edges from node
        int[] edge_to = {4,3,1,4,5};    // Edges to node
        int k = 2;

        System.out.println("Number of vertical paths with cost divisible by " + k + " : " +
                countVerticalPaths(cost, edge_nodes, edge_from, edge_to, k));
    }
}
