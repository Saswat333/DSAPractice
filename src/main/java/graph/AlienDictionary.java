package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {
    public static void main(String[] args) {
        // n = number of entries in dict, k = number of nodes
        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        AlienDictionary obj = new AlienDictionary();
        String ans = obj.findOrder(dict, N, K);

        for (int i = 0; i < ans.length(); i++) {
            System.out.print(ans.charAt(i) + " ");
        }
        System.out.println("");
    }

    private String findOrder(String[] dict, int n, int k) {
        //create adj list from the given dictionary by comparing string in order
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<k;i++){
            adj.add(new ArrayList<>());
        }
        // from dict entry S1 to Sn-1 and last element Sn we will compare with Sn-1
        for(int i=0;i<n-1;i++){
            String s1 = dict[i];
            String s2 = dict[i+1];
            int len = Math.min(s1.length(), s2.length());
            for(int j=0;j<len;j++){
                if(s1.charAt(j)!=s2.charAt(j)){
                    adj.get(s1.charAt(j)-'a').add(s2.charAt(j)-'a');
                    break;
                }
            }
        }

        // Now we have the adj list ready of nodes which
        List<Integer> topo = topoSort(k, adj);
        String ans = "";
        for(int it: topo){
            ans = ans+(char)(it+(int)('a'));
        }

        return ans;
    }

    private List<Integer> topoSort(int v, List<List<Integer>> adj) {
        //create in-degree
        int[] inDegree = new int[v];
        for(int i=0;i<v;i++){
            for(int it: adj.get(i)){
                inDegree[it]++;
            }
        }

        //add indegree to queue
        Queue<Integer> que = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(inDegree[i]==0)
                que.add(i);
        }

        List<Integer> topo = new ArrayList<>();

        while(!que.isEmpty()){
            int node = que.poll();
            topo.add(node);

            for(int it:adj.get(node)){
                inDegree[it]--;
                if(inDegree[it]==0)
                    que.add(it);
            }
        }

        return topo;
    }
}
