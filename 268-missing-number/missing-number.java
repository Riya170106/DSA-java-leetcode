class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        int i=0;
        while(i<n){
            int j=nums[i];
            if(j<n&&nums[i]!=nums[j]){
                swap(nums,i,j);
            }
            else{
                i++;
            }
        }
        for(i=0;i<n;i++){
            if(nums[i]!=i){
                return i;
            }
        }
        return n;
    }
        void swap(int[]nums,int i,int j)
        {
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }
}