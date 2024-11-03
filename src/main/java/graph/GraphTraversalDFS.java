package graph;

import java.util.ArrayList;
import java.util.Stack;

public class GraphTraversalDFS {

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[V];
        //consider the first root node is 0 indexed value
        dfs(0,visited, adj, res);
        return res;
    }

    //dfs logic: using recursion
    private void dfs(int currentNode, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> res) {
        visited[currentNode] = true;
        res.add(currentNode);

        for(Integer it: adj.get(currentNode)){
            if(!visited[it]){
                // apply dfs on the adjecent neighbour node
                dfs(it, visited, adj, res);
            }
        }
    }

    //dfs using stack
    private ArrayList<Integer> dfsUsingStack(int node, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[node];
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();

        stk.add(0);
        visited[0] = true;

        while(!stk.isEmpty()){
            Integer temp = stk.pop();
            res.add(temp);

            for(Integer it: adj.get(temp)){
                if(!visited[it]){
                    // apply dfs on the adjecent neighbour node
                    stk.add(it);
                    visited[it] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int numberOfNodes = 5;
        ArrayList <ArrayList< Integer >> adj = new ArrayList <> ();
        for (int i = 0; i < numberOfNodes; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(2).add(4);
        adj.get(4).add(2);
        adj.get(4).add(1);
        adj.get(1).add(4);

        GraphTraversalDFS obj = new GraphTraversalDFS();
        ArrayList<Integer> res = obj.dfsOfGraph(numberOfNodes, adj);
        // using recursion
        System.out.println("DFS Recursion: "+res);

        // using stack
        ArrayList<Integer> res1 = obj.dfsUsingStack(numberOfNodes, adj);
        System.out.println("DFS Stack: "+res1);

        // using optimized recursion
        boolean[] visited = new boolean[numberOfNodes];
        ArrayList<Integer> res2 = new ArrayList<>();
        int currentNode = 0;
        obj.dfs(currentNode,visited, adj, res2);
        System.out.println("DFS Stack: "+res2);
    }
}
