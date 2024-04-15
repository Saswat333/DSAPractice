package graph;

import java.util.*;

//743: You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges
// times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel
// from source to target.
//We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
// If it is impossible for all the n nodes to receive the signal, return -1.
//Intution: we have to find the shortest time to reach the farthest node
public class NetworkDelay {
    public static void main(String[] args) {
        NetworkDelay obj = new NetworkDelay();
//        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
//        int n = 4, k = 2;

//        int[][] times = {{1,2,1}};
//        int n = 2, k = 1;

        int[][] times = {{1,2,1}};
        int n = 2, k = 2;

        int result = obj.findNetworkTime(times, n, k);
        System.out.println("Result: "+ result);
    }

    private int findNetworkTime(int[][] times, int n, int k) {
        PriorityQueue<Pair1> que = new PriorityQueue<>((x,y)->x.second-y.second);
        //crete adj
        ArrayList<ArrayList<Pair1>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<times.length;i++){
            adj.get(times[i][0]).add(new Pair1(times[i][1], times[i][2]));
        }
//        System.out.println(adj.size());
//        for(int i=0;i<n;i++){
//            for(graph.Pair1 it: adj.get(i)){
//                System.out.print(i+", ");
//                System.out.print(it.first +", ");
//                System.out.println(it.second);
//            }
//        }

        que.add(new Pair1(k, 0));//node , distance

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        while (!que.isEmpty()){
            Pair1 cur = que.poll();
            int curNode = cur.first;
            int curDist = cur.second;
//            System.out.println("Queue: node: "+curNode+" dist:"+curDist);
            for(Pair1 it:adj.get(curNode)){
                int nextNode = it.first;
                int nextDist = it.second;

                if(curDist+nextDist < dist[nextNode]){
                    dist[nextNode] = curDist+nextDist;
                    que.add(new Pair1(nextNode, curDist+nextDist));
                }
            }
        }

//        System.out.println(Arrays.toString(dist));
        int result =0;
        for(int i=1;i<=n;i++){
            if(dist[i]==Integer.MAX_VALUE)
                return -1;
            else{
                result = Math.max(dist[i], result);
            }
        }
        return result;
    }
}
