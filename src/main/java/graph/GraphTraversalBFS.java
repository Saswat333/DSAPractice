package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversalBFS {
    //No of vertices
    private int V;
    //Adjacency list (array of type main.LinkedList
    private LinkedList<Integer> adj[];

    //Below is logic for bfs on main.graph, s = source node
    public void BFS(int s){
        boolean[] visited = new boolean[V];
        Queue<Integer> que = new LinkedList<>();
        // mark current node as visited and enqueue it
        visited[s] = true;
        que.add(s);

        while(!que.isEmpty()){
            //Dequeue a vertex from queue and print it
            s = que.poll();
            System.out.print(s +", ");
            // get all the adj vertices of the dequeued vertex s, if an adjacent has not been visited then mark as
            //visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while(i.hasNext()){
                // i = adj node value, from linkedlist
                int n = i.next();
                if(!visited[n]){
                    visited[n] = true;
                    que.add(n);
                }
            }
        }
    }

    GraphTraversalBFS(int totalVertices){
        V = totalVertices;
        adj = new LinkedList[totalVertices];
        //fill all the array indexes with empty LL nodes
        for(int i=0;i<totalVertices; ++i){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w){
        // at index v, add edge to node w
        adj[v].add(w);
    }

    public static void main(String[] args) {
        int totalVertices =4;
        GraphTraversalBFS g = new GraphTraversalBFS(totalVertices);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        GraphTraversalBFS g2 = new GraphTraversalBFS(5);
        g2.addEdge(0,1);
        g2.addEdge(1,0);
        g2.addEdge(0,3);
        g2.addEdge(3,0);
        g2.addEdge(1,3);
        g2.addEdge(3,1);
        g2.addEdge(1,2);
        g2.addEdge(2,1);
        g2.addEdge(2,3);
        g2.addEdge(3,2);
        g2.addEdge(3,4);
        g2.addEdge(4,3);
        g2.addEdge(2,4);
        g2.addEdge(4,2);

        System.out.println(
                "Following is Breadth First Traversal "
                        + "(starting from vertex 2)");
//        g.BFS(2);
        g2.BFS(0);
    }
}
