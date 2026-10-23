class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer>ans=new ArrayList<>();
        HashSet<Integer>set=new HashSet<>();
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(set.contains(nums[i])){
                ans.add(nums[i]);
            }else{
                set.add(nums[i]);
            }
        }
        return ans;
    }
}