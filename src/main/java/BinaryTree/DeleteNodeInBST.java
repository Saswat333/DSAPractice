package BinaryTree;

public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key){
        //search the node recursively
        //call the subtree and receive the modified bst as its child, if the node is deleted from that subtree
        if(root==null)
            return root;
        if(key<root.val){
            root.left = deleteNode(root.left,key);
        }
        else if(key>root.val){
            root.right = deleteNode(root.right, key);
        }
        else{
            //found the key
            //case2.a: if its a left skewed or right skewed tree
            if(root.left==null)
                return root.right;
            else if (root.right==null)
                return root.left;
            //case2.b: both are not null
            root.val = findInOrderSucessor(root.right);
            //we have set the deleting node with inorder successor now we have to delete that
            //inorder successor node
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private int findInOrderSucessor(TreeNode node){
        int minVal = node.val;
        while(node.left!=null){
            minVal = node.left.val;
            node = node.left;
        }
        return minVal;
    }
}
/*
1. Search the node
2. Delete the node.
    Delete Leaf:
    - check if both left and right subtree is null then its a leaf
    - set that node(left/right) at parent as null
    Delete non-leaf:
    a. delete with one child:
        - if with one child, then point parents.left or parents.right to the child of the node to be deleted
        - as the child subtree is already a BST so we can directly assign it from parent
    b. delete with both child [understand]:
        - we cant replace the deleted node with any sub-tree because we have both subtrees
        - find a element which can be put at the deleted node and still keep it as an BST.
        - We have 2 options:
            - the largest elem of left-subtree (IN-ORDER PREDECESSOR)
            - the smallest elem of right subtree (IN-ORDER SUCCESSOR) -consider it default replacement
        - delete the in-order successor, and place it in the delete node position

Time complexity: O(n) , when tree is skewed
Space: O(n), skewed tree, all node will be in stack
* */