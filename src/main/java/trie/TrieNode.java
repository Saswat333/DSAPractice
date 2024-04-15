package trie;

import java.util.Arrays;

public class TrieNode {
    TrieNode[] children = new TrieNode[26];;
    boolean isEnd;
    public TrieNode(){
        isEnd = false;
        for(int i=0;i<26;i++)
            children[i] = null;
    }
//    public boolean search(TrieNode root, String key) {
//        TrieNode curNode = root;
//        for(int i=0;i<key.length();i++){
//            int curIndx = key.charAt(i)-'a';
//            //not present
//            if(curNode.children[curIndx]==null)
//                return false;
//            System.out.println(curIndx);
//        }
//        return curNode.isEnd;
//    }
//    public void insertTrie(TrieNode root, String key) {
//        TrieNode curNode = root;
//        for(int i=0;i<key.length();i++){
//            int curIndx = key.charAt(i)-'a';
//            if(curNode.children[curIndx]==null){
//                //create a new node
//                curNode.children[curIndx] = new TrieNode();;
//                System.out.println(curIndx);
//            }
//            curNode = curNode.children[curIndx];
//        }
//        curNode.isEnd = true;
//    }
}
