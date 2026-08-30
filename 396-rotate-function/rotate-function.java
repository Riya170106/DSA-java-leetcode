class Solution {
    public int maxRotateFunction(int[] nums) {
        int n=nums.length;
        long sum=0;
        long F=0;
        for (int i=0;i<n;i++)
        {
         sum+=nums[i];
         F+=(long)i*nums[i];
        }
        long max=F;
        for(int j=1;j<n;j++)
        {
        F=F+sum-(long)n*nums[n-j];
        max=Math.max(max,F);
        }
      return (int)max;
    }
}