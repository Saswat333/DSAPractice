package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheapestFlightStop {
    public static void main(String[] args) {
//        int n = 4, src = 0, dst = 3, K = 1;
//        int[][] flights={{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};

        //input-2
        int n = 2, src=1, dst=0, K=0; // result =-1;
        int[][] flights = {{0,1,2}};

        CheapestFlightStop obj = new CheapestFlightStop();
        int ans = obj.cheapestFLight(n,flights,src,dst,K);

        System.out.print(ans);
        System.out.println();
    }

    private int cheapestFLight(int n, int[][] flights, int src, int dst, int k) {
        //create adj list from flight edges
        // src ->{dest1, cost}
        ArrayList<ArrayList<Pair1>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<flights.length;i++){
            adj.get(flights[i][0]).add(new Pair1(flights[i][1], flights[i][2]));
        }
        //create queue which store <stops, {node, dist}> and add the source node
        Queue<Tuple> que = new LinkedList<>();
        que.add(new Tuple(0, src, 0)); //stop=0, node=src, distance=0(initial)
        //dist array to store the updated total dist from source
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        //iterate queue popping out the element in the front as we will store only the stops and stops increase by
        // we will need normal queue
        while (!que.isEmpty()){
            Tuple cur = que.poll();
            int curStop = cur.first;
            int curNode = cur.second;
            int curCost = cur.third;

            //stop as soon as we reach stops limit
            if(curStop > k)continue;
            for(Pair1 it: adj.get(curNode)){
                int nextNode = it.first;
                int nextCost = it.second;

                // update the queue if only
                if(curCost+nextCost<dist[nextNode] && curStop<=k){
                    dist[nextNode] = curCost + nextCost;
                    que.add(new Tuple(curStop+1, nextNode, dist[nextNode]));
                }
            }
        }

        //if destination is reachable means it doesnt have int.max then return the calculate value from dist
        if(dist[dst]==Integer.MAX_VALUE)
            return -1;
        return dist[dst];
    }
}

//n = 2, flight = [[0,1,2]], src=1, dst=0, k=0, result =-1, expected=-1