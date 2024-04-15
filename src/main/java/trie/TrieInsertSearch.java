package trie;

public class TrieInsertSearch {
    TrieNode root;
    public static void main(String[] args) {
        TrieInsertSearch obj = new TrieInsertSearch();
        obj.root= new TrieNode();
        String[] key = {"the","a", "there", "answer", "any", "by", "bye", "their"};
        String search = "there";
        int n= key.length;
        for(int i=0;i<n;i++){
            obj.insertTrie(obj.root, key[i]);
        }

        boolean result = obj.search(obj.root, search);
        System.out.println("Result: "+result);
    }

    private boolean search(TrieNode root, String word) {
        TrieNode curNode = root;
        for(int i=0;i<word.length();i++){
            int curIndx = word.charAt(i)-'a';
            if(curNode.children[curIndx]==null){
                return false;
            }
            curNode = curNode.children[curIndx];
        }
        return curNode.isEnd;
    }

    private void insertTrie(TrieNode root, String key) {
        TrieNode curNode = root;
        for(int i=0;i<key.length();i++){
            int curIndx = key.charAt(i)-'a';
            if(curNode.children[curIndx]==null){
                //create a new node
                curNode.children[curIndx] = new TrieNode();;
            }
            curNode = curNode.children[curIndx];
        }
        curNode.isEnd = true;
    }
}
