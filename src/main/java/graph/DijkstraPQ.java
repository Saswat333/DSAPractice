package graph;// Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj where adj[i] is a
// list of lists containing two integers where the first integer of each list j denotes there is an edge between i and j,
// second integers corresponds to the weight of that edge.
// You are given the source vertex S and You have to Find the shortest distance of all the vertex from the source vertex S.
//Note:Only positive weight graph

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraPQ {
    public static void main(String[] args) {
        int  v=3, e=3, src=2;
        int[][] graph = new int[][]{{0,1,1},{0,2,6},{1,2,3},{1,0,1},{2,1,3},{2,0,6}};
        //create adj
        ArrayList<ArrayList<Pair1>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<graph.length;i++){
            int u = graph[i][0];
            int d = graph[i][1];
            int w = graph[i][2];

            adj.get(u).add(new Pair1(d,w));

        }
        //adj list print
//        for(int i=0;i<adj.size();i++){
//            for(graph.Pair1 it: adj.get(i))
//                System.out.println("Node:"+i+"->"+it.first+" , "+it.second);
//            System.out.println();
//        }
        DijkstraPQ obj = new DijkstraPQ();
        int[] res= obj.dijkstra(v,adj,src);

        for(int i=0;i<v;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();

    }

    private int[] dijkstra(int v, ArrayList<ArrayList<Pair1>> adj, int src) {
        // graph.Pair.first = destination_node, graph.Pair.second = weight
        PriorityQueue<Pair1> pq = new PriorityQueue<>((x,y)->x.second-y.second);
        //create dist and fill with integer.max
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src]=0;
        pq.add(new Pair1(src, 0));

        //traverse by poping out from priority queue
        while(!pq.isEmpty()){
            Pair1 node = pq.poll();
            int curDest = node.first;
            int curWeight = node.second;
            System.out.println("dest:"+curDest+" weight:"+curWeight);
            for(Pair1 it: adj.get(curDest)){
                int nextNode = it.first;
                int nextWeight = it.second;

                if(curWeight+nextWeight<dist[nextNode]){
                    dist[nextNode] = curWeight+nextWeight;
                    pq.add(new Pair1(nextNode,dist[nextNode]));
                }
            }
        }
        return dist;
    }
}

//time: for every edge we have to check in priority queue for min dist, everytime we pop heapify costs logn = E.LOG(V)
//space: priority que(E) and dist(V) = v+e