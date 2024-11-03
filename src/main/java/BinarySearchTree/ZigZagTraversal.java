package BinarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ZigZagTraversal {
    public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;

        //true: L-R, false: R-L
        boolean flag = true;
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            // store current level data, and size will be equal to number of elem currently in queue
            // defining size will help us to put values in reverse
            //for every iteration new temp array
            ArrayList<Integer> temp = new ArrayList<>(size);
            for(int i=0;i<size;i++){
                //if not child push node to queue
                if(que.peek().left != null)
                    que.offer(que.peek().left);
                if(que.peek().right != null)
                    que.offer(que.peek().right);
                temp.add(que.poll().val);
                //check flag and according to direction pull elem from que to list
//                if(flag) {
//                    temp.add(que.poll().val);
//                }
//                else {
//                    //adds value to front of the list always: making the list reverse
//                    temp.add(0, que.poll().val);
//                }
            }
            // once a level ends we reverse the flag, add temp to result list
            if(flag){
                res.add(temp);
            }
            else{
                Collections.reverse(temp);
                res.add(temp);
            }
            flag = !flag;
//            res.add(temp);
        }
        return res;
    }

//    public static void main(String[] args) {
//        int i, j;
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.left.right = new TreeNode(11);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        root.left.right.left = new TreeNode(10);
//        root.right.left.left = new TreeNode(13);
//        root.right.left.right = new TreeNode(16);
//        ArrayList <ArrayList< Integer >> ans;
//        ans = zigzagLevelOrder(root);
//        System.out.println("Zig Zag Traversal of Binary Tree ");
//        for (i = 0; i < ans.size(); i++) {
//            for (j = 0; j < ans.get(i).size(); j++) {
//                System.out.print(ans.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
//    }


}
