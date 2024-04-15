package graph;

import java.util.ArrayList;
import java.util.List;

//Row col conversion formula
// r,c => r*m+c
public class NumberOfIsland2 {
    public static void main(String[] args) {
        //matrix size, n=row, m=col
        int n = 4, m = 5;

        //coordinates marked as island
        int[][] operators = {{0, 0}, {0, 0}, {1, 1}, {1, 0}, {0, 1},
                {0, 3}, {1, 3}, {0, 4}, {3, 2}, {2, 2}, {1, 2}, {0, 2}
        };

        NumberOfIsland2 obj = new NumberOfIsland2();
        List<Integer> ans = obj.numOfIslands(n, m, operators);

        int sz = ans.size();
        for (int i = 0; i < sz; i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println("");

    }

    private List<Integer> numOfIslands(int n, int m, int[][] operators) {
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        DisjointSetMain ds = new DisjointSetMain(n*m);
        int[][] vis = new int[n][m];
        int len = operators.length;
        int islandCount = 0;
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<len;i++){
            int r = operators[i][0];
            int c = operators[i][1];
            if(vis[r][c]==1){
                //if we have already done addLand to a particular coordinate then add the same result
                result.add(islandCount);
                continue;
            }
            vis[r][c] = 1;
            islandCount++;
            //look for neightbour
            for(int k=0;k<4;k++){
                int nr = r+dx[k];
                int nc = c+dy[k];
                //if the index is a valid index then
                if(nr>=0 && nr<n && nc>=0 && nc<m){
                    //if any neighbour is visited then we have to union and check if they belong to same island
                    if(vis[nr][nc]==1){
                        int nodeNumber = r*m+c;
                        int nextNodeNum = nr*m+nc;

                        if(ds.findParent(nodeNumber) != ds.findParent(nextNodeNum)){
                            //means they are not yet connected, connect them and reduce the increased count
                            islandCount--;
                            ds.unionBySize(nodeNumber, nextNodeNum);
                        }
                    }
                }
            }
            result.add(islandCount);
        }
        return result;
    }
}
