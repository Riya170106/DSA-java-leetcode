class Solution {
    public int minMoves(int[] nums) {
        int min=nums[0];
        int n=nums.length;
        int sum=0;
        for(int i=0;i<n;i++){
            if(min>nums[i]){
                min=nums[i];
            }
            sum+=nums[i];
        }
        int ans=sum-min*n;
        return ans;
    }
}