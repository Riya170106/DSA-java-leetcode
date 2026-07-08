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
    public int diameterOfBinaryTree(TreeNode root) {
       ht(root);
       return ans;
    }
    int ans=0;
    int ht(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftht=ht(root.left);
        int rightht=ht(root.right);
        ans=Math.max((leftht+rightht),ans);
        return Math.max(leftht,rightht)+1;
    }
}