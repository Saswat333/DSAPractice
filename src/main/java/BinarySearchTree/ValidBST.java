package BinarySearchTree;


import java.util.ArrayList;

public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        // we  need to check for a given subtree the left node should be smaller than equal root value and
        //right subtree val should be greater than equal root
        return isValidUtil(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private boolean isValidUtil(TreeNode node, double minVal, double maxVal) {
        if(node == null)
            return true;
        //negative scenario
        if(node.val<=minVal || node.val>=maxVal)
            return false;

        return isValidUtil(node.left, minVal, node.val) && isValidUtil(node.right, node.val, maxVal);
    }
}
