package graph;

import java.util.ArrayList;

public class CheckCycleInDirected {
    public static void main(String[] args) {
        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);

        CheckCycleInDirected obj = new CheckCycleInDirected();
        boolean ans = obj.isCyclic(V, adj);
        if (ans)
            System.out.println("True");
        else
            System.out.println("False");
    }

    private boolean isCyclic(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[v];
        int[] pathVis = new int[v];

        for(int i=0;i<v;i++){
            if(vis[i]==0){
                //if the dfs check returns true means there is a cycle detected
                if(dfsCheck(i, adj, vis, pathVis)) return true;
            }
        }
        return false;
    }

    private boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] pathVis) {
        vis[node] = 1;
        pathVis[node] = 1;

        //traverse adjacent nodes
        for(int nextNode: adj.get(node)){
            //node is not visited
            if(vis[nextNode]==0){
                if(dfsCheck(nextNode, adj, vis, pathVis)) return true;
            }
            //node visited is true and path visited is also true, that means the node is visited again in same path
            //so there is cycle return true
            else if(pathVis[nextNode]==1){
                return true;
            }
        }

        pathVis[node] = 0;
        return false;
    }
}