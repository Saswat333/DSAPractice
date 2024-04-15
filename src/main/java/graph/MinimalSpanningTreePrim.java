package graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

// We have N nodes and N-1 edges(all nodes reachable)
// a graph can have more than one spanning tree
// Minimum spanning tree: Is the one with minimum weight
//Question: Given a weighted, undirected, and connected graph of V vertices and E edges. The task is to find the sum of weights
// of the edges of the Minimum Spanning Tree.
//V = 5, edges = { {0, 1, 2}, {0, 3, 6}, {1, 2, 3}, {1, 3, 8}, {1, 4, 5}, {4, 2, 7}}
//Result = 16
//Prim's Algo: we need priority queue to store edge information
//visited array, priority queue, and sum_variable = sum of the edges in mst
//we can also save a mst array if asked to return all the edges
public class MinimalSpanningTreePrim {
    public static void main(String[] args) {
        int v = 5;
        int[][] edges =  {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        //create adj list
        ArrayList<ArrayList<Pair1>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            adj.get(edges[i][0]).add(new Pair1(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair1(edges[i][0], edges[i][2]));
        }
//        System.out.println(adj.size());
//        for(int i=0;i<v;i++){
//            for(graph.Pair1 it: adj.get(i)){
//                System.out.print(i+", ");
//                System.out.print(it.first +", ");
//                System.out.println(it.second);
//            }
//        }
        MinimalSpanningTreePrim obj = new MinimalSpanningTreePrim();
        int sum = obj.spanningTree(v, adj);
        System.out.println("The sum of all the edge weights: " + sum);
    }

    private int spanningTree(int v, ArrayList<ArrayList<Pair1>> roads) {
        PriorityQueue<Pair1> pq = new PriorityQueue<>((x,y)->x.second - y.second);
        int[] vis = new int[v];
        pq.add(new Pair1(0,0));
        int sum=0;
        while(!pq.isEmpty()){
            Pair1 cur = pq.poll();
            int wt = cur.second;
            int node = cur.first;

            if(vis[node] == 1) continue;

            vis[node] =1;
            sum+=wt;

            for(Pair1 next: roads.get(node)){
                int nextWt = next.second;
                int nextNode = next.first;
                if(vis[nextNode] == 0){
                    pq.add(new Pair1(nextNode, nextWt));
                }
            }
        }
        return sum;
    }
}
