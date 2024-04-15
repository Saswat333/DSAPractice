package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

//Given a DAG, find the shortest path from the source to all other nodes in this DAG.
// In this problem statement, we have assumed the source vertex to be ‘0’. You will be given the weighted edges of the graph.
//Finding the shortest path to a vertex is easy if you already know the shortest paths to all the vertices that can precede it.
//Steps: perform topo sort and store the result in a stack
public class ShortedPathDAG {
    public static void main(String[] args) {
        int n = 6, m = 7;
        int[][] edge = {{0,1,2},{0,4,1},{4,5,4},{4,2,2},{1,2,3},{2,3,6},{5,3,1}};
        ShortedPathDAG obj = new ShortedPathDAG();
        int res[] = obj.shortestPath(n, m, edge);
        for (int i = 0; i < n; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }

    private void topoSortDFS(int node, boolean[] vis, ArrayList<ArrayList<Pair1>> adj, Stack<Integer> topoSortResult) {
        vis[node] = true;

        for(Pair1 it : adj.get(node)){
            int v = it.first;
            if(!vis[v]){
                topoSortDFS(v, vis, adj, topoSortResult);
            }
        }
        topoSortResult.add(node);
    }

    private int[] shortestPath(int v, int e, int[][] edge) {
        //create adj list
        ArrayList<ArrayList<Pair1>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<Pair1>());
        }
        for(int i=0;i<e;i++){
            int src = edge[i][0];
            int dest = edge[i][1];
            int weight = edge[i][2];
            adj.get(src).add(new Pair1(dest, weight));
        }

        // do topo sort and store in stack
        boolean[] vis = new boolean[v];
        Stack<Integer> topoSortResult = new Stack<>();
        for(int i=0;i<v;i++){
            if(!vis[i])
                topoSortDFS(i, vis, adj, topoSortResult);
        }

        //create distance array, and assign max then perform traversal and keep checking for shortest dist using bfs
        int[] dist = new int[v];
        for(int i=0;i<v;i++)
            Arrays.fill(dist, Integer.MAX_VALUE);
        //mark source as 0, 0th node is considered as src
        dist[0] = 0;

        // pop each stk element and traverse
        while(!topoSortResult.isEmpty()){
            int node = topoSortResult.pop();
            for(Pair1 it: adj.get(node)){
                int nextNode = it.first;
                int nextNodeWeight = it.second;

                if(dist[node]+nextNodeWeight < dist[nextNode])
                    dist[nextNode] = dist[node]+nextNodeWeight;
            }
        }

        //replace max with -1(unreachable nodes)
        for(int i=0;i<v;i++){
            if(dist[i]==Integer.MAX_VALUE)
                dist[i] = -1;
        }

        return dist;
    }
}


//Time: topo:(v+2e)+traverese each node for distance cal(v+2e) = O(v+e)
//Space: v(stack) + v(dist arr) +  v(disited arr) + (v+2e) adj list