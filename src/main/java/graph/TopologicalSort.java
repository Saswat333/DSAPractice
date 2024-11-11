package graph;

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {
    public static void main(String[] args) {
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

        TopologicalSort obj = new TopologicalSort();
        int[] ans = obj.topoSort(V, adj);
        for (int node : ans) {
            System.out.print(node + " ");
        }
        System.out.println("");
    }

    private int[] topoSort(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[v];
        Stack<Integer> stk = new Stack<>();//store the result when doing dfs
        for(int i=0;i<v;i++){
            if(!vis[i]){
                //if not visited then dfs traverse on it
                dfs(i, stk, adj, vis);
            }
        }

        int[] result = new int[v];
        int cnt=0;
        while(!stk.isEmpty()){
            result[cnt++] = stk.pop();
        }
        return result;
    }

    private void dfs(int node, Stack<Integer> stk, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;

        for(int nextNode: adj.get(node)){
            if(!vis[nextNode]){
                dfs(nextNode, stk, adj, vis);
            }
        }
        //from the last visited node in the path push it to stack
        stk.push(node);
    }
}
