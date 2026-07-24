class Solution {
    public int smallestRangeI(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
        int min=nums[0];
        int max=nums[n-1];
        int result=(max-k)-(min+k);
        if(result<0){
            return 0;
        }
        else{
            return result;
        }
    }
}