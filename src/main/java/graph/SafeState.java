package graph;//A directed graph of V vertices and E edges is given in the form of an adjacency list adj.
// Each node of the graph is labeled with a distinct integer in the range 0 to V – 1.
// A node is a terminal node if there are no outgoing edges.
// A node is a safe node if every possible path starting from that node leads to a terminal node.
// You have to return an array containing all the safe nodes of the graph.
// The answer should be sorted in ascending order.

import java.util.ArrayList;
import java.util.List;

// Observation1: a cycle means it will never be an ans(terminal node) as each node in cycle will have one outdegree
//obs2: any path that leads to cycle will never end up in terminal node
// If any dfs call gets completed then its a success path
// if the dfs finds cycle then end the dfs call
// IF using DFS when returning back we check if any other node of the current_path has another node to check
// unless we check all tha path from that node we can't say it as safe, if that node doesnt have any alt path then we
// can add that node as safe
public class SafeState{
    public static void main(String[] args) {
        int V = 12;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(2).add(4);
        adj.get(3).add(4);
        adj.get(3).add(5);
        adj.get(4).add(6);
        adj.get(5).add(6);
        adj.get(6).add(7);
        adj.get(8).add(1);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);
        adj.get(11).add(9);

        SafeState obj = new SafeState();
        List<Integer> safeNodes = obj.eventualSafeNodes(V, adj);

        int[][] graph = new int[][]{{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> safeNodes1 = obj.eventualSafeNodes2D(graph);

        for (int node : safeNodes1) {
            System.out.print(node + " ");
        }
        System.out.println("");
    }

    private List<Integer> eventualSafeNodes(int v, List<List<Integer>> adj) {
        int[] vis = new int[v];
        int[] pathVis = new int[v];
        int[] safe = new int[v];

        //do dfs on each component
        for(int i=0;i<v;i++){
            if(vis[i]==0){
                dfsCheck(i, adj, vis, pathVis, safe);
            }
        }
        // get the safe nodes from the adj list by checking if its marked safe
        List<Integer> safeNode = new ArrayList<>();
        for(int i=0;i<v;i++){
            if(safe[i]==1)
                safeNode.add(i);
        }
        return safeNode;
    }

    private List<Integer> eventualSafeNodes2D(int[][] graph) {
        int v = graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<v;i++){
            for(int it: graph[i]){
                adj.get(i).add(it);
            }
        }

        int[] vis = new int[v];
        int[] pathVis = new int[v];
        int[] safe = new int[v];

        //do dfs on each component
        for(int i=0;i<v;i++){
            if(vis[i]==0){
                dfsCheck(i, adj, vis, pathVis, safe);
            }
        }
        // get the safe nodes from the adj list by checking if its marked safe
        List<Integer> safeNode = new ArrayList<>();
        for(int i=0;i<v;i++){
            if(safe[i]==1)
                safeNode.add(i);
        }
        return safeNode;
    }

    private boolean dfsCheck(int node, List<List<Integer>> adj, int[] vis, int[] pathVis, int[] safe) {
        vis[node] = 1;
        pathVis[node] = 1;
        safe[node]=0;

        //traverse for neighbour nodes
        for(int nextNode:adj.get(node)){
            if(vis[nextNode]==0){
                if(dfsCheck(nextNode, adj, vis, pathVis, safe))
                    return true;
            }
            //if the node is previously visited
            //but it has to be visited in the current path
            else if(pathVis[nextNode]==1){
                // path has already been visited vis[node] =true and
                // path is also visited in current traversing path then its cycle,return true
                return true;
            }
        }
        //if traverse completed for the node then there is a path that reaches the terminal node
        //unvisited the current traversed path
        safe[node] = 1;
        pathVis[node] =0;

        // no cycle
        return false;
    }
}
