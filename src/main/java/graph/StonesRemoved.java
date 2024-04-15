package graph;

import java.util.HashMap;
import java.util.Map;

//On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
//A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
//Basically we can keep only 1 stone in a particular row or column
// so if we can connect all the components(all stones available in its 4 direction), then for each component there will be
// #stone in the component -1 stones removed. So in total sum of #stonesincomponment-1 will be answer
//sum = c1-1 +c2-1 ...cn-1(cn = num of stones in component n ) => (total_stones - number of component) will give result
public class StonesRemoved {
    public static void main(String[] args) {
        // n = length of array stones
        int n = 6;
        int[][] stones = {
                {0, 0}, {0, 2},
                {1, 3}, {3, 1},
                {3, 2}, {4, 3}
        };

        StonesRemoved obj = new StonesRemoved();
        int ans = obj.maxRemove(stones, n);
        System.out.println("The maximum number of stones we can remove is: " + ans);
    }

    private int maxRemove(int[][] stones, int n) {
        int maxRow = 0, maxCol = 0;
        // find max row value and col value from the given stones array
        for(int i=0;i<n;i++){
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSetMain obj = new DisjointSetMain(maxRow+maxCol+2);
        HashMap<Integer,Integer> storeNode = new HashMap<>();
        //for each edge information
        for(int i=0;i<n;i++){
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1]+maxRow+1;
            obj.unionBySize(nodeRow, nodeCol);
            //store all the visited rows and column number so that later we can check for ultimate parents
            //which will give us number of components
            storeNode.put(nodeRow,1);
            storeNode.put(nodeCol,1);
        }

        int countComponent=0;

        for(Map.Entry<Integer,Integer> it: storeNode.entrySet()){
            int node = it.getKey();
            if(obj.findParent(node) == node){
                //condition will satisy for those nodes who are unique parents(# unique_parents == #components)
                countComponent++;
            }
        }

        return n-countComponent;
    }
}
