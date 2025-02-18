package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TopologyKahnsAlgo {
    public static void main(String[] args) {
        TopologyKahnsAlgo obj = new TopologyKahnsAlgo();
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        int[] ans = obj.topoSort(V, adj);
        for (int node : ans) {
            System.out.print(node + " ");
        }
        System.out.println("");
    }

    private int[] topoSort(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] in_degree = new int[v];
        //fill in-degree array
        for(int i=0;i<v;i++){
            for(Integer it: adj.get(i)){
                in_degree[it]++;
            }
        }
        System.out.println(Arrays.toString(in_degree));
        Queue<Integer> que = new LinkedList<>();

        // initially: add all in-degree 0 to queue
        for(int i=0;i<v;i++){
            if(in_degree[i]==0)
                que.add(i);
        }
        //not do bfs and remove the node and reduce the in-degree
        int[] result = new int[v];
        int itr=0;
        while(!que.isEmpty()){
            int node = que.poll();
            result[itr++] = node;

            for(Integer nextNode: adj.get(node)){
                in_degree[nextNode]--;
                if(in_degree[nextNode]==0){
                    que.offer(nextNode);
                }
            }
        }
        //to find cycle check if the count of nodes in the result is equal to V
//        if(result.length == v)
//            return true;// no cycle
        return result;
    }
}
/*
Time complexity: O(V+E)
    𝑉 is the number of courses (vertices) and 𝐸 is the number of prerequisites (edges).
Space complexity: O(V+E)
    Space is used for the adjacency list and the in-degree array.

For finding cycle refer Course Scheduler-I in leetcode.

Approach
We use Topological Sorting via Kahn's Algorithm (DFS-based approach) to detect cycles in the directed graph:

-Graph Representation:
We represent the graph using an adjacency list. Each course points to a list of courses that depend on it.
-In-Degree Calculation:
We calculate the in-degree for each course. The in-degree represents the number of prerequisites for each course.
-Queue Initialization:
Initialize a queue and add all courses with zero in-degrees (i.e., courses with no prerequisites).
-BFS and Topological Sort:
Perform BFS. For each course processed, reduce the in-degree of its neighbors (dependent courses). If any neighbor's in-degree becomes zero, add it to the queue.
-Cycle Detection:
Maintain a count of processed courses. If this count equals the total number of courses, it means all courses can be completed (no cycle). Otherwise, a cycle exists.
* */