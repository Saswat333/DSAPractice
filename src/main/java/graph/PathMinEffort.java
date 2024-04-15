package graph;

import java.util.Arrays;
import java.util.PriorityQueue;

//You are a hiker preparing for an upcoming hike. You are given heights,
// a 2D array of size rows x columns, where heights[row][col] represents the height of the cell (row, col).
// You are situated in the top-left cell, (0, 0), and
// you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e.,0-indexed). You can move up, down, left, or right,
// and you wish to find a route that requires the minimum effort.
//A route’s effort is the maximum absolute difference in heights between two consecutive cells of the route.
//Intution: If we need minimum effort mean we have to avoid bif elevations in the path, so we will have to avoid the maximum
//elevation from the all calculated elevation, i.e out of all difference between nodes avoid path which has max difference
// p1: 1-2-2-5  p2:1-3-5-5
//p1: max(1,0,3)=3, p2: max(2,2,2)=2
//so we chose p2 as it has minimum elevation or minimum(max diff)
public class PathMinEffort {
    public static void main(String[] args) {
        int[][] heights={{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};

        PathMinEffort obj = new PathMinEffort();
        int ans = obj.MinimumEffort(heights);

        System.out.print(ans);
        System.out.println();
    }

    private int MinimumEffort(int[][] heights) {
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};
        // use a priority que to return the
        PriorityQueue<Tuple> que = new PriorityQueue<>((p,q)->p.first-q.first);

        int n= heights.length, m = heights[0].length;

        //visited array or distance array will be a matrix, initially all cell marked Int.max
        //un-visit it and mark with the distanced needed to travel from its source(0,0) to that cell
        int[][] dist = new int[n][m];
        for(int[] row: dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dist[0][0] = 0;
        que.add(new Tuple(0, 0,0));// distance, row, col

        while(!que.isEmpty()){
            Tuple curNode = que.poll();
            int curR = curNode.second;
            int curC = curNode.third;
            int curDiff = curNode.first;

            //check if reached destination
            if(curR==n-1 && curC==m-1)
                return curDiff;

            for(int i=0;i<4;i++){
                int nextR = curR+dx[i];
                int nextC = curC+dy[i];

                //check validity
                if(nextR>=0 && nextR<n && nextC>=0 && nextC<m){
                    //Effort can be calculated as the max value of differences
                    // between the heights of the node and its adjacent nodes
                    // we will always compare with previous path difference and current difference, so at any point the
                    //dist will have the max difference needed for that node
                    int newEffort = Math.max(Math.abs(heights[curR][curC] - heights[nextR][nextC]), curDiff);
                    //from the max difference till that node we will always pick min and update, so by the time we reach
                    //destination node we will always have the min(max difference) of the paths
                    if(newEffort < dist[nextR][nextC]) {
                        dist[nextR][nextC] = newEffort;
                        que.add(new Tuple(newEffort, nextR, nextC));
                    }

                }
            }

        }
        //if distance is unreachable then return 0
        return 0;
    }
}
