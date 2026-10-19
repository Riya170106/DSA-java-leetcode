class Solution {
    public int maximumDifference(int[] nums) {
        int maxdiff=-1;
        int n=nums.length;
        int min=nums[0];
        for(int i=0;i<n;i++){
            if(nums[i]>min){
                int diff=nums[i]-min;
                maxdiff=Math.max(maxdiff,diff);
            }else{
                min=nums[i];
            }
        }
        return maxdiff;
    }
}