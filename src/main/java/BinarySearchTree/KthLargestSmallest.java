package BinarySearchTree;

public class KthLargestSmallest {
    public int kthSmallest(TreeNode root, int k){
        // res[0] = counter, res[1] = result
        int[] res = {0,-1};
        helperSmallest(root, k, res);
        return res[1];
    }

    private void helperSmallest(TreeNode root, int k, int[] res) {
        if(root != null){
            helperSmallest(root.left,k, res);
            //increase counter, if counter reached k then return
            res[0] = res[0]+1;
            if(res[0] == k){
                res[1] = root.val;
                return;
            }
            helperSmallest(root.right, k, res);
        }
    }

    public int kthLargest(TreeNode root, int k){
        // res[0] = counter, res[1] = result
        int[] res = {0,-1};
        helperLargest(root, k, res);
        return res[1];
    }

    private void helperLargest(TreeNode root, int k, int[] res) {
        if(root != null){
            helperLargest(root.right,k, res);
            //increase counter, if counter reached k then return
            res[0] = res[0]+1;
            if(res[0] == k){
                res[1] = root.val;
                return;
            }
            helperLargest(root.left, k, res);
        }
    }



}
