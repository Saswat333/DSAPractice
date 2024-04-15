package graph;

public class DistinctIsland {
    public static void main(String[] args) {
        char[][] grid =  {
                {'0', '1', '1', '1', '0', '0', '0'},
                {'0', '0', '1', '1', '0', '1', '0'}
        };

        char[][] grid1 = {{'0','1','1','0'},{'0','1','1','0'},{'0','0','1','0'},{'0','0','0','0'},{'1','1','0','1'}};

        char[][] grid2 = {{'0','0','0'},{'0','1','0'},{'0','1','0'}};

        DistinctIsland obj = new DistinctIsland();
        System.out.println("Number of island: "+obj.numIslands(grid2));
    }

    private int numIslands(char[][] grid) {
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };//-1,0,1,0,-1,-1,1,1
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };//0,-1,0,1,-1, 1,-1,1
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int componentCount =0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!vis[i][j] && grid[i][j]=='1'){
                    componentCount++;
                    dfs(i,j, grid, vis, dx, dy);
                }
            }
        }
        return componentCount;
    }

    private void dfs(int row, int col, char[][] grid, boolean[][] vis, int[]dx, int[] dy) {
        vis[row][col] = true;
        for(int i=0;i<8;i++){
            int nextR = row+dx[i];
            int nextC = col+dy[i];

            if(nextR>=0 && nextR<grid.length && nextC>=0 && nextC<grid[0].length
                    && !vis[nextR][nextC] && grid[nextR][nextC]=='1'){
                dfs(nextR,nextC,grid, vis, dx, dy);
            }
        }
    }
}
