package graph;

import java.util.Arrays;

public class CityWithSmallestNeighbour {
    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] edges =  {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;

        CityWithSmallestNeighbour obj = new CityWithSmallestNeighbour();
        int cityNo = obj.findCity(n, edges, distanceThreshold);
        System.out.println("The answer is node: " + cityNo);
    }

    private int findCity(int n, int[][] edges, int distanceThreshold) {
        int m = edges.length;
        //Create distance matrix and fill to max
        int[][] dist = new int[n][n];
        for(int [] row: dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        //now read all the edges value into distance matrix
        for(int i=0;i<m;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            dist[u][v] = wt;
            dist[v][u] = wt;
        }
        // fill all diagonals as 0, as distance to self is 0
        for(int i=0;i<n;i++)
            dist[i][i] = 0;

        // floyd warshal logic
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE)
                        continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        //now calculate the count of city which as min number city under threshold and also
        //keep track of the largest city with min city under threshold
        int cntCity=n;//because beest case there can be all the cities within threshold limit, we need the minimum
        int cityNo = -1;
        for(int city=0;city<n;city++){
            int cnt=0;
            for(int adjCity=0;adjCity<n;adjCity++){
                if(dist[city][adjCity]<= distanceThreshold)
                    cnt++;
            }

            if(cnt<=cntCity){
                cntCity = cnt;
                cityNo = city; // if there are min city between 2 nodes then return city with greatest number
            }
        }

        return cityNo;
    }
}
