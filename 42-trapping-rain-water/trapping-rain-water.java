class Solution {
    public int trap(int[] height) {
       int n=height.length;
       int l=0,r=n-1;
       int leftmax=0;
       int rightmax=0;
       int ans=0;
       while(l<r)
       {
        leftmax=Math.max(leftmax,height[l]);
        rightmax=Math.max(rightmax,height[r]);
        if(leftmax<rightmax)
        {
            ans+=leftmax-height[l];
            l++;
        }
        else
        {
            ans+=rightmax-height[r];
            r--;
        }
       }
       return ans;
    }
}