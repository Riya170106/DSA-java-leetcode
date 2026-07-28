class Solution {
    public int majorityElement(int[] nums) {
        int vote=0;
        int candidate=0;
        int n=nums.length;
        for(int i=0;i<n;i++){
            if (vote==0){
                candidate=nums[i];
            }
            if(nums[i]==candidate){
                vote++;
            }
            else{
                vote--;
            }
        }
        return candidate;
    }
}