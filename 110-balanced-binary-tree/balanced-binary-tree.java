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
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        int leftHt=height(root.left);
        int rightHt=height(root.right);
        int diff=Math.abs(leftHt-rightHt);
        if(diff<=1&&isBalanced(root.left)&&isBalanced(root.right)){
            return true;
        }
        return false;
    }
    public int height(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=height(root.left);
        int right=height(root.right);
        int maxht=Math.max(left,right);
        return maxht+1;
    }
}