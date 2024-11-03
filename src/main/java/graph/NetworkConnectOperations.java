package graph;

import java.util.Arrays;

//You are given a graph with n vertices and m edges. You can remove one edge from anywhere
// and add that edge between any two vertices in one operation. Find the minimum number of operations that will be required to make the graph connected. If it is not possible to make the graph connected, return -1.
//Objective is to make the graph connected, by moving an existing edge in graph(can't add edges that does't exisit)
// We need n-1 edges to make the graph connected
//we can conclude that if a graph contains nc-1 extra-edges, we can make the graph connected with just nc-1
// operations(where nc = no. of components of the graph).
//first find no of extra edges needed(do union operation on the give components) and then find the number of components in graph
//1. extract all edge info
//2. to find how many extra edges needed to completely connect the graph we have to do union operation, #union = #extra edges needed
//3. now count the components just by finding the ultimate parent of each node.
//4. If count of  extra edges is greater or same we will return the answer that is(#components-1), else return -1
public class NetworkConnectOperations {
    int[] size;
    int[] parent;

    NetworkConnectOperations(int v){
        size = new int[v];
        parent = new int[v];
        Arrays.fill(size, 1);
        for(int i=0;i<v;i++){
            parent[i] = i;
        }
    }

    private int findParent(int a){
        if(a == parent[a])
            return parent[a];
        return parent[a] = findParent(parent[a]);
    }
    private void unionBySize(int u, int v){
        u = findParent(u);
        v = findParent(v);
        if(u==v)
            return;
        if(size[u]>size[v]){
          parent[v] = u;
        }else{
            parent[u]=v;
            size[v] += size[u];
        }
    }

    public static void main(String[] args) {
        int V = 9;
        int[][] edge = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}, {4, 5}, {5, 6}, {7, 8}};

        NetworkConnectOperations obj = new NetworkConnectOperations(9);
        int ans = obj.solveByUnionFind(V, edge);
        System.out.println("The number of operations needed: " + ans);
    }

    private int solveByUnionFind(int n, int[][] connections) {
        int validEdges = 0;
        int m = connections.length;

        for(int i=0;i<m;i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            if (findParent(u) == findParent(v))
                validEdges+=1;
            else
                unionBySize(u, v);
        }
        int countComponent = 0;
        for(int i=0;i<n;i++){
            if(findParent(i) == i)
                countComponent++;
        }
        int requiredEdges = countComponent-1;
        if(validEdges >= requiredEdges)
            return requiredEdges;
        return -1;
    }
}
