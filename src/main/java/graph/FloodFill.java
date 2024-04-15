package graph;

public class FloodFill {
    public static void main(String[] args) {
        int[][] image =  {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };

        // sr = 1, sc = 1, newColor = 2
        FloodFill obj = new FloodFill();
        int[][] ans = obj.floodFill(image, 1, 1, 2);
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[i].length; j++)
                System.out.print(ans[i][j] + " ");
            System.out.println();
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor){
        int initColor = image[sr][sc];
        if(image[sr][sc]!=newColor)
            dfs(image, sr, sc, initColor, newColor);
        return image;
    }

    public void dfs(int[][] image, int row, int col, int oldColor, int newColor){
        if(row<0 || row>=image.length || col<0 || col>=image[0].length || image[row][col] != oldColor)return;
        //change old color to new
        image[row][col] = newColor;
        dfs(image, row, col-1, oldColor, newColor); //0, -1
        dfs(image, row, col+1, oldColor, newColor); // 0,+1
        dfs(image, row+1, col, oldColor, newColor); //+1, 0
        dfs(image, row-1, col, oldColor, newColor); // -1,0
    }
}
