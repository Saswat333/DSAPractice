package BinarySearchTree;

public class DeleteNodeBST {
    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null){
            return null;
        }
        if(root.val == key)
            return helper(root);

        TreeNode dummy = root;
        while(root!=null){
            if(key < root.val){
                if(root.left != null && root.left.val == key){
                    //helper gets the new sub tree which will get connected with the parent node
                    root.left = helper(root.left);
                }
                else{
                    root = root.left;
                }
            }
            else{
                if(root.right != null && root.right.val == key){
                    root.right = helper(root.right);
                }
                else{
                    root = root.right;
                }
            }
        }
        return dummy;
    }

    private TreeNode helper(TreeNode root) {
        if(root.left == null){
            return root.right;
        }
        if(root.right == null){
            return root.left;
        }
        else{
            TreeNode rightChild = root.right;
            TreeNode leftMostRight = getLeftMostRightChild(root.left);
            leftMostRight.right = rightChild;
            return root.left;
        }
    }

    private TreeNode getLeftMostRightChild(TreeNode root) {
        if(root.right == null)
            return root;
        return getLeftMostRightChild(root.right);
    }
}
