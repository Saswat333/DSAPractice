package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class ConnectedComponent {
    public static void main(String[] args) {
        int[][] isConnected1 = {{1,1,0},{1,1,0},{0,0,1}};
        int[][] isConnected2 = {{1,1,1,0,0},{1,1,1,0,0},{0,1,1,0,0},{0,0,0,1,0},{0,0,0,0,1}};
        System.out.println(Arrays.deepToString(isConnected2));
        ConnectedComponent obj = new ConnectedComponent();
        System.out.println(obj.countComponents(isConnected2));
    }

    public int countComponents(int[][] isConnected){
        int V = isConnected.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<Integer>());
        }
        //add connected components to the adj list
        for(int i=0;i<V;i++){
            for(int j=0; j<V;j++){
                if(isConnected[i][j] == 1 && i != j){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        int[] vis = new int[V];
        int count =0;
        //call all components untill they are visited
        for( int i=0;i<V;i++){
            if(vis[i] == 0){
                count++;
                dfs(i, adj, vis);
            }
        }
        return count;
    }

    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis){
        vis[node] = 1;
        for(Integer it: adj.get(node)){
            if(vis[it] == 0){
                dfs(it, adj, vis);
            }
        }
    }
}
