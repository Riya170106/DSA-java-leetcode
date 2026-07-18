class Solution {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        int result=1;
        int min=nums[0];
        int max=nums[n-1];
        for(int i=1;i<=1000;i++){
            if(min%i==0&&max%i==0){
                result=i;
            }
        }
        return result;
    }
}