package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//find the shortest path from the source to all other nodes in this graph
//all edges are 1 unit weight given will be an adj lst of graph
// SOLVED USING BFS
public class ShortestPathUndirected {
    public static void main(String[] args) {
        // n = number of nodes(0-8)
        // m = number of edges
        int n=9, m=10;
        int[][] edge = {{0,1},{0,3},{3,4},{4,5},{5,6},{1,2},{2,6},{6,7},{7,8},{6,8}};

        ShortestPathUndirected obj = new ShortestPathUndirected();
        int[] res = obj.shortestPath(edge,n,m,0);
        for(int i=0;i<n;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();
    }

    private int[] shortestPath(int[][] edge, int v, int e, int src) {
        //create adj list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<e;i++){
            adj.get(edge[i][0]).add(edge[i][1]);
            adj.get(edge[i][1]).add(edge[i][0]);
        }

        //create a dist array and fill to all value
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src]=0;

        //Implement bfs
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        while(!que.isEmpty()){
            int node = que.poll();
            for(int nextNode: adj.get(node)){
                if(dist[node]+1<dist[nextNode]){
                    dist[nextNode] = dist[node]+1;
                    que.add(nextNode);
                }
            }
        }

        //for all unreachable nodes make the dist from Integer.MAX to -1
        for(int i=0;i<v;i++){
            if(dist[i]==Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        return dist;
    }
}


//TIME COMPLEXITY: (v+2e) for bfs, e for creating adj list, v for dist list update , total=(v+e)
// SPACE: v->resultant array dist,  v+2e for adj list, v ->queue space = (v+e)