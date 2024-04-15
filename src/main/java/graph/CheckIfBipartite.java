package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckIfBipartite {
    //Sol1: bruteforce: Manually traverse over each node and assign alt colors
    //Odd cycle graph willl never be bipartite, even cycle graph can be bipartite
    public static void main(String[] args)
    {
        // V = 4, E = 4
        ArrayList <ArrayList< Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        CheckIfBipartite obj = new CheckIfBipartite();
        boolean ans = obj.isBipartite(4, adj);
        System.out.println(ans?"Is bipartite":"Is not bipartite");

        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        boolean ans1 = obj.isBipartite2D(graph);
        System.out.println(ans1?"Is bipartite":"Is not bipartite");
    }

    //here the nodes will be given in adjacency list
    private boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        //instead of visited array we have color arr and fill with -1
        int[] color = new int[V];
        Arrays.fill(color,-1);

        //for each component do a dfs traversal
        //we will use 0 and 1 as colors
        for(int i=0;i<V;i++){
            if(color[i]==-1){
                if(!dfs(i, 0, color, adj))
                    return false;
            }
        }
        return true;
    }

    private boolean dfs(int node, int curColor, int[] color, ArrayList<ArrayList<Integer>> adj){
        color[node] = curColor;

        //traverse the neighbour nodes of current node
        //Only if the color of nextNode is currentColor then return false
        for(int nextNode: adj.get(node)){
            if(color[nextNode]==-1){
                //not traversed, call dfs
                if(!dfs(nextNode, 1 - curColor, color, adj))
                    return false;
                else if(color[nextNode] == curColor)
                    return false;
            }
        }
        return true;
    }

    private boolean isBipartite2D(int[][] adj) {
        int V = adj.length;
        //instead of visited array we have color arr and fill with -1
        int[] color = new int[V];
        Arrays.fill(color,-1);
        System.out.println("Color initial: "+Arrays.toString(color));;

        //for each component do a dfs traversal
        //we will use 0 and 1 as colors
        for(int i=0;i<V;i++){
            if(color[i]==-1){
                if(!dfs2D(i, 0, color, adj))
                    return false;
            }
        }
        System.out.println("Color final: "+Arrays.toString(color));
        return true;
    }

    private boolean dfs2D(int node, int curColor, int[] color, int[][] adj){
        color[node] = curColor;

        //traverse the neighbour nodes of current node
        //Only if the color of nextNode is currentColor then return false
        for(Integer nextNode: adj[node]){
            //if uncolored/not traverseed
            if(color[nextNode]==-1){
                //not traversed, call dfs
                if(!dfs2D(nextNode, 1 - curColor, color, adj))return false;
            }
            else if(color[nextNode] == curColor){
                return false;
            }
        }
        return true;
    }
}
