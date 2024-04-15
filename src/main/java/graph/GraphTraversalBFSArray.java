package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversalBFSArray {

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> bfs = new ArrayList<>();
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[V];
        // because nodes value is starting with 0, we can directly add 0 in the queue
        que.add(0);
        visited[0] = true;

        while(!que.isEmpty()){
            Integer temp = que.poll();
            bfs.add(temp);

            for(Integer it: adj.get(temp)){
                if(!visited[it]){
                    visited[it] = true;
                    que.add(it);
                }
            }
        }
        return bfs;
    }

    public static void main(String[] args) {
        int numberOfNodes = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<numberOfNodes; i++){
            adj.add(new ArrayList<>());
        }
        //nodes are starting with 0
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);

        GraphTraversalBFSArray obj = new GraphTraversalBFSArray();
        ArrayList<Integer> result = obj.bfsOfGraph(numberOfNodes, adj);

        System.out.println(result);


    }
}
