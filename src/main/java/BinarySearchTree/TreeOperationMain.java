package BinarySearchTree;


import java.util.ArrayList;

public class TreeOperationMain {
    public static void main(String[] args) {
        TreeOperationMain obj = new TreeOperationMain();
        //put the question number which you want to test
        int questionNum=4;

        switch (questionNum){
            case 1:{
                TreeNode root = obj.tree1();
                //Question1: is valid binary search tree
                boolean ans;
                ValidBST obj1 = new ValidBST();
                ans = obj1.isValidBST(root);
                System.out.println("Is Valid BST: "+ans);
                break;
            }
            case 2:{
                TreeNode root = obj.tree2(); //input
                //Question2: ZIGZAG BST
                ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
                ZigZagTraversal obj2 = new ZigZagTraversal();
                ans = obj2.zigzagLevelOrder(root);
                obj.printNestedList(ans);
                break;
            }
            case 3:{
                //Question3: Delete node in a BST
                TreeNode root = obj.tree3(); //input
                int key=50;
                System.out.println("Actual BST:");
                obj.printTreePreOrder(root);
                System.out.println();
                DeleteNodeInBST obj1 = new DeleteNodeInBST();
                TreeNode ans = obj1.deleteNode(root,key);
                System.out.println("Updated BST ");
                obj.printTreePreOrder(ans);
                break;
            }
            case 4:{
                //Question4: Lowest Common Ancestor in BST
                TreeNode root = obj.tree4(); //input
                TreeNode p = new TreeNode(70);
                TreeNode q = new TreeNode(90);
                System.out.println("Actual BST:");
                obj.printTreePreOrder(root);
                System.out.println();
                LCABinarySearchTree obj1 = new LCABinarySearchTree();
                TreeNode ans = obj1.lowestCommonAncestor(root, p, q);
                System.out.println("LCA of the BST: "+ans.val);
                break;
            }
            default:
                System.out.println("Wrong question number.");
        }
    }

    private void printNestedList(ArrayList<ArrayList<Integer>> ans) {
        int i, j;
        for (i = 0; i < ans.size(); i++) {
            for (j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }

    private void printTreeInOrder(TreeNode node){
        if (node == null)
            return;
        printTreeInOrder(node.left);
        System.out.print(node.val + " ");
        printTreeInOrder(node.right);

    }

    private void printTreePreOrder(TreeNode node){
        if (node == null)
            return;
        System.out.print(node.val + " ");
        printTreePreOrder(node.left);
        printTreePreOrder(node.right);

    }

    public TreeNode tree1(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.right = new TreeNode(11);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(10);
        root.right.left.left = new TreeNode(13);
        root.right.left.right = new TreeNode(16);
        return root;
    }

    public TreeNode tree2(){
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(12);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(18);

        return root;
    }

    public TreeNode tree3(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(50);
        root.right.left = new TreeNode(20);
        root.right.right = new TreeNode(80);
        root.right.right.left = new TreeNode(70);
        root.right.right.right = new TreeNode(100);
        root.right.right.left.left = new TreeNode(60);
        root.right.right.left.left.right = new TreeNode(65);
        return root;
    }

    public TreeNode tree4(){
        TreeNode root = new TreeNode(60);
        root.left = new TreeNode(20);
        root.right = new TreeNode(80);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(40);
        root.left.right.left = new TreeNode(30);
        root.left.right.right = new TreeNode(50);
        root.right.left = new TreeNode(70);
        root.right.right = new TreeNode(90);
        return root;
    }
}
