package graph;

public class FindEnclave {
    public static void main(String[] args)
    {
        int grid[][] = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}};

        int grid1[][] = {{0,0,0,1,1,1,0,1,0,0},{1,1,0,0,0,1,0,1,1,1},{0,0,0,1,1,1,0,1,0,0},{0,1,1,0,0,0,1,0,1,0},{0,1,1,1,1,1,0,0,1,0},{0,0,1,0,1,1,1,1,0,1},{0,1,1,0,0,0,1,1,1,1},{0,0,1,0,0,1,0,1,0,1},{1,0,1,0,1,1,0,0,0,0},{0,0,0,0,1,1,0,0,0,1}};

        FindEnclave ob = new FindEnclave();
        int ans = ob.numberOfEnclaves(grid1);
        System.out.println(ans);
    }

    private int numberOfEnclaves(int[][] grid) {
        int n= grid.length, m= grid[0].length;
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        boolean [][] vis= new boolean[n][m];
        //0th and n-1th row
        for(int i=0;i<m;i++){
            //0th row: if cell is not visited and is 1(land) call dfs
            if(!vis[0][i] && grid[0][i]==1){
                dfs(0, i, grid,vis, dx, dy);
            }
                if(!vis[n-1][i] && grid[n-1][i]==1){
                dfs(n-1, i, grid,vis, dx, dy);
            }
        }

        //0th and m-1th col
        for(int i=0;i<n;i++){
            if(!vis[i][0] && grid[i][0]==1){
                dfs(i, 0, grid, vis, dx, dy);
            }
            if(!vis[i][m-1] && grid[i][m-1]==1){
                dfs(i, m-1, grid, vis, dx, dy);
            }
        }

        // traverse the entire grid and check for if value is 1 and is not visited then count
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!vis[i][j] && grid[i][j] == 1){
                    ans++;
                }
            }
        }
        return ans;
    }

    public void dfs(int row, int col, int[][] grid, boolean[][] vis,int[] dx, int[]dy){
        int n=grid.length, m=grid[0].length;
        vis[row][col] = true;
        for(int i=0;i<4;i++){
            int nextR = row+dx[i];
            int nextC = col+dy[i];

            if(nextR>=0 && nextR<n && nextC>=0 && nextC<m && !vis[nextR][nextC] && grid[nextR][nextC]==1){
                dfs(nextR, nextC, grid, vis, dx, dy);
            }
        }
    }
}
