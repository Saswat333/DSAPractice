package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule2 {
    public static void main(String[] args) {
        CourseSchedule2 obj = new CourseSchedule2();

        int[][] prerequisites = new int[][]{{1,0},{2,1},{3,2}};

        // input:2
        int[][] prerequisites1 = new int[][]{{1,0}};

        int N = 2;//no of nodes
        int[] ans = obj.findOrder(N, prerequisites1);

        for (int task : ans) {
            System.out.print(task + " ");
        }
        System.out.println("");
    }

    private int[] findOrder(int v, int[][] prerequisites) {
        //create adjacency list of graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        System.out.println(prerequisites.length);

        for(int i=0;i<prerequisites.length;i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

//        System.out.println(adj);
        //create in-degree array and count the in-degrees of nodes
        int[] inDegree = new int[v];
        for(int i=0;i<v;i++){
            for(int it: adj.get(i)){
                inDegree[it]++;
            }
        }
        // push all the 0 indegree to queue
        Queue<Integer> que = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(inDegree[i]==0)
                que.add(i);
        }
        // do bfs on the indegree nodes
        ArrayList<Integer> topo = new ArrayList<>();
        while(!que.isEmpty()){
            int node = que.poll();
            topo.add(node); //keeps the nodes values in result array (index= node_val)

            for(int it: adj.get(node)){
                inDegree[it]--; //reduce the in-degree of neighbours as node is removed
                if(inDegree[it]==0)
                    que.add(it);
            }
        }
        System.out.println(topo);
        // if result array length is no of nodes means we have got all the elements which occured in sequence
        // and we didnt miss any course : we can complete all courses


        if(topo.size() == v){
            int[] result = new int[v];
            int cntr=0;
            for(int i: topo)
                result[cntr++] = i;
            return result;
        }
        return new int[]{};
    }
}
