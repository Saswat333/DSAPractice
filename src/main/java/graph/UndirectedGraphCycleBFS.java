package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraphCycleBFS {
    private boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent,-1);

        for(int i=0;i<V;i++){
            //if node not visited check for cycle on that node
            // this loops helps if the main.graph is not connected
            if(!visited[i]){
                if(checkForCycle(adj, i, visited, parent))
                    return true;
            }
        }

        return false;
    }

    //main logic to find cycle in main.graph
    private boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int i, boolean[] visited, int[] parent) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(i, -1)); // let parent of head node be -1
        visited[i] = true;

        while(!que.isEmpty()){
            int node = que.peek().first;
            int par = que.peek().second;
            que.poll();

            for(Integer it: adj.get(node)){
                if(visited[it] == false){
                    que.add(new Node(it, node));
                    visited[it] = true;
                }
                //if adjecent node is visited and is not current nodes parent node
                else if(par != it){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int vertex = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<vertex;i++){
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(2).add(4);
        adj.get(4).add(2);
        adj.get(3).add(4);
        adj.get(4).add(3);

        UndirectedGraphCycleBFS obj = new UndirectedGraphCycleBFS();
        boolean ans = obj.isCycle(vertex, adj);
        if (ans)
            System.out.println("Cycle exist");
        else
            System.out.println("Cycle doesn't exist");

    }
}

class Node{
    int first;
    int second;

    public Node(int first, int second) {
        this.first = first;
        this.second = second;
    }
}