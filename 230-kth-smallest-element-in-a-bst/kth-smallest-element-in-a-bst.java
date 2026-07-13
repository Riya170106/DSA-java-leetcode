/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int prevorder=0;
    public int kthSmallest(TreeNode root, int k) {
        if(root==null){
            return -1;
        }
        if(root.left!=null){
            int leftans=kthSmallest(root.left,k);
            if(leftans!=-1){
                return leftans;
            }
        }
        if(prevorder+1==k){
            return root.val;
        }
        prevorder=prevorder+1;
        if(root.right!=null){
            int rightans=kthSmallest(root.right,k);
            if(rightans!=-1){
                return rightans;
            }
        }
        return -1;
    }
}