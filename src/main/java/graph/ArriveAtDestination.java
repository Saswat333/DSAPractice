package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//You are in a city that consists of n intersections numbered from 0 to n – 1 with bi-directional roads between some intersections.
// The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
//
//You are given an integer n and a 2D integer array ‘roads’ where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel.
// Question: You want to know in how many ways you can travel from intersection 0 to intersection n – 1 in the shortest amount of time.
//if there are 5, then in how many ways we can travel from 0 to 4th node in shortest amount of time
//Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
//Note: We have to keep track of number of ways, ways[node] = ways[node1] + ways[node2] + ways[node3]
// if we keep calculing nodes from 0 to n-1th nodes by the time we reach n-1 we will have sum of all the ways to reach dest
//so we have to maintain a extra ways array for each node, increase the num of ways when you get a more smaller dist
public class ArriveAtDestination {
    public static void main(String[] args) {
//        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
//        int n = 7;

        int[][] roads = {{0,1,1000000000},{0,3,1000000000},{1,3,1000000000},{1,2,1000000000},{1,5,1000000000},{3,4,1000000000},{4,5,1000000000},{2,5,1000000000}};
        int n=6;

        ArriveAtDestination obj = new ArriveAtDestination();
        int ans = obj.countPaths(n, roads);

        System.out.print(ans);
        System.out.println();
    }

    private int countPaths(int n, int[][] roads) {
        //adj list
        ArrayList<ArrayList<Pair2>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<roads.length;i++){
            adj.get(roads[i][0]).add(new Pair2(roads[i][1], (long)roads[i][2]));
            adj.get(roads[i][1]).add(new Pair2(roads[i][0], (long)roads[i][2]));
        }
//        System.out.println(adj.size());
//        for(int i=0;i<n;i++){
//            for(graph.Pair1 it: adj.get(i)){
//                System.out.print(i+", ");
//                System.out.print(it.first +", ");
//                System.out.println(it.second);
//            }
//        }
        PriorityQueue<Pair2> pq = new PriorityQueue<>((x,y)->Long.compare(x.second, y.second));
        //dist(time) and ways array
        // time can be 10^9, so if we add prev time with new time then it integer might overflow so we take time as long
        long[] time = new long[n];
        int[] ways = new int[n];

        Arrays.fill(time, Long.MAX_VALUE/2);
        time[0] = 0;
        ways[0] = 1; //default there is atleast oneway to reach
        pq.add(new Pair2(0,0)); //node:0, dist:0
        int MOD = (int)(1e9 +7);

        while(!pq.isEmpty()){
            Pair2 cur = pq.poll();
            int curNode = cur.first;
            long curDist = cur.second;

            for(Pair2 next: adj.get(curNode)){
                int nextNode = next.first;
                long nextDist = next.second;

                // This ‘if’ condition signifies that this is the first
                // time we’re coming with this short distance, so we push
                // in PQ and keep the no. of ways the same
                if(curDist+nextDist < time[nextNode]){
                    time[nextNode] = curDist+nextDist;
                    pq.add(new Pair2(nextNode, curDist+nextDist));
                    ways[nextNode] = ways[curNode];
                }
                // If we again encounter a node with the same short distance
                // as before, we simply increment the no. of ways.
                else if(curDist+nextDist == time[nextNode]){
                    ways[nextNode] = (ways[nextNode]+ways[curNode])%MOD;
                }
            }
        }
        // Finally the ways[n-1] will have sum of all the ways
        return (ways[n-1])%MOD;
    }
}
