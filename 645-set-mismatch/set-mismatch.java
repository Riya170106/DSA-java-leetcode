class Solution {
    public int[] findErrorNums(int[] nums) {
        int duplicate =-1;
        int missing=-1;
        int n=nums.length;
        for(int i=1;i<=n;i++){
            int count=0;
            for(int j=0;j<n;j++){
                if(nums[j]==i){
                    count++;
                }
            }
            if(count==2){
                duplicate=i;
            }
            if(count==0){
                missing=i;
            }
        }
        int []ans=new int[2];
        ans[0]=duplicate;
        ans[1]=missing;
        return ans;
    }
}