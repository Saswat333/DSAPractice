package graph;//dijstra and bellman are both single source shortest path algo
//graph.BellmanFord can handle graphs with both positive and negative edge weights. It is available only for Directed graphs
// It can even detect negative cycles (cycles where the total weight is negative).
//Dijkstra is faster for using PQ,
//Given a weighted, directed and connected graph of V vertices and E edges, Find the shortest distance of all the vertices from the source vertex S.
//Algo:
// relax all the edges n-1 times (e*n-1 times the loop will run)

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
    public static void main(String[] args) {
        int v = 6;
        int s = 0;
        int[][] edges = {{3, 2, 6},{5, 3, 1},{0, 1, 5},{1, 5, -3},{1, 2, -2},{3, 4, -2},{2, 4, 3}};

        //create adj list
        ArrayList<ArrayList<Pair1>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            adj.get(edges[i][0]).add(new Pair1(edges[i][1], edges[i][2]));
        }

        System.out.println(adj.size());
        for(int i=0;i<v;i++){
            for(Pair1 it: adj.get(i)){
                System.out.print(i+", ");
                System.out.print(it.first +", ");
                System.out.println(it.second);
            }
        }

        BellmanFord obj = new BellmanFord();
        int[] dist = obj.bellman_ford(v, adj, s);
        for (int i = 0; i < v; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println("");
    }

    private int[] bellman_ford(int n, ArrayList<ArrayList<Pair1>> adj, int s) {
        int[] dist =new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        for(int i=0;i<n-1;i++){
            for(Pair1 it: adj.get(i)){
                int u = i;
                int v = it.first;
                int weight = it.second;

                if(dist[u] != Integer.MAX_VALUE && dist[u]+weight <dist[v]){
                    dist[v] = dist[u] +weight;
                }
            }
        }

        //Nth relaxation to check negative cycle
        for(int i=0;i<n-1;i++){
            for(Pair1 it: adj.get(i)){
                int u = i;
                int v = it.first;
                int weight = it.second;

                if(dist[u] != Integer.MAX_VALUE && dist[u] +weight <dist[v]){
                    int[] temp = new int[1];
                    temp[0] = -1;
                    return temp;
                }
            }
        }
        return new int[]{};
    }
}
// Time = 2 (V*E) -> for each edge we check every edge
//space: v (dist array)