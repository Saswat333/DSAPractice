package graph;

import java.util.*;

public class AccountMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList() {
            {
                add(new ArrayList<String>(Arrays.asList("John", "j1@com", "j2@com", "j3@com")));
                add(new ArrayList<String>(Arrays.asList("John", "j4@com")));
                add(new ArrayList<String>(Arrays.asList("Raj", "r1@com", "r2@com")));
                add(new ArrayList<String>(Arrays.asList("John", "j1@com", "j5@com")));
                add(new ArrayList<String>(Arrays.asList("Raj", "r2@com", "r3@com")));
                add(new ArrayList<String>(Arrays.asList("Mary", "m1@com")));

            }
        };

        AccountMerge obj = new AccountMerge();
        List<List<String>> ans = obj.accountMergeSolve(accounts);

        int n = ans.size();
        for (int i = 0; i < n; i++) {
            System.out.print(ans.get(i).get(0) + ": ");
            int size = ans.get(i).size();
            for (int j = 1; j < size; j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }

            System.out.println("");
        }

    }

    private List<List<String>> accountMergeSolve(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSetMain ds = new DisjointSetMain(n);

        HashMap<String,Integer> mapMailNode = new HashMap<>(); //used to map mail-id to node(index)
        //Read each mail-id and put it in map. <Mailid, nodenumber>. Nodenumber will be the index
        for(int i=0;i<n;i++){
            //start from 1 as oth index is name
            for(int j=1;j<accounts.get(i).size();j++){
                String mail = accounts.get(i).get(j);
                if(!mapMailNode.containsKey(mail))
                    mapMailNode.put(mail,i);
                else
                    ds.unionBySize(i, mapMailNode.get(mail)); //if the mail-id exist then union both the current node and the node where it exists
            }
        }
        //adding the node and mailid to array of list
        ArrayList<String>[] mergedMail = new ArrayList[n];
        for(int i=0;i<n;i++)
            mergedMail[i] = new ArrayList<>();

        for(Map.Entry<String,Integer> it: mapMailNode.entrySet()){
            String mail = it.getKey();
            int curNode = it.getValue();
            int node = ds.findParent(curNode);
            mergedMail[node].add(mail);
        }
        //from the array of list add name and list of mail-id to answer in sorted order
        List<List<String>> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(mergedMail[i].size() != 0){
                Collections.sort(mergedMail[i]);
                List<String> temp = new ArrayList<>();
                temp.add(accounts.get(i).get(0)); //extract the name for the particular node/indx
                for(String it: mergedMail[i]){
                    temp.add(it);
                }
                ans.add(temp);
            }
        }
        return ans;
    }
}


//Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails
// representing emails of the account.
//Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two
// accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order.
// The accounts themselves can be returned in any order.

//Note: Here we will perform the disjoint set operations on the indices of the accounts considering them as the nodes.
//The algorithm steps are the following:

//First, we will create a map data structure. Then we will store each email with the respective index of the account(the email belongs to) in that map data structure.
//While doing so, if we encounter an email again(i.e. If any index is previously assigned for the email), we will perform union(either unionBySize() or unionByRank()) of the current index and the previously assigned index.
//After completing step 2, now it’s time to merge the accounts. For merging, we will iterate over all the emails individually and find the ultimate parent(using the findUPar() method) of the assigned index of every email. Then we will add the email of the current account to the index(account index) that is the ultimate parent. Thus the accounts will be merged.
//Finally, we will sort the emails for every account separately and store the final results in the answer array accordingly.