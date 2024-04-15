package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySerachTreeBasicOperations {

    public static void main(String[] args) {
        BinarySerachTreeBasicOperations tree = new BinarySerachTreeBasicOperations();
        /* Let us create following BST
                50
             /     \
            30      70
           /  \    /  \
         20   40  60   80
     */

        TreeNode root = new TreeNode(50); //root
        tree.insertNode(root,20);
        tree.insertNode(root,30);
        tree.insertNode(root,40);
        tree.insertNode(root,70);
        tree.insertNode(root,60);
        tree.insertNode(root,80);
        tree.insertNode(root, 65);

        //count number of node in tree
        int totalCount = tree.countNode(root);
        System.out.println("Tree Node in tree: "+ totalCount);

        //Inorder Traversal
        System.out.print("InOrder traversal: ");
        tree.printInOrderTraversal(root);
        System.out.println("");


        //search key
//        int key = 10;
//        TreeNode res = tree.searchNode(root, key);
//        if(res == null)
//            System.out.println(key +" not found");
//        else
//            System.out.println(key +" found");

        //find minimum and max value of binary search tree
//        tree.MinMaxOfBST(root);

        // ceil = lower value greater than equal the key, eg ceil of 66 is 70
//        key = 61;
//        int ceil = tree.ceilValue(root, key);
//        System.out.println("Ceil of "+key+" is "+ ceil);

        //DELETE NODE
//        DeleteNodeBST delOperation = new DeleteNodeBST();
//        TreeNode curTree = delOperation.deleteNode(root, 60);
//        tree.printInOrderTraversal(curTree);

        // check is valid bst
//        int max= Integer.MAX_VALUE, min = Integer.MIN_VALUE;
//        if(tree.isValidBST(root, min, max))
//            System.out.println("Is valid bst.");
//        else
//            System.out.println("Is NOT valid bst.");

        // Kth largest and Smallest number
//        KthLargestSmallest kth = new KthLargestSmallest();
//        int kthValue = 2; // 2nd smallest. largest
//        int resSmallest = kth.kthSmallest(root, kthValue);
//        int resLargest = kth.kthLargest(root, kthValue);
//        System.out.println("The "+ kthValue+" th smallest value is "+resSmallest);
//        System.out.println("The "+ kthValue+" th largest value is "+resLargest);

        PredecessorSuccessor preSuccessorObj = new PredecessorSuccessor();
        int key = 50;
        TreeNode preSuccessorResponse = preSuccessorObj.inorderSuccessor(root, key);
        TreeNode inSuccessorResponse = preSuccessorObj.inorderPredecessor(root, key);
        System.out.println("Key: "+ key);
        System.out.println(" Inorder Predecessor : "+preSuccessorResponse.val);
        System.out.println(" Inorder Successor : "+inSuccessorResponse.val);
    }

    public TreeNode insertNode(TreeNode root, int key){
        if(root == null){
            root = new TreeNode(key);
            return root;
        }

        if(key < root.val){
            root.left = insertNode(root.left, key);
        }
        else if(key > root.val){
            root.right = insertNode(root.right, key);
        }
        return root;
    }

    private int countNode(TreeNode root){
        if( root == null)
            return 0;
        return 1+ countNode(root.left) + countNode(root.right);
    }

    public void printInOrderTraversal(TreeNode root){
        if(root!=null){
            printInOrderTraversal(root.left);
            System.out.print(root.val+" ");
            printInOrderTraversal(root.right);
        }
    }

    public void inOrderTraversalList(TreeNode root, List<Integer> res){
        if(root!=null){
            inOrderTraversalList(root.left, res);
            res.add(root.val);
            inOrderTraversalList(root.right, res);
        }
    }

    public TreeNode searchNode(TreeNode root, int key){
        if(root == null || root.val == key)
            return root;

        if(key > root.val){
            return searchNode(root.right, key);
        }
        return searchNode(root.left, key);
    }

    public void MinMaxOfBST(TreeNode root){
        // InOrder traversal of binary search tree is always sorted in non-decreasing order
        List<Integer> result = new ArrayList<>();
        inOrderTraversalList(root, result);
        System.out.println("Minimum value: " + result.get(0));
        System.out.println("Maximum value: " + result.get(result.size()-1));

    }

    public int ceilValue(TreeNode root, int key){
        // when checking key with root value, if root value is bigger than key at the iteration save the node value as that
        // might be the ceil and we dont find any value larger than the key later
        int ceil = -1;
        while(root != null){
            if(root.val == key) {
                ceil = root.val;
                return ceil;
            }
            if(key > root.val)
                root = root.right;
            else {
                ceil = root.val;
                root = root.left;
            }
        }
        // if no exact value found return the nearest larger number
        return ceil;
    }

    private boolean isValidBST(TreeNode root, int min, int max){
        if(root == null)
            return true;
        if(root.val <= min || root.val >= max)
            return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
