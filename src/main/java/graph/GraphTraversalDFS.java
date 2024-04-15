package graph;

import java.util.ArrayList;

public class GraphTraversalDFS {

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[V];
        //consider the first root node is 0 indexed value
        dfs(0,visited, adj, res);
        return res;
    }

    //main dfs logic
    private void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> res) {
        visited[node] = true;
        res.add(node);

        for(Integer it: adj.get(node)){
            if(!visited[it]){
                // apply dfs on the adjecent neighbour node
                dfs(it, visited, adj, res);
            }
        }
    }

    public static void main(String[] args) {
        int numberOfNodes = 5;
        ArrayList <ArrayList< Integer >> adj = new ArrayList < > ();
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

        GraphTraversalDFS obj = new GraphTraversalDFS();
        ArrayList<Integer> res = obj.dfsOfGraph(numberOfNodes, adj);

        System.out.println(res);
    }
}
