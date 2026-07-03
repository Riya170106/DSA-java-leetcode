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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }if(p==null||q==null){
            return false;
        }
        boolean isleftsame=isSameTree(p.left,q.left);
        boolean isrightsame=isSameTree(p.right,q.right);
        return (isleftsame&&isrightsame&&p.val==q.val);
    }
}