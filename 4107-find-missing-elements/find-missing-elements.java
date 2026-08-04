class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer>ans=new ArrayList<>();
        int n=nums.length;
        for(int i=0;i<n-1;i++){
            int j=nums[i]+1;
            while(j<nums[i+1]){
                ans.add(j);
                j++;
            }
        }
        return ans;
    }
}