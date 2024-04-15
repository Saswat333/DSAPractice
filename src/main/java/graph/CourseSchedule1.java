package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule1 {
    public static void main(String[] args) {
        int[][] prerequisites = new int[3][2];
        prerequisites[0][0] = 1;
        prerequisites[0][1] = 0;

        prerequisites[1][0] = 2;
        prerequisites[1][1] = 1;

        prerequisites[2][0] = 3;
        prerequisites[2][1] = 2;

        int[][] pre1 = new int[4][2];
        pre1[0][0] = 3;
        pre1[0][1] = 2;

        pre1[1][0] = 1;
        pre1[1][1] = 3;

        pre1[2][0] = 0;
        pre1[2][1] = 1;

        pre1[3][0] = 3;
        pre1[3][1] = 0;

        int N = 4; //no of nodes != array length
        CourseSchedule1 obj = new CourseSchedule1();
        boolean ans = obj.isPossible(N, pre1);
        if (ans)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private boolean isPossible(int v, int[][] prerequisites) {
        //create a graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<prerequisites.length;i++){
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        System.out.println(adj);

        // do topo sorting
        // Fill in-degree
        int[] inDegree = new int[v];
        for(int i=0;i<v;i++){
            for(int it: adj.get(i)){
                inDegree[it]++;
            }
        }
        // add all 0 indefree to que
        Queue<Integer> que = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(inDegree[i]==0)
                que.add(i);
        }
        //pop out all 0 indegree from que and reduce indegree untill any indegree turns 0
        ArrayList<Integer> topo = new ArrayList<>();
        while(!que.isEmpty()){
            int node = que.poll();
            topo.add(node);

            for(int it: adj.get(node)){
                inDegree[it]--;
                if(inDegree[it]==0)
                    que.add(it);
            }
        }

        //if the topo list is of size v, then we have ans
        if(topo.size()==v)
            return true;
        return false;
    }
}
