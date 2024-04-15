package graph;

import java.util.ArrayList;
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

        Queue<Integer> que = new LinkedList<>();

        // initially: add all in-degree 0 to queue
        for(int i=0;i<v;i++){
            if(in_degree[i]==0)
                que.add(i);
        }
        //not do bfs and remove the node and reduce the indegree
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

        return result;
    }
}
