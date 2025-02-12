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
        List<Integer> topo = topoSort(k, adj); //returns the index of the chars, [1, 3, 0, 2] for b,d,a,c

        StringBuilder ans = new StringBuilder();
        for(int it: topo){
            ans.append((char) (it + (int) ('a')));
        }

        return ans.toString();
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

//time complexity:
//space complexity:

/*
gfg: https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
tuf [above solution explanation]: https://takeuforward.org/data-structure/alien-dictionary-topological-sort-g-26/

LeetCode(Premium)
There is a new alien language that uses the English alphabet However, the order among the letters is unknown to you.
You are given a list of strings words from the alien language's dictionary, where the strings
in words are sorted lexicographically by the rules of this new language.
Return a string of the unique letters in the new alien language sorted in lexicographically increasing order,
by the new language's rules. If there is no solution, return "".
If there are multiple solutions, return any of them.
A string s is lexicographically smaller than a string t if at the first letter where they
differ, the letter in s comes before the letter in t in the alien language. If the first
min(s.length, t. length) letters are the same, then s is smaller if and only if s. length
< t. length .
Example 1:
Input: words = ["wrt , "wrf", "er","ett", rftt"]
Output: "wertf"


Simple Explanation:
- pick 2 string at a time iterate over it and if there is a mismatch then add those 2 characters to
    adjecency list.
- from the above line we would have formed a adjecency list, now run the topological sort on the list.

* */
