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
    public boolean isidentical(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }
        boolean isleftsame=isidentical(p.left,q.left);
        boolean isrightsame=isidentical(p.right,q.right);
        return (isleftsame&&isrightsame&&p.val==q.val);
    }
    public boolean isSubtree(TreeNode root, TreeNode subroot) {
        if(root==null&&subroot==null){
            return true;
        }
        if(root==null||subroot==null){
            return false;
        }
        if(root.val==subroot.val&&isidentical(root,subroot)){
            return true;
        }
        return (isSubtree(root.left,subroot)|| isSubtree(root.right,subroot));
    }
}