package graph;// Multi Source shortest path algorithm
// we wil store the graph in 2d array/adj matrix instead of adj list
// we will be building a cost matrix , where cost to reach self is 0
//Negative cycle: If the cost of reaching

import java.util.Arrays;

//matrix[i][j] =min(matrix[i][j], matrix[i ][k]+matrix[k][j]), where i = source node,
//                  j = destination node and k = the node via which we are reaching from i to j.
// first loop = pick k
// 2 nested loop to pick i and j so we can check matrix[i][j] = min(matrix[i][j], matrix[i ][k]+matrix[k][j])
public class FloydWarshal {
    public static void main(String[] args) {
        int V = 4;
        int[][] matrix = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = -1;
            }
        }

        matrix[0][1] = 2;
        matrix[1][0] = 1;
        matrix[1][2] = 3;
        matrix[3][0] = 3;
        matrix[3][1] = 5;
        matrix[3][2] = 4;
        
        FloydWarshal obj = new FloydWarshal();
        obj.shortest_distance(matrix);

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void shortest_distance(int[][] matrix) {
        int n = matrix.length;

        //matrix initialization
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j] == -1){
                    matrix[i][j] = (int)(1e9);
                }
                if(i==j)
                    matrix[i][j] = 0; // for diagonals
            }
        }

        System.out.println(Arrays.deepToString(matrix));
        //main logic
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k]+matrix[k][j]);
                }
            }
        }

        // if any max value replace to -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == (int)(1e9))
                    matrix[i][j] = -1;
            }
        }
    }
}
// time = v^3 (for 3 nested loop)
//space = adj matrix store in 2d array = v^2