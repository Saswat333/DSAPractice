package graph;//Using FindUnion to solve the MST problem
// we store the edge weights in asc sorted order

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    int[] parent;
    int[] rank;

    Kruskal(int V){
        parent = new int[V];
        rank = new int[V];
        for(int i=0;i<V;i++){
            parent[i] = i;
        }
    }
    public static void main(String[] args) {
        int V = 5;
        int[][] edges =  {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        Kruskal obj = new Kruskal(V);
        int mstWt = obj.spanningTree(V, edges);
        System.out.println("The sum of all the edge weights: " + mstWt);
    }

    private int spanningTree(int v, int[][] edges) {
        ArrayList<Tuple> adj = new ArrayList<>();
        //O(E)- extracting edge info
        for(int i=0;i<edges.length;i++){
            adj.add(new Tuple(edges[i][0],edges[i][1],edges[i][2]));
        }
        Collections.sort(adj, (a,b)->a.third-b.third); // ElogE for sorting
        //Collections.sort(adj, Comparator.comparingInt(a -> a.third));
        int sum=0;

        //O(E*2*4)-> for nbumber of edges time we are doing the union and find operation(each take 4 const time)
        for(int i=0;i<adj.size();i++){
            Tuple curNode = adj.get(i);
            int a = curNode.first;
            int b = curNode.second;
            int cost = curNode.third;

            //if the node doesn't have same parent join it
            if(findParent(a)!=findParent(b)){
                unionByRank(a,b);
                sum += cost;
            }
        }
        return sum;
    }

    private int findParent(int a){
        if(parent[a] ==  a)
            return a;
        return parent[a] = findParent(parent[a]);
    }

    private void unionByRank(int u, int v){
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

//1. First, we need to extract the edge information(if not given already) from the given adjacency list in the format of (wt, u, v)
// where u is the current node, v is the adjacent node and wt is the weight of the edge between node u and v and we will store the tuples in an array.
//2. Then the array must be sorted in the ascending order of the weights so that while iterating we can get the edges
// with the minimum weights first.
//3. After that, we will iterate over the edge information, and for each tuple, we will apply the  following operation:
    //a. First, we will take the two nodes u and v from the tuple and check if the ultimate parents of both nodes are the
    // same or not using the findUPar() function provided by the Disjoint Set data structure.
    //b. If the ultimate parents are the same, we need not do anything to that edge as there already exists a path between
    // the nodes and we will continue to the next tuple.
    //c. If the ultimate parents are different, we will add the weight of the edge to our final answer(i.e. mstWt
    // variable used in the following code) and apply the union operation(i.e. either unionBySize(u, v) or unionByRank(u, v)) with the nodes u and v.
    // The union operation is also provided by the Disjoint Set.
//Finally, we will get our answer (in the mstWt variable as used in the following code) successfully.

//Time: extracting edge info from from edges = e, sort = e.loge, doing union find = e.const => E + E*LOGE + E => E*logE