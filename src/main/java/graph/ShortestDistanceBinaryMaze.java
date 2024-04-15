package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceBinaryMaze {
    public static void main(String[] args) {
        int[] source={0,1};
        int[] destination={2,2};

        int[][] grid={{1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}};

        ShortestDistanceBinaryMaze obj = new ShortestDistanceBinaryMaze();
        int res = obj.shortestPath(grid, source, destination);

        System.out.print(res);
        System.out.println();

        // Leetcode
//        int[][] grid1 = {{0,0,0},{1,1,0},{1,1,0}};
        int[][] grid1 = {{1,0,0},{1,1,0},{1,1,0}};

        int res1 = obj.shortestPathLeetCode(grid1);

        System.out.print("Leetcode result: "+res1);
        System.out.println();
    }

    private int shortestPath(int[][] grid, int[] source, int[] destination) {
        //delta row and cols
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        //if source is destination then distance will be 0
        if(source[0]==destination[0] && source[1]==destination[1])
            return 0;
        //store the neightbour cells in the format <distance, <i,j>>
        Queue<Tuple> que = new LinkedList<>();
        int n= grid.length, m = grid[0].length;


        //As we will use dijkstra, create a distance matrix which will have all the cells initially marked as Int.MAX

        int[][] dist = new int[n][m];
        for(int[] row:dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        //push the 0th source node
        dist[source[0]][source[1]] = 0;
        que.add(new Tuple(0, source[0], source[1]));

        //iterate throught the maze by poping elements from the queue
        //push whatever shortest path is found
        while(!que.isEmpty()){
            Tuple curNode = que.poll();
            int curDist = curNode.first;
            int curR = curNode.second;
            int curC = curNode.third;

            for(int i=0;i<4;i++){
                int nextR = curR+dx[i];
                int nextC = curC+dy[i];

                //validaity: must be inside the grid and must be 1 and if curdist+1 gives smaller distance than than
                // existing distance value, i.e: curDist+1<dist[nr][nc]
                if(nextR>=0 && nextR <n && nextC>=0 && nextC<m && grid[nextR][nextC]==1 &&
                curDist+1<dist[nextR][nextC]){
                    //update the new shortest dist
                    dist[nextR][nextC]=curDist+1;

                    //stop when we get destination cell
                    if(nextR == destination[0] && nextC == destination[1])
                        return curDist+1;

                    que.add(new Tuple(curDist+1, nextR, nextC));
                }
            }
        }

        //if no path return -1
        return -1;

    }

    private int shortestPathLeetCode(int[][] grid) {
        int n= grid.length;
        int[] source = {0,0};
        int[] destination = {n-1,n-1};

        //delta row and cols
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
        //if source is 1 then there is no result return -1
        if(grid[0][0]==1)
            return -1;
        //if source is destination then return distance 1(base path =1), possible if single cell
        if(source[0]==destination[0] && source[1]==destination[1])
            return 1;
        //store the neightbour cells in the format <distance, <i,j>>
        Queue<Tuple> que = new LinkedList<>();

        //As we will use dijkstra, create a distance matrix which will have all the cells initially marked as Int.MAX

        int[][] dist = new int[n][n];
        for(int[] row:dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        //push the 0th source node
        dist[source[0]][source[1]] = 0;
        que.add(new Tuple(1, source[0], source[1]));

        //iterate throught the maze by poping elements from the queue
        //push whatever shortest path is found
        while(!que.isEmpty()){
            Tuple curNode = que.poll();
            int curDist = curNode.first;
            int curR = curNode.second;
            int curC = curNode.third;

            for(int i=0;i<8;i++){
                int nextR = curR+dx[i];
                int nextC = curC+dy[i];

                //validaity: must be inside the grid and must be 1 and if curdist+1 gives smaller distance than than
                // existing distance value, i.e: curDist+1<dist[nr][nc]
                if(nextR>=0 && nextR <n && nextC>=0 && nextC<n && grid[nextR][nextC]==0 &&
                        curDist+1<dist[nextR][nextC]){
                    //update the new shortest dist
                    dist[nextR][nextC]=curDist+1;

                    //stop when we get destination cell
                    if(nextR == destination[0] && nextC == destination[1])
                        return curDist+1;

                    que.add(new Tuple(curDist+1, nextR, nextC));
                }
            }
        }
        //if no path return -1
        return -1;
    }
}

//Time: worst case we may have to travel all the nodes n*m and for each node 4 neightbour = 4*n*m
//space = n*m for the extra dist matrix