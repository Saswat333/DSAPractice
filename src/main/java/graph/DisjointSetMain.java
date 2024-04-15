package graph;

import java.util.Arrays;

public class DisjointSetMain {
    int[] size;
    int[] parent;

    DisjointSetMain(int v){
        size = new int[v];
        parent = new int[v];
        Arrays.fill(size, 1);
        for(int i=0;i<v;i++){
            parent[i] = i;
        }
    }

    public int findParent(int a){
        if(a == parent[a])
            return parent[a];
        return parent[a] = findParent(parent[a]);
    }
    public void unionBySize(int u, int v){
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
}
