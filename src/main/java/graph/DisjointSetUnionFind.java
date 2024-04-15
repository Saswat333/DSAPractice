package graph;

import java.util.Arrays;

public class DisjointSetUnionFind {
    int[] size;
    int[] rank;
    int[] parent;
    public DisjointSetUnionFind(int n){
        size = new int[n];
        parent = new int[n];
        rank = new int[n];
        Arrays.fill(size, 1);
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }

    public static void main(String[] args) {
        DisjointSetUnionFind obj = new DisjointSetUnionFind(7);
        obj.unionBySize(0,1);
        obj.unionBySize(1,2);
        obj.unionBySize(3,4);
        obj.unionBySize(5,6);
        obj.unionBySize(4,5);

        //if 3 and 7 same component or not
//        if(obj.findParent(2) == obj.findParent(6)){
//            System.out.println("Same");
//        }else {
//            System.out.println("Not Same");
//        }
//        //now union 3 and 7 to same component
//        obj.unionBySize(2,6);
//
//        //if 3 and 7 same component or not
//        if(obj.findParent(2) == obj.findParent(6)){
//            System.out.println("Same");
//        }else {
//            System.out.println("Not Same");
//        }

        //if 3 and 7 same component or not
        if(obj.findParent(2) == obj.findParent(6)){
            System.out.println("Same");
        }else {
            System.out.println("Not Same");
        }
        //now union 3 and 7 to same component
        obj.unionByRank(2,6);

        //if 3 and 7 same component or not
        if(obj.findParent(2) == obj.findParent(6)){
            System.out.println("Same");
        }else {
            System.out.println("Not Same");
        }
    }

//    private int findParent(int node) {
//        if(node != parent[node])
//            parent[node] = findParent(parent[node]);
//        return parent[node];
//    }

    private int findParent(int node) {
        if(node == parent[node])
            return node;
        return parent[node] = findParent(parent[node]);
    }

    private void unionBySize(int u, int v) {
        u = findParent(u);
        v = findParent(v);

        if(u == v)
            return;

        if(size[u] < size[v]){
            parent[u] = v;
            size[v] += size[u];
        }else {
            parent[v] = u;
            size[u] += size[v];
        }
    }

    //Rank: The rank of a node generally refers to the distance (the number of nodes including the leaf node) between the
    // furthest leaf node and the current node. Basically rank includes all the nodes beneath the current node.
    //Algo: we will connect the ultimate parent with a smaller rank to the other ultimate parent with a larger rank.
    // But if the ranks are equal, we can connect any parent to the other parent and we will increase the rank by one for the parent node to whom we have connected the other one.
    private void unionByRank(int u, int v) {
        u = findParent(u);
        v = findParent(v);

        if(u==v){
            return;
        }
        if(rank[u]<rank[v]){
            parent[u] = v;
        } else {
            parent[v] = u;
            rank[u] += 1;
        }
    }
}
