class Solution {
    public int numIdenticalPairs(int[] nums) {
        int n=nums.length;
        int count=0;
        int x;
        int[]freq=new int [101];
        for(int i=0;i<n;i++){
            x=nums[i];
            count=count+freq[x];
            freq[x]++;
        }
        return count;
    }
}