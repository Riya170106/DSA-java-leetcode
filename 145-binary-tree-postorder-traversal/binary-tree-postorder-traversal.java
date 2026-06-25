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
    public void postorderTraversalHelper(TreeNode node,List<Integer> output){
        if(node==null){
            return;
        }
        postorderTraversalHelper(node.left,output);
        postorderTraversalHelper(node.right,output);
        output.add(node.val);
    }
    public List<Integer> postorderTraversal(TreeNode root) {
       List<Integer>output=new ArrayList<>();
       if(root==null){
          return output;
        }
      postorderTraversalHelper(root,output);
      return output;
   }
}