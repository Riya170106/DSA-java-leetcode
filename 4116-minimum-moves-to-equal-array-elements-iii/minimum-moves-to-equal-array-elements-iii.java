class Solution {
    public int minMoves(int[] nums) {
        int max=nums[0];
        int n=nums.length;
        int sum=0;
        for(int i=0;i<n;i++){
            if(nums[i]>max){
                max=nums[i];
            }
            sum+=nums[i];
        }
        int ans=max*n-sum;
        return ans;
    }
}