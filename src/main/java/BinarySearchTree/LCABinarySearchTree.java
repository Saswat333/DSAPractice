package BinarySearchTree;

public class LCABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        TreeNode node = root;
        while(node!=null){
            if(p.val<node.val && q.val<node.val)
                node = node.left;
            else if (p.val>node.val && q.val>node.val)
                node=node.right;
            else
                return node;
        }
        return null;
    }
}
/*
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Solution Explanation:
1. find the divergent node:
    - node where p<node.val and q> node.val, is divergent node and can be LCS. Basically we are just checking is node
    are on different side or not
    - edge case: one of the node can be equal to the root node
        - then root can be lca


Time Complexity:
    - O(h) or O(logn), h=height of the tree
Space: O(h), stack
* */