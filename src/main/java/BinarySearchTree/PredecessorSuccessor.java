package BinarySearchTree;

public class PredecessorSuccessor {
    public TreeNode inorderPredecessor(TreeNode root, int key){
        TreeNode predecessor = null;
        while(root != null){
            if(root.val >= key){
                root = root.left;
            }
            else{
                predecessor = root;
                root = root.right;
            }
        }

        return predecessor;
    }

    public TreeNode inorderSuccessor(TreeNode root, int key){
        TreeNode successor = null;
        while(root != null){
            if(root.val <= key){
                root = root.right;
            }
            else{
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }
}
