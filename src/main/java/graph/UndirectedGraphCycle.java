package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraphCycle {
    private boolean isCycleBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent,-1);

        //check all connected components
        for(int i=0;i<V;i++){
            //if node not visited check for cycle on that node
            // this loops helps if the main.graph is not connected
            if(!visited[i]){
                if(checkForCycleBFS(adj, i, visited, parent))
                    return true;
            }
        }

        return false;
    }

    private boolean checkForCycleBFS(ArrayList<ArrayList<Integer>> adj, int i, boolean[] visited, int[] parent) {
        //Pair1(int first, int second)
        Queue<Pair1> que = new LinkedList<>();
        que.add(new Pair1(i, -1)); // let parent of head node be -1
        visited[i] = true;

        while(!que.isEmpty()){
            int node = que.peek().first;
            int par = que.peek().second;
            que.poll();

            for(Integer it: adj.get(node)){
                if(visited[it] == false){
                    que.add(new Pair1(it, node));
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

    private boolean isCycleDFS(int V, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent,-1);
        
        for(int i=0;i<V;i++){
            if(!visited[i]){
                //parent value for root node will be -1
                if(checkForCycleDFS(i, adj, visited, -1))
                    return true;
            }
        }
        return false;
    }

    private boolean checkForCycleDFS(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
        visited[i] =true;

        for(Integer neighbor: adj.get(i)){
            //if neighbor not visited then call the adjecent node
            if(!visited[neighbor]){
                //parent of neighbor will be current node
                if(checkForCycleDFS(neighbor, adj, visited, i)){
                    //if we get true means we have cycle, so return true
                    return true;
                } else if (neighbor != parent) {
                    //check if the current nodes neighbor is not parent, then we have cycle
                    return true;
                }
            }
        }
        return false; //no cycle detected in the current component
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

        UndirectedGraphCycle obj = new UndirectedGraphCycle();
//        boolean ans = obj.isCycleBFS(vertex, adj);
        boolean ans = obj.isCycleDFS(vertex, adj);
        if (ans)
            System.out.println("Cycle exist");
        else
            System.out.println("Cycle doesn't exist");

    }
}

/*
IDEA:
- Using a parent array which will store the current nodes parent as well.
- rest of the logic is just doing BFS/DFS and if we visit a node which is visited, check with current nodes parent
if the neightbour is not parent of current node then there is a cycle
* */