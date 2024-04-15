package graph;


import java.util.LinkedList;
import java.util.Queue;

public class RottenOrange {
    private int orangesRotting(int[][] grid) {
        int[] rowX = {-1,0,1,0};
        int[] colY = {0,1,0,-1};
        if(grid == null || grid.length==0)
            return -1;
        int row = grid.length, col = grid[0].length;
        Queue<Pair1> que = new LinkedList<>();
        int totalOranges = 0; // total = rotten + fresh
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j] == 2)
                    que.offer(new Pair1(i,j));
                if(grid[i][j] != 0 )
                    totalOranges++;
            }
        }
        int day=0, cntRot=0;//cntRot= total oranges which are rotten by end, day = number of iteration
        while(!que.isEmpty()){
            int size = que.size();
            cntRot += size;
            //take each rotten orange one by one
            while(size-- != 0){
                Pair1 temp = que.poll();
                for(int k=0;k<4;k++){
                    int dx = temp.first + rowX[k];
                    int dy = temp.second + colY[k];

                    if(dx<0 || dx>=row || dy<0 || dy>=col || grid[dx][dy] != 1)
                        continue;
                    //if all condition satify: mark current cell as rotten and add the neighbout to queue
                    que.add(new Pair1(dx, dy));
                    grid[dx][dy] = 2;
                }
            }
            if(!que.isEmpty()){
                day++;
            }
        }
        return (totalOranges == cntRot)?day:-1;
    }

    public static void main(String[] args) {
        int arr[][]={ {2,1,1} , {1,1,0} , {0,1,1} };

        RottenOrange obj = new RottenOrange();
        int rotting = obj.orangesRotting(arr);
        System.out.println("Minimum Number of Minutes Required: "+rotting);
    }
}
